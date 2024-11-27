package top.rongxiaoli.plugins.PicturesPlugin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase;
import top.rongxiaoli.log.ElysiaLogger;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Picture plugin.
 */
public class PicturesPlugin extends JRawCommand implements PluginBase {
    private final ElysiaLogger logger = new ElysiaLogger();
    /**
     * The PicturePlugin static instance.
     */
    public static final PicturesPlugin INSTANCE = new PicturesPlugin();
    private static final String NAME = "PicturesPlugin";
    private DelayedDisposer disposer;
    private static boolean isPluginRunning = false;

    /**
     * Pictures from lolicon API.
     */
    public PicturesPlugin() {
        super(Elysia.INSTANCE, "setu");
        setUsage("[/]setu [keyword1 keyword2 keyword3 ...]");
        setDescription("涩图，使用Lolicon API，可指定关键词");
        setPrefixOptional(true);
    }

    /**
     * PicturePlugin processing main method.
     */
    @Override
    public void onCommand(@NotNull CommandContext context, @NotNull MessageChain args) {
        // Plugin running?
        if (!isPluginRunning) {
            context.getSender().sendMessage("已禁用该插件");
            return;
        }
        long userID = 0, subjectID = 0;
        boolean isDirectMessaging = false;
        // From console, return:
        try {
            userID = Objects.requireNonNull(context.getSender().getUser()).getId();
            subjectID = Objects.requireNonNull(context.getSender().getSubject()).getId();
        } catch (NullPointerException e) {
            logger.warn(NAME, "This command cannot be invoked from console! ");
        }
        if (userID == 0 || subjectID == 0) {
            logger.warn(NAME, "This command cannot be invoked from console! ");
            return;
        }
        // Is DM?
        if (userID == subjectID) {
            isDirectMessaging = true;
        }

        // From user, log it:
        logger.debug(NAME, "Command invoked by " + context.getSender().getUser().getId()
                + ", on context: " + (context.getSender().getSubject() == null ? " " : context.getSender().getSubject().getId()));

        // Try adding user into cooling queue.
        try {
            disposer.AddUser(Objects.requireNonNull(context.getSender().getUser()));
            // User already cooling.
        } catch (DelayedDisposer.ElementAlreadyExistsException e) {
            // Reject request.
            long time = disposer.QueryCoolingTime(Objects.requireNonNull(context.getSender().getUser()));
            if (isDirectMessaging) {
                context.getSender().sendMessage("请等待冷却：" + time + "秒");   // XXX: Use reply instead of directly say sth.
                logger.verbose(NAME, "User is cooling. Done. ");
            } else {
                context.getSender().sendMessage(new At(userID).plus("请等待冷却：" + time + "秒"));   // Todo: Use reply instead of directly say sth.
                logger.verbose(NAME, "User is cooling. Done. ");
            }
            return;
        }

        // Processing request.
        if (isDirectMessaging) {
            context.getSender().sendMessage("正在处理你的消息");
        }else {
            context.getSender().sendMessage(new At(userID).plus("正在处理你的消息"));
        }

        // Check tags' safety.
        if (!messageTagSafetyCheck(args)) {
            context.getSender().sendMessage("已触发安全警告：请求已拦截。");
            logger.warn(NAME, "Security alarm triggered. Request intercepted. ");
            messageOutputToLogAsWarn(args);
            return;
        }

        // Prepare API request.
        StringBuilder stbAPI = new StringBuilder("https://api.lolicon.app/setu/v2?size=regular&");
        stbAPI.append("proxy=i.pixiv.re&");
        for (Message m :
                args) {
            stbAPI.append("tags=").append(m.contentToString()).append("&");
        }

        // Safe. Request start.
        String result = null;
        try {
            result = HttpUtil.get(stbAPI.toString());
        } catch (IORuntimeException e) {
            context.getSender().sendMessage("API请求失败。");
        }
        logger.debug(NAME, "API HTTP GET successful. ");
        logger.verbose(NAME, result);
        PictureAPIDataStruct base = JSONUtil.toBean(result, PictureAPIDataStruct.class);
        PictureAPIDataStruct.Data data = base.getData().get(0);
        // Prepare file storage.
        Path targetPath = new File(Elysia.GetDataPath().toFile(), "PictureCache").toPath();

        // Download file.
        String[] UrlSplit = data.urls.regular.split("/");
        String localFileName = UrlSplit[UrlSplit.length - 1];
        // File exists?
        boolean isPictAvailable = true;
        if (!FileUtil.isExistsAndNotDirectory(new File(targetPath.toFile(), localFileName).toPath(), false)) {
            try {
                HttpUtil.downloadFile(base.getData().get(0).urls.regular, targetPath.toFile());
            } catch (IORuntimeException e) {
                context.getSender().sendMessage("图片源请求失败。");
                isPictAvailable = false;
            } catch (HttpException e) {
                context.getSender().sendMessage("图片可能已从Pixiv移除。");
            }
            logger.verbose(NAME, "Picture downloaded. ");
        } else {
            logger.verbose(NAME, "File exists. ");
        }

        // Ready for file uploading and picture description.
        // Picture description.
        String stbPictDescription = "标题：" + data.title + "\n" +
                "pid: " + data.pid + "\n" +
                "作者：" + data.author + "\n" +
                data.urls.regular;
        context.getSender().sendMessage(stbPictDescription);
        // Picture file.
        logger.verbose(NAME, "Done processing: " + userID + " on context: " + subjectID);
        if (!isPictAvailable) {
            return;
        }
        File imageFile = new File(targetPath.toFile(), localFileName);
        Image image = ExternalResource.uploadAsImage(imageFile, context.getSender().getSubject());
        MessageChainBuilder PictureMessage = new MessageChainBuilder();
        PictureMessage.append(image);
        context.getSender().sendMessage(PictureMessage.build());
    }

    /**
     * Safety check for URL.
     * @param mc Source message chain.
     * @return True if check pass.
     * <br/>False if there is special symbols.
     */
    private boolean messageTagSafetyCheck(MessageChain mc) {
        for (Message message :
                mc) {
            // Logic 1: Special characters.
            if (ReUtil.isMatch("\\\\|\\@|\\!|\\$|\\<|\\>|\\#|\\%|\\[|\\]|\\(|\\)|\\.|\\/", message.contentToString())) {
                return false;
            }
            // Logic 2: Symbol &.
            if (message.contentToString().contains("&")) {
                return false;
            }
        }
        return true;
    }
    private void messageOutputToLogAsWarn(MessageChain mc) {
        logger.warn(NAME, "Message.contentToString: ");
        logger.info(NAME, "MessageChain element start. ");
        for (Message m :
                mc) {
            logger.warn(NAME, m.contentToString());
        }
        logger.info(NAME, "MessageChain element end. ");
    }

    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {
        logger.debug(NAME, "Command loading. ");
        logger.verbose(NAME, "Initializing Disposer. ");
        this.disposer = new DelayedDisposer();
        disposer.startTiming();
        logger.verbose(NAME, "Try creating directories. ");
        Path targetPath = new File(Elysia.GetDataPath().toFile(), "PictureCache").toPath();
        logger.verbose(NAME, targetPath.toString());
        if (!targetPath.toFile().mkdirs() && targetPath.toFile().exists()) {
            logger.warn(NAME, "Directories could not be created. Could be either directory already exists or directory cannot be created. ");
        }
        isPluginRunning = true;
        logger.debug(NAME, "Command loaded. ");
    }

    /**
     * Reload method. Usually for resetting state.
     */
    @Override
    public void reload() {
        logger.debug(NAME, "Reloading. ");
        disposer = new DelayedDisposer();
        logger.debug(NAME, "Reload complete. ");
    }

    /**
     * Shutdown method.
     */
    @Override
    public void shutdown() {
        logger.debug(NAME, "shutdown() invoked.");
        disposer.Shutdown();
        isPluginRunning = false;
        logger.debug(NAME, "Shut down.");
    }

    /**
     * Manually save the data.
     */
    @Override
    public void saveData() {
        logger.debug(NAME, "Nothing to store. ");
    }

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    @Override
    public void reloadData() {
        logger.debug(NAME, "Nothing to load. ");
    }
}
