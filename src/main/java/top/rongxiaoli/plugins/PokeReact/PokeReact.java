package top.rongxiaoli.plugins.PokeReact;

import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.Commands.ArisuBotAbstractSimpleCommand;

public class PokeReact extends ArisuBotAbstractSimpleCommand {
    public PokeReact() {
        super(ArisuBot.INSTANCE, "poke", "戳一戳");
        setPrefixOptional(false);
    }

    @Override
    public void onCommand() {

    }
}
