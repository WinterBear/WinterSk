/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.winterbear.wintersk.CoreProtect;

import ch.njol.skript.lang.Effect;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author WinterBear | winterbear.github.io
 */
public abstract class CoreProtectEffect extends Effect{

    public CoreProtectAPI getCoreProtect() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");
        // Check that CoreProtect is loaded
        if (plugin == null || !(plugin instanceof CoreProtect)) {
            return null;
        }
        // Check that the API is enabled
        CoreProtectAPI CoreProtect = ((CoreProtect)plugin).getAPI();
        if (CoreProtect.isEnabled()==false){
            return null;
        }
        // Check that a compatible version of the API is loaded
        if (CoreProtect.APIVersion() < 4){
            return null;
        }

        return CoreProtect;
    }
}
