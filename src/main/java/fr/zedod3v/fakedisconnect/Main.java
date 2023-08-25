package fr.zedod3v.fakedisconnect;

import fr.zedod3v.fakedisconnect.events.EventManager;
import fr.zedod3v.fakedisconnect.utils.commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[FakeDisconnect] : Running...");
        getServer().getPluginManager().registerEvents(new EventManager(this), this);

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), command);
    }
}
