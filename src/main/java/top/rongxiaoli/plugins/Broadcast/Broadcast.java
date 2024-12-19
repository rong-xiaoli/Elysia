package top.rongxiaoli.plugins.Broadcast;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.SingleMessage;
import net.mamoe.mirai.utils.MiraiLogger;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.Commands.ArisuBotAbstractSimpleCommand;

import java.util.Arrays;
import java.util.List;

public class Broadcast extends ArisuBotAbstractSimpleCommand {
    private static final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(Broadcast.class, "ArisuBot.Broadcast");
    public static Broadcast INSTANCE = new Broadcast();
    public Broadcast() {
        super(ArisuBot.INSTANCE, "broadcast", "广播");
        setDescription("向所有人广播消息，请注意一定要配合LuckPerms进行权限管理，该功能可能会造成滥用！");
        setPrefixOptional(false);
    }
    public static final boolean isBroadcasting = false;
    @Handler
    public void onCommand(CommandContext context, String... args) {
        if (isBroadcasting) {
            context.getSender().sendMessage("正在广播，请稍后再试");
            return;
        }
        if (args.length == 0) {
            context.getSender().sendMessage("广播内容为空，请重新发送内容");
            return;
        }
        if (context.getSender().getSubject() == null) {
            LOGGER.warning("Command sender is null. ");
            return;
        }
        MessageChainBuilder mcb = new MessageChainBuilder();
        List<SingleMessage> source = context.getOriginalMessage().subList(0,context.getOriginalMessage().size() - 1);
        source.remove(0);
        SingleMessage firstMessage = source.get(0);
        mcb.append(firstMessage.contentToString());
        source.remove(0);
        for (SingleMessage message :
                source) {
            mcb.append(message);
        }
        context.getSender().sendMessage(mcb.build());
    }
}
