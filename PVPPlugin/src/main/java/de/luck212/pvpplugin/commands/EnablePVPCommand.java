package de.luck212.pvpplugin.commands;

import de.luck212.pvpplugin.main.PVPPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnablePVPCommand implements CommandExecutor {

    private final PVPPlugin plugin;

    public EnablePVPCommand(final PVPPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(plugin.getPVPIsActive()){
            plugin.setPvpIsActive(false);
            player.sendMessage(ChatColor.RED + "PVP wurde deaktiviert");
            plugin.getSafeTimer().stopRunning();
            plugin.getTimer().setRunning(false);
        }else{
            player.sendMessage(ChatColor.GREEN + "PVP wurde aktiviert");
            plugin.getSafeTimer().startRunning();
        }
        return false;
    }
}
