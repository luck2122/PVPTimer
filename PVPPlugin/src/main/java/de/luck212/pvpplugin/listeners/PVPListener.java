package de.luck212.pvpplugin.listeners;

import de.luck212.pvpplugin.main.PVPPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPListener implements Listener {

    private final PVPPlugin plugin;

    public PVPListener(PVPPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerDamage(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = ((Player) event.getEntity()).getPlayer();
        if(player.isOp()) return;
        if(!plugin.getPVPIsActive())
            event.setCancelled(true);
    }
}