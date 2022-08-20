package pl.jacobtm.connecthealth.command;

import eu.okaeri.commands.annotation.Command;
import eu.okaeri.commands.annotation.Executor;
import eu.okaeri.commands.bukkit.annotation.Async;
import eu.okaeri.commands.bukkit.annotation.Permission;
import eu.okaeri.commands.bukkit.annotation.Sender;
import eu.okaeri.commands.bukkit.annotation.Sync;
import eu.okaeri.commands.service.CommandService;
import eu.okaeri.i18n.message.Message;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import org.bukkit.entity.Player;
import pl.jacobtm.connecthealth.config.LocaleFile;

@Async
@Command(label="fly")
@Permission("connecthealth.command.fly")
public class FlyCommand implements CommandService {

    private @Inject BI18n i18n;
    private @Inject LocaleFile messages;

    @Sync
    @Executor(description = "Fly.")
    public Message __(@Sender Player player) {
        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
        return this.i18n.get(player, this.messages.getCommandFlySuccess())
                .with("fly", "on");
    } else
        player.setAllowFlight(false);
        return this.i18n.get(player, this.messages.getCommandFlySuccess())
                .with("fly", "off");
    }
}
