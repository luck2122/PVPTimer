package de.luck212.pvpplugin.timer;

import de.luck212.pvpplugin.main.PVPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SafeTimer {

    private boolean running;
    private int time = 0;
    private BukkitTask myTask;
    private final PVPPlugin plugin;

    public SafeTimer(PVPPlugin plugin){
        this.plugin = plugin;
        this.running = false;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void startRunning(){
        setRunning(true);
        run();
    }

    public void stopRunning(){
        setRunning(false);
        this.myTask.cancel();
    }


    private void run() {
        myTask = new BukkitRunnable() {
            @Override
            public void run() {

                if(!isRunning())
                    return;
                switch (time){
                    case 60: case 45: case 30: case 15: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(ChatColor.GRAY + "Die Schutzzeit endet in " + ChatColor.GOLD + time + ChatColor.GRAY + " sekunden");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(ChatColor.GRAY + "Die Schutzzeit endet in " + ChatColor.GOLD + time + ChatColor.GRAY + " sekunde");
                        break;
                    case 0:
                        plugin.getTimer().startRunning();

                        stopRunning();
                        break;
                    default:
                        break;
                }
                time--;
            }
        }.runTaskTimer(plugin, 20, 20);
    }
}