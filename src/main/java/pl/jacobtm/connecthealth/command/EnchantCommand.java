package pl.jacobtm.connecthealth.command;

import eu.okaeri.commands.annotation.Arg;
import eu.okaeri.commands.annotation.Command;
import eu.okaeri.commands.annotation.Executor;
import eu.okaeri.commands.bukkit.annotation.Async;
import eu.okaeri.commands.bukkit.annotation.Permission;
import eu.okaeri.commands.bukkit.annotation.Sender;
import eu.okaeri.commands.bukkit.annotation.Sync;
import eu.okaeri.commands.service.CommandService;
import eu.okaeri.commands.service.Option;
import eu.okaeri.i18n.message.Message;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.i18n.BI18n;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.jacobtm.connecthealth.config.LocaleFile;

@Async
@Command(label = "enchant")
@Permission("connecthealth.command.enchant")
public class EnchantCommand implements CommandService {

    private @Inject BI18n i18n;
    private @Inject LocaleFile messages;

    @Sync
    @Executor(description = "Enchant a player's item above default limit")
    public Message __(@Sender Player player, @Arg Enchantment enchantment, @Arg Option<Integer> level) {

        int levelValue = level.orElse(1);
        ItemStack mainHand = player.getInventory().getItemInMainHand();

        if (mainHand.getType().isAir()) {
            return this.i18n.get(player, this.messages.getCommandEnchantError());
        }

        mainHand.addUnsafeEnchantment(enchantment, levelValue);
        return this.i18n.get(player, this.messages.getCommandEnchantSuccess())
                .with("enchantment", enchantment.getKey().getKey())
                .with("level", levelValue);
    }
}
