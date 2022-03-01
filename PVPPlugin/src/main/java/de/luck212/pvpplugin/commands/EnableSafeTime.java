package de.luck212.pvpplugin.commands;

import de.luck212.pvpplugin.main.PVPPlugin;
import de.luck212.pvpplugin.timer.SafeTimer;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Console;

public class EnableSafeTime implements CommandExecutor {

    private final PVPPlugin plugin;
    private int time;

    public EnableSafeTime(final PVPPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = ((Player) sender).getPlayer();
        if(args.length == 0){
            player.sendMessage(ChatColor.RED + "Bitte benutze /schutzzeit <ZEIT>");
            return false;
        }else if(args.length == 1){
            try {
                time = Integer.parseInt(args[0]);
                plugin.getSafeTimer().setTime(time);
                player.sendMessage(ChatColor.GRAY + "Die Schutzzeit beträgt: " + ChatColor.GOLD + time + ChatColor.GRAY + " sekunden");
            }catch (Exception e){
                e.printStackTrace();
                player.sendMessage(ChatColor.RED + "Keine gültige Zeit angegeben");
                return false;
            }
        }else
            player.sendMessage(ChatColor.RED + "Bitte benutze /schutzzeit <ZEIT>");

        return false;
    }
}