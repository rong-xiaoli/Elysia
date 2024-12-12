package top.rongxiaoli.plugins.Broadcast;

import net.mamoe.mirai.console.command.CommandContext;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.Commands.ArisuBotAbstractSimpleCommand;

public class Broadcast extends ArisuBotAbstractSimpleCommand {
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
        StringBuilder builder = new StringBuilder();
    }
}
