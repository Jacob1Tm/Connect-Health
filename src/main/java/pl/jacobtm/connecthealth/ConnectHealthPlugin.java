package pl.jacobtm.connecthealth;

import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import org.bukkit.plugin.Plugin;

@Scan(exclusions = "org.example.okaeriplatformtest.libs", deep = true)
public class ConnectHealthPlugin extends OkaeriBukkitPlugin {

    @Planned(ExecutionPhase.STARTUP)
    public void onStartup() {
        this.getLogger().info("Enabled!");
    }

    @Planned(ExecutionPhase.SHUTDOWN)
    public void onShutdown() {
        this.getLogger().info("Disabled!");
    }

    @Bean("pluginString")
    public String configurePluginString(Plugin plugin) {
        return "plugin -> " + plugin.getName();
    }
}
