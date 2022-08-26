package pl.jacobtm.connecthealth.config;

import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.i18n.configs.LocaleConfig;
import eu.okaeri.platform.core.annotation.Messages;
import lombok.Getter;

@Getter
@Messages(path = "i18n", suffix = ".yml", defaultLocale = "en", unpack = true)
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class LocaleFile extends LocaleConfig {
    private String commandConnectPlayersSuccess = "Players health is now connected!";
    private String commandConnectPlayersEnabledTrue = "~Health is now connected!";
    private String commandConnectPlayersEnabledFalse = "~Health is no longer connected!";
    private String commandConnectPlayersTitle = "Your health is now connected with {player}!";
    private String commandConnectPlayersTitle3p = "Your health is now connected with {player} and {player2}!";
    private String commandConnectPlayersTitle4p = "Your health is now connected with {player}, {player2} and {player3}!";
    private String commandConnectPlayersError = "Player duplicate found!";
}
