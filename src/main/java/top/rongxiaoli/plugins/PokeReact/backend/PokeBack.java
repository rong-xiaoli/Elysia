package top.rongxiaoli.plugins.PokeReact.backend;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.utils.MiraiLogger;

import java.util.Objects;
import java.util.Random;

public class PokeBack {
    MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(PokeBack.class, "ArisuBot.PokeReact.PokeBack");
    private static String getRandomString() {
        String out;
        String[] preset = {
                "看我戳回去！",
                "就你会戳吗？",
                "别戳啦x_x",
                "干什么！",
        };
        Random random = new Random(System.currentTimeMillis());
        out = preset[random.nextInt(4)];
        return out;
    }
    public static void React(CommandContext context) {
        String messageString = getRandomString();
        context.getSender().sendMessage(messageString);
        Objects.requireNonNull(context.getSender().getUser()).nudge();
    }
}
