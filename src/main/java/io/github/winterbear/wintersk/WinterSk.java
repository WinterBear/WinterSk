/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.winterbear.wintersk;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WinterBear | winterbear.github.io
 */
public class WinterSk extends JavaPlugin{
    
    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            Skript.registerEffect(CoreProtectLogChat.class, "coreprotectlog chat %string% [from] %player%");
            Skript.registerEffect(CoreProtectLogInteraction.class, "coreprotectlog interaction [at] %location% [by] %player%");
            Skript.registerEffect(CoreProtectLogBreak.class, "coreprotectlog break %block% [at] %location% [by] %player%");
            Skript.registerEffect(CoreProtectLogPlace.class, "coreprotectlog place %block% [at] %location% [by] %player%");
            Bukkit.getLogger().info("WinterSk loaded successfully!");
        } else {
            Bukkit.getLogger().info("WinterSk failed to load - Skript is not installed!");
        }
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
        
    }
    
}
