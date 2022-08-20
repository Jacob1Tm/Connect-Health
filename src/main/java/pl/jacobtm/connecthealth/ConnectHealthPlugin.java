package pl.jacobtm.connecthealth;

import eu.okaeri.commands.OkaeriCommands;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import org.bukkit.plugin.Plugin;

@Scan(exclusions = "org.example.okaeriplatformtest.libs", deep = true)
public class ConnectHealthPlugin extends OkaeriBukkitPlugin {

    @Planned(ExecutionPhase.STARTUP) // do not use onEnable (especially without calling super)
    public void onStartup() {
        this.getLogger().info("Enabled!");
    }

    @Planned(ExecutionPhase.POST_SETUP)
    public void setupCommands(OkaeriCommands commands) {
        commands.registerType(new EnchantmentTypeResolver());
    }


    @Planned(ExecutionPhase.SHUTDOWN) // do not use onDisable (especially without calling super)
    public void onShutdown() {
        this.getLogger().info("Disabled!");
    }

    @Bean("testString")
    public String configureTestString(Plugin plugin) {
        return "plugin -> " + plugin.getName();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND, async = true)
    public void runAfterServerIsFullyLoaded() {
        this.getLogger().info("Looks like server is now fully loaded!");
    }
}
