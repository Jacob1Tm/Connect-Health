package pl.jacobtm.connecthealth.command;

import eu.okaeri.commands.annotation.Arg;
import eu.okaeri.commands.annotation.Command;
import eu.okaeri.commands.annotation.Executor;
import eu.okaeri.commands.bukkit.annotation.Async;
import eu.okaeri.commands.bukkit.annotation.Permission;
import eu.okaeri.commands.bukkit.annotation.Sender;
import eu.okaeri.commands.service.CommandService;
import eu.okaeri.i18n.message.Message;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.jacobtm.connecthealth.ConnectHealth;
import pl.jacobtm.connecthealth.config.LocaleFile;

import java.util.HashSet;
import java.util.Set;


@Async
@Command(label = "connectplayers", aliases = "cp")
@Permission("connecthealth.command.connectplayers")
public class ConnectPlayersCommand implements CommandService {

    //when you need to check for players duplicates
    private static <T> boolean checkForDuplicates(T... array)
    {
        Set<T> set = new HashSet<T>();
        for (T e: array)
        {
            if (set.contains(e)) {
                return true;
            }
            if (e != null) {
                set.add(e);
            }
        }
        return false;
    }

    private @Inject BI18n i18n;
    private @Inject LocaleFile messages;

    @Executor (description = "select two players to connect health!")
    public Message mode_2players(@Arg Player player1, @Arg Player player2, @Sender Player player) {

        ConnectHealth.setPlayer1(player1);
        ConnectHealth.setPlayer2(player2);

        Player[] players = {player1, player2};
        if (checkForDuplicates(players)) {
            return this.i18n.get(player, this.messages.getCommandConnectPlayersError());
        }

        ConnectHealth.setGamemode("2players");

        Message message1 = this.i18n.get(player1, this.messages.getCommandConnectPlayersTitle()).with("player", player2.getName());
        Message message2 = this.i18n.get(player2, this.messages.getCommandConnectPlayersTitle()).with("player", player1.getName());
        player1.sendMessage(message1.apply());
        player1.playSound(player1.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        player2.sendMessage(message2.apply());
        player2.playSound(player2.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        return this.i18n.get(player, this.messages.getCommandConnectPlayersSuccess());
    }

    @Executor (description = "select three players to connect health!")
    public Message mode_3players(@Arg Player player1, @Arg Player player2, @Arg Player player3 , @Sender Player player) {

        ConnectHealth.setPlayer1(player1);
        ConnectHealth.setPlayer2(player2);
        ConnectHealth.setPlayer3(player3);
        Player[] players = {player1, player2, player3};
        if (checkForDuplicates(players)) {
            return this.i18n.get(player, this.messages.getCommandConnectPlayersError());
        }
        ConnectHealth.setGamemode("3players");

        Message message1 = this.i18n.get(player1, this.messages.getCommandConnectPlayersTitle3p())
                .with("player", player2.getName())
                .with("player2", player3.getName());
        Message message2 = this.i18n.get(player2, this.messages.getCommandConnectPlayersTitle3p())
                .with("player", player1.getName())
                .with("player2", player3.getName());
        Message message3 = this.i18n.get(player3, this.messages.getCommandConnectPlayersTitle3p())
                .with("player", player1.getName())
                .with("player2", player2.getName());

        player1.sendMessage(message1.apply());
        player1.playSound(player1.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        player2.sendMessage(message2.apply());
        player2.playSound(player2.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        player3.sendMessage(message3.apply());
        player3.playSound(player3.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        return this.i18n.get(player, this.messages.getCommandConnectPlayersSuccess());
    }

    @Executor (description = "select four players to connect health!")
    public Message mode_4players(@Arg Player player1, @Arg Player player2, @Arg Player player3, @Arg Player player4 , @Sender Player player) {

        ConnectHealth.setPlayer1(player1);
        ConnectHealth.setPlayer2(player2);
        ConnectHealth.setPlayer3(player3);
        ConnectHealth.setPlayer4(player4);

        Player[] players = {player1, player2, player3, player4};
        if (checkForDuplicates(players)) {
            return this.i18n.get(player, this.messages.getCommandConnectPlayersError());
        }
        ConnectHealth.setGamemode("4players");

        Message message1 = this.i18n.get(player1, this.messages.getCommandConnectPlayersTitle4p())
                .with("player", player2.getName())
                .with("player2", player3.getName())
                .with("player3", player4.getName());
        Message message2 = this.i18n.get(player2, this.messages.getCommandConnectPlayersTitle4p())
                .with("player", player1.getName())
                .with("player2", player3.getName())
                .with("player3", player4.getName());
        Message message3 = this.i18n.get(player3, this.messages.getCommandConnectPlayersTitle4p())
                .with("player", player1.getName())
                .with("player2", player2.getName())
                .with("player3", player4.getName());
        Message message4 = this.i18n.get(player4, this.messages.getCommandConnectPlayersTitle4p())
                .with("player", player1.getName())
                .with("player2", player2.getName())
                .with("player3", player3.getName());

        player1.sendMessage(message1.apply());
        player1.playSound(player1.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        player2.sendMessage(message2.apply());
        player2.playSound(player2.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        player3.sendMessage(message3.apply());
        player3.playSound(player3.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        player4.sendMessage(message4.apply());
        player4.playSound(player4.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

        return this.i18n.get(player, this.messages.getCommandConnectPlayersSuccess());
    }

    @Executor(description = "disables health connection")
    public Message disable(@Sender Player player, @Arg boolean enabled) {

        ConnectHealth.setGamemode("disabled");
        return this.i18n.get(player, this.messages.getCommandConnectPlayersEnabledFalse());
    }
}
