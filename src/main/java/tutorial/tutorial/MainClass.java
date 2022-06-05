package tutorial.tutorial;

import org.bukkit.plugin.java.JavaPlugin;
import tutorial.tutorial.EPS4.StaffChat;

public final class MainClass extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("staffchat").setExecutor(new StaffChat());
        getServer().getPluginManager().registerEvents(new StaffChat(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // lalu add command di plugin.yml
}
