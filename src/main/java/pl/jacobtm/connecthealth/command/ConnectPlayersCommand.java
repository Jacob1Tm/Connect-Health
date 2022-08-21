package pl.jacobtm.connecthealth.command;

import eu.okaeri.commands.annotation.Arg;
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
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.jacobtm.connecthealth.ConnectHealth;
import pl.jacobtm.connecthealth.config.LocaleFile;


@Async
@Command(label = "connectplayers", aliases = "cp")
@Permission("connecthealth.command.connectplayers")
public class ConnectPlayersCommand implements CommandService {

    private @Inject BI18n i18n;
    private @Inject LocaleFile messages;

    @Sync
    @Executor(description = "Connects players health")
    public Message connect(@Arg Player player1 , @Arg Player player2, @Sender Player player) {
        if (player1 == player2) {
            return this.i18n.get(player, messages.getCommandConnectPlayersError());
        }
        ConnectHealth.setPlayer1(player1);
        ConnectHealth.setPlayer2(player2);
        ConnectHealth.setEnabled(true);
        player1.setHealth(20);
        player2.setHealth(20);
        Message message1 = this.i18n.get(player1, this.messages.getCommandConnectPlayersTitle()).with("player", player2.getName());
        Message message2 = this.i18n.get(player2, this.messages.getCommandConnectPlayersTitle()).with("player", player1.getName());

        player1.sendMessage(message1.apply());
        player1.playSound(player1.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        player2.sendMessage(message2.apply());
        player2.playSound(player2.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        return this.i18n.get(player, this.messages.getCommandConnectPlayersSuccess());
    }
    @Executor (description = "enables or disables health connection")
    public Message enabled(@Sender Player player, @Arg boolean enabled) {
        if(enabled) {
            ConnectHealth.setEnabled(true);
                return this.i18n.get(player, this.messages.getCommandConnectPlayersEnabledTrue());
        } else {
        ConnectHealth.setEnabled(false);
        return this.i18n.get(player, this.messages.getCommandConnectPlayersEnabledFalse());
    }
    }
}
