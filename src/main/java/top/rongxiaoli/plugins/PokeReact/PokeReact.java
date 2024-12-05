package top.rongxiaoli.plugins.PokeReact;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.data.java.JavaAutoSavePluginData;
import net.mamoe.mirai.utils.MiraiLogger;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.Commands.ArisuBotAbstractSimpleCommand;
import top.rongxiaoli.plugins.PokeReact.backend.PokeBack;
import top.rongxiaoli.plugins.PokeReact.backend.SayRandom;

import java.security.SecureRandom;
import java.util.Objects;

public class PokeReact extends ArisuBotAbstractSimpleCommand {
    private final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(PokeReact.class, "ArisuBot.PokeReact");
    public PokeReact() {
        super(ArisuBot.INSTANCE, "poke", "戳一戳");
        setPrefixOptional(false);
    }

    @Override
    public void onCommand(CommandContext context) {
        if (isConsoleCalling(context)) return;
        int branch;
        SecureRandom random = new SecureRandom();
        random.setSeed(random.generateSeed(8));
        branch = random.nextInt(1,4);
        switch (branch) {
            default:
                LOGGER.warning("Unexpected value " + random + " received, range 1 to 4. ");
            case 1:
                PokeBack.React(context);
                break;
            case 2:
                SayRandom.React(context);
            case 3:
            case 4:
                break;
        }
    }
    boolean isConsoleCalling(CommandContext context) {
        long userID = 0, subjectID = 0;
        // From console, return:
        try {
            userID = Objects.requireNonNull(context.getSender().getUser()).getId();
            subjectID = Objects.requireNonNull(context.getSender().getSubject()).getId();
        } catch (NullPointerException e) {
            LOGGER.warning("This command cannot be invoked from console! ");
            return true;
        }
        if (userID == 0 || subjectID == 0) {
            LOGGER.warning("This command cannot be invoked from console! ");
            return true;
        }
        return false;
    }
}
