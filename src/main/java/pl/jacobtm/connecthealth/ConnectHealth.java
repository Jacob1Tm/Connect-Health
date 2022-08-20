package pl.jacobtm.connecthealth;

import eu.okaeri.commands.bukkit.annotation.Async;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

@Component
public class ConnectHealth implements Listener {

    private static Player p1 = null;
    private static Player p2 = null;
    private static boolean Enabled = false;

    public static void getPlayer1(Player player1) {
        p1 = player1;
    }

    public static void getPlayer2(Player player2) {
        p2 = player2;
    }

    public static void getEnabled(boolean enabled) {
        Enabled = enabled;
    }

    @Async
    @EventHandler
    public void onRegainHealth(EntityRegainHealthEvent event) {
        if (!Enabled) return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null) return;
            Player player = (Player) event.getEntity();;
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() + event.getAmount());
                if (p1h > 20) p1h = 20;
                p2.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p1.getHealth() + event.getAmount());
                if (p2h > 20) p2h = 20;
                p1.setHealth(p2h);
            }
        }
    }
    @Async
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!Enabled) return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() - event.getFinalDamage());
                p2.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p1.getHealth() - event.getFinalDamage());
                p1.setHealth(p2h);
            }
        }
    }
    @Async
    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (!Enabled) return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                if (p2.isDead()) return;
                p2.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
            } else if (player.equals(p2)) {
                if (p1.isDead()) return;
                p1.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
            }
        }
    }

}
