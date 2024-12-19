package top.rongxiaoli.plugins.PokeReact;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.utils.MiraiLogger;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.Commands.ArisuBotAbstractSimpleCommand;
import top.rongxiaoli.plugins.PokeReact.backend.PokeReactTextConfig;

import java.security.SecureRandom;
import java.util.Objects;

public class PokeReact extends ArisuBotAbstractSimpleCommand {
    public static final PokeReact INSTANCE = new PokeReact();
    private final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(PokeReact.class, "ArisuBot.PokeReact");
    public PokeReact() {
        super(ArisuBot.INSTANCE, "poke", "戳一戳");
        setPrefixOptional(false);
    }
    @Override
    public void load() {
        LOGGER.debug("PokeReact loading. ");
        LOGGER.debug("PokeReact loaded. ");
    }
    @Handler
    public void onCommand(CommandContext context) {
        if (isConsoleCalling(context)) return;
        int branch;
        SecureRandom random = new SecureRandom();
        random.setSeed(random.generateSeed(8));
        branch = random.nextInt(1,2);
        LOGGER.verbose(String.valueOf(branch));
        switch (branch) {
            default:
                LOGGER.warning("Unexpected value " + random + " received, range 1 to 4. ");
            case 1:
                PokeBack p = new PokeBack();
                p.React(context);
                break;
            case 2:
                SayRandom s = new SayRandom();
                s.React(context);
                break;
        }
    }
    public void onNudgeEvent(NudgeEvent event) {
        if (event.getTarget().getId() != event.getBot().getId()) return;
        int branch;
        SecureRandom random = new SecureRandom();
        random.setSeed(random.generateSeed(8));
        branch = random.nextInt(1,2);
        LOGGER.verbose(String.valueOf(branch));
        switch (branch) {
            default:
                LOGGER.warning("Unexpected value " + random + " received, range 1 to 4. ");
            case 1:
                PokeBack p = new PokeBack();
                p.React(event);
                break;
            case 2:
                SayRandom s = new SayRandom();
                s.React(event);
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
    @Override
    public void reload() {
        LOGGER.debug("PokeReact data reloading. ");
        LOGGER.verbose("Nothing stored, pass. ");
        PokeReactTextConfig.INSTANCE.reload();
        LOGGER.debug("PokeReact reload done. ");
    }

    @Override
    public void shutdown() {
        LOGGER.debug("PokeReact shutting down. ");
        LOGGER.verbose("Nothing stored, pass. ");
        LOGGER.debug("PokeReact shut down. ");
    }

    @Override
    public void saveData() {
        LOGGER.debug("PokeReact data saving. ");
        LOGGER.verbose("Nothing stored, pass. ");
        PokeReactTextConfig.INSTANCE.saveData();
        LOGGER.debug("PokeReact data saved. ");
    }

    @Override
    public void reloadData() {
        LOGGER.debug("PokeReact data reloading. ");
        LOGGER.verbose("Nothing stored, pass. ");
        PokeReactTextConfig.INSTANCE.reload();
        LOGGER.debug("PokeReact reload done. ");
    }
}
