package de.luck212.pvpplugin.timer;

import de.luck212.pvpplugin.main.PVPPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {

    private boolean running;
    private int seconds, minutes, hours;
    private final PVPPlugin plugin;

    public Timer(PVPPlugin plugin){
        this.running = false;
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
        this.plugin = plugin;

    }

    public void startRunning(){
        plugin.setPvpIsActive(true);
        setRunning(true);
        run();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int time) {
        this.seconds = time;
    }

    public int getMinutes(){
        return this.minutes;
    }

    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    public int getHours(){
        return this.hours;
    }

    public void setHours(int hours){
        this.hours = hours;
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!isRunning()) {
                player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Timer ist pausiert"));
                continue;
            }

            player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW.toString() + ChatColor.BOLD + getHours() + ":" + getMinutes() + ":" + getSeconds()));
        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                sendActionBar();

                if (!isRunning()) {
                    return;
                }
                if(seconds/60 == 1){
                    setSeconds(0);
                    setMinutes(getMinutes() + 1);
                }else if(minutes/60 == 1){
                    setMinutes(0);
                    setSeconds(0);
                    setHours(getHours() + 1);
                }else
                    setSeconds(getSeconds() + 1);

            }
        }.runTaskTimer(plugin, 20, 20);
    }
}
