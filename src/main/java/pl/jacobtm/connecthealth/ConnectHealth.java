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

    private static Player p3 = null;

    private static Player p4 = null;
    private static String mode = null;

    public static void setPlayer1(Player player1) {
        p1 = player1;
    }

    public static void setPlayer2(Player player2) {
        p2 = player2;
    }

    public static void setPlayer3(Player player3) {
        p3 = player3;
    }

    public static void setPlayer4(Player player4) {
        p4 = player4;
    }
    public static void setGamemode(String gamemode) {
        mode = gamemode;
    }

        //2 players mode
        @Async
        @EventHandler
        public void onRegainHealth2p (EntityRegainHealthEvent event){
        if (mode != "2players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() + event.getAmount());
                if (p1h > 20) p1h = 20;
                p2.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p2.getHealth() + event.getAmount());
                if (p2h > 20) p2h = 20;
                p1.setHealth(p2h);
            }
        }
    }
        @Async
        @EventHandler
        public void onDamage2p (EntityDamageEvent event){
        if (mode != "2players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() - event.getFinalDamage());
                p2.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p2.getHealth() - event.getFinalDamage());
                p1.setHealth(p2h);
            }
        }
    }
        @Async
        @EventHandler
        public void onDeath2p (EntityDeathEvent event){
        if (mode != "2players") return;
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


    //3 players mode
    @Async
    @EventHandler
    public void onRegainHealth3p (EntityRegainHealthEvent event){
        if (mode != "3players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null || p3 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() + event.getAmount());
                if (p1h > 20) p1h = 20;
                p2.setHealth(p1h);
                p3.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p2.getHealth() + event.getAmount());
                if (p2h > 20) p2h = 20;
                p1.setHealth(p2h);
                p3.setHealth(p2h);
            } else if (player.equals(p3)) {
                double p3h = Math.abs(p3.getHealth() + event.getAmount());
                if (p3h > 20) p3h = 20;
                p1.setHealth(p3h);
                p3.setHealth(p3h);
            }
        }
    }
    @Async
    @EventHandler
    public void onDamage3p (EntityDamageEvent event){
        if (mode != "3players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null || p3 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() - event.getFinalDamage());
                p2.setHealth(p1h);
                p3.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p2.getHealth() - event.getFinalDamage());
                p1.setHealth(p2h);
                p3.setHealth(p2h);
            } else if (player.equals(p3)) {
                double p3h = Math.abs(p3.getHealth() - event.getFinalDamage());
                p1.setHealth(p3h);
                p2.setHealth(p3h);
            }
        }
    }
    @Async
    @EventHandler
    public void onDeath3p (EntityDeathEvent event){
        if (mode != "3players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null || p3 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                if (p2.isDead() || p3.isDead()) return;
                p2.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
                p3.spigot().respawn();
            } else if (player.equals(p2)) {
                if (p1.isDead() || p3.isDead()) return;
                p1.setHealth(0);
                p3.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
                p3.spigot().respawn();
            } else if (player.equals(p3)) {
                if (p1.isDead() || p2.isDead()) return;
                p1.setHealth(0);
                p2.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
                p3.spigot().respawn();
            }
        }
    }

    //4 players mode
    @Async
    @EventHandler
    public void onRegainHealth4p (EntityRegainHealthEvent event){
        if (mode != "4players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null || p3 == null || p4 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() + event.getAmount());
                if (p1h > 20) p1h = 20;
                p2.setHealth(p1h);
                p3.setHealth(p1h);
                p4.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p2.getHealth() + event.getAmount());
                if (p2h > 20) p2h = 20;
                p1.setHealth(p2h);
                p3.setHealth(p2h);
                p4.setHealth(p2h);
            } else if (player.equals(p3)) {
                double p3h = Math.abs(p3.getHealth() + event.getAmount());
                if (p3h > 20) p3h = 20;
                p1.setHealth(p3h);
                p3.setHealth(p3h);
                p4.setHealth(p3h);
            } else if (player.equals(4)) {
                double p4h = Math.abs(p4.getHealth() + event.getAmount());
                p1.setHealth(p4h);
                p2.setHealth(p4h);
                p3.setHealth(p4h);
            }
        }
    }
    @Async
    @EventHandler
    public void onDamage4p (EntityDamageEvent event){
        if (mode != "4players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null || p3 == null || p4 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                double p1h = Math.abs(p1.getHealth() - event.getFinalDamage());
                p2.setHealth(p1h);
                p3.setHealth(p1h);
                p4.setHealth(p1h);
            } else if (player.equals(p2)) {
                double p2h = Math.abs(p2.getHealth() - event.getFinalDamage());
                p1.setHealth(p2h);
                p3.setHealth(p2h);
                p4.setHealth(p2h);
            } else if (player.equals(p3)) {
                double p3h = Math.abs(p3.getHealth() - event.getFinalDamage());
                p1.setHealth(p3h);
                p2.setHealth(p3h);
                p4.setHealth(p3h);
            } else if (player.equals(p4)) {
                double p4h = Math.abs(p4.getHealth() - event.getFinalDamage());
                p1.setHealth(p4h);
                p2.setHealth(p4h);
                p3.setHealth(p4h);
            }
        }
    }
    @Async
    @EventHandler
    public void onDeath4p (EntityDeathEvent event) {
        if (mode != "4players") return;
        if (event.getEntity() instanceof Player) {
            if (p1 == null || p2 == null || p3 == null || p4 == null) return;
            Player player = (Player) event.getEntity();
            if (player.equals(p1)) {
                if (p2.isDead() || p3.isDead() || p4.isDead()) return;
                p2.setHealth(0);
                p3.setHealth(0);
                p4.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
                p3.spigot().respawn();
                p4.spigot().respawn();
            } else if (player.equals(p2)) {
                if (p1.isDead() || p3.isDead() || p4.isDead()) return;
                p1.setHealth(0);
                p3.setHealth(0);
                p4.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
                p3.spigot().respawn();
                p4.spigot().respawn();
            } else if (player.equals(p3)) {
                if (p1.isDead() || p2.isDead() || p4.isDead()) return;
                p1.setHealth(0);
                p2.setHealth(0);
                p4.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
                p3.spigot().respawn();
                p4.spigot().respawn();
            } else if (player.equals(p4)) {
                if (p1.isDead() || p2.isDead() || p3.isDead()) return;
                p1.setHealth(0);
                p2.setHealth(0);
                p4.setHealth(0);
                p1.spigot().respawn();
                p2.spigot().respawn();
                p3.spigot().respawn();
                p4.spigot().respawn();
            }
        }
    }
}
