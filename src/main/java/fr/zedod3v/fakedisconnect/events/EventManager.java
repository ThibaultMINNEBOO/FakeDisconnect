package fr.zedod3v.fakedisconnect.events;

import fr.zedod3v.fakedisconnect.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventManager implements Listener {

    private Main main;
    private final HashMap<Player, Boolean> vanishes;

    public EventManager(Main main) {
        this.main = main;
        this.vanishes = new HashMap<Player, Boolean>();
    }

    @EventHandler
    public void onCommandEvent(PlayerCommandPreprocessEvent event) {
        String permission = main.getConfig().getString("vanish_permission");
        List<String> commands = main.getConfig().getStringList("vanish_commands");

        if (event.getPlayer().hasPermission(permission)) {
            for (String command : commands) {
                if (event.getMessage().equalsIgnoreCase("/" + command)) {
                    if (vanishes.get(event.getPlayer()) == null) {
                        Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " left the game");
                        vanishes.put(event.getPlayer(), true);
                    } else {
                        Bukkit.broadcastMessage(ChatColor.YELLOW + event.getPlayer().getName() + " joined the game");
                        vanishes.remove(event.getPlayer());
                    }
                }
            }
        }
    }

}
