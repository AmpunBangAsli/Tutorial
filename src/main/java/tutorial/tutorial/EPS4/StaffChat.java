package tutorial.tutorial.EPS4;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class StaffChat implements CommandExecutor, Listener {
    // membuat toggle
    public ArrayList<Player> stafftoggle = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // cek command
        if (command.getName().equalsIgnoreCase("staffchat")) {
            // cek kalau sender itu bukan player
            if (!(sender instanceof Player)){
                sender.sendMessage("Only player can execute this command.");
                return true;
            }

            // cek permission/izin
            Player player = (Player) sender;

            if (!player.hasPermission("staffchat")) {
                // kirim error message karena player tidak punya permission
                player.sendMessage("You dont have permission!");
                return true;
            }
            // message
            String message = "";
            for (String part : args) {
                if (message != "") message += " ";
                message += part;
            }


            // loop
            for (Player all : Bukkit.getOnlinePlayers()) {
                // cek permission semua player
                if (all.hasPermission("staffchat.read")) {
                    all.sendMessage("[STAFFCHAT] " + player.getName() + ": " + message);
                }
            }


            // cek args 2
            if (args[0].equalsIgnoreCase("$toggle")) {
                if (!stafftoggle.contains(player)) {
                    stafftoggle.add(player);
                    player.sendMessage("Toggle: on");
                } else {
                    player.sendMessage("Toggle: off");
                    stafftoggle.remove(player);
                }
            }




        }


        return true;
    }

    // Event
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        // cek kalau player ada di list/ cek player jika togglenya nyala




        Player player = event.getPlayer();
        if (stafftoggle.contains(event.getPlayer())) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                // cek permission semua player
                if (all.hasPermission("staffchat.read")) {
                    all.sendMessage("[STAFFCHAT] " + player.getName() + ": " + event.getMessage());
                }
            }
        }
    }
    // dengan begini plugin ini sudah work
    // jika ingin mengirim message tanpa toggle gunakanlah /staffchat <message>
    // jika ingin menggunakan togglenya gunakanlah /staffchat $toggle
    // jangan lupa add commandlistener sama eventlistener di main class


}
