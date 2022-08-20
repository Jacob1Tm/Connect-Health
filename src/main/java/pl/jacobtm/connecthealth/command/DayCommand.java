package pl.jacobtm.connecthealth.command;

import eu.okaeri.commands.annotation.Command;
import eu.okaeri.commands.annotation.Executor;
import eu.okaeri.commands.annotation.Label;
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
@Command(label = "day", aliases = "night")
@Permission("connecthealth.command.time")
public class DayCommand implements CommandService {

    private @Inject BI18n i18n;
    private @Inject LocaleFile messages;

    @Sync
    @Executor(description = "yes.")
    public Message __(@Label String label, @Sender Player player) {

        if (label.equals("day")) {
            player.getWorld().setTime(0);
            return this.i18n.get(player, this.messages.getCommandTimeSuccess())
                    .with("time", "day");
        } else if (label.equals("night")) {
            player.getWorld().setTime(13000);
            return this.i18n.get(player, this.messages.getCommandTimeSuccess())
                    .with("time", "night");
        } else return this.i18n.get(player, this.messages.getCommandAnyError());
    }
}