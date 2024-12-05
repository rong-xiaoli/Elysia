package top.rongxiaoli.plugins.PokeReact.backend;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.utils.MiraiLogger;

import java.security.SecureRandom;

public class RandomReact {
    private static final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(RandomReact.class, "ArisuBot.PokeReact.RandomReact");
    public static void process(CommandContext context) {
        SecureRandom random = new SecureRandom();
        int reactBranch = random.nextInt(1,4);
        switch (reactBranch) {
            default:
                LOGGER.warning("Expected react branch ranging from 1 to 4, got " + reactBranch);
            case 1:
                PokeBack.React(context);
                break;
            case 2:

        }
    }
}
