package de.luck212.pvpplugin.main;

import de.luck212.pvpplugin.commands.EnablePVPCommand;
import de.luck212.pvpplugin.commands.EnableSafeTime;
import de.luck212.pvpplugin.listeners.PVPListener;
import de.luck212.pvpplugin.timer.SafeTimer;
import de.luck212.pvpplugin.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PVPPlugin extends JavaPlugin {

    private boolean pvpIsActive = false;
    private Timer timer;
    private SafeTimer safeTimer;
    private final PluginManager pluginManager = Bukkit.getPluginManager();
    private boolean safeTime = false;

    @Override
    public void onEnable() {
        this.timer = new Timer(this);
        this.safeTimer = new SafeTimer(this);
        pluginManager.registerEvents(new PVPListener(this), this);
        getCommand("pvp").setExecutor(new EnablePVPCommand(this));
        getCommand("schutzzeit").setExecutor(new EnableSafeTime(this));

    }

    @Override
    public void onDisable() {

    }

    public SafeTimer getSafeTimer(){
        return this.safeTimer;
    }

    public void setSafeTimer(SafeTimer safeTimer){
        this.safeTimer = safeTimer;
    }

    public Timer getTimer(){
        return this.timer;
    }

    public boolean getSafeTime(){
        return this.safeTime;
    }

    public void setSafeTime(boolean safeTime){
        this.safeTime = safeTime;
    }

    public boolean getPVPIsActive(){
        return pvpIsActive;
    }

    public void setPvpIsActive(final boolean pvpIsActive){
        this.pvpIsActive = pvpIsActive;
    }
}
