/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.winterbear.wintersk;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.util.EnchantmentType;
import io.github.winterbear.wintersk.AdvancedBan.IsBannedExpression;
import io.github.winterbear.wintersk.AdvancedBan.KickReasonExpression;
import io.github.winterbear.wintersk.CoreProtect.CoreProtectLogBreak;
import io.github.winterbear.wintersk.CoreProtect.CoreProtectLogChat;
import io.github.winterbear.wintersk.CoreProtect.CoreProtectLogInteraction;
import io.github.winterbear.wintersk.CoreProtect.CoreProtectLogPlace;
import io.github.winterbear.wintersk.EnchantedBook.ExprEnchBookWithEnch;
import io.github.winterbear.wintersk.EnchantedBook.ExprEnchantLevelInEnchBook;
import io.github.winterbear.wintersk.EnchantedBook.ExprEnchantsInEnchBook;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WinterBear | winterbear.github.io
 *
 */
public class WinterSk extends JavaPlugin{
    
    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            if (Bukkit.getPluginManager().getPlugin("CoreProtect") != null) {
                Skript.registerEffect(CoreProtectLogChat.class, "coreprotectlog chat %string% [from] %player%");
                Skript.registerEffect(CoreProtectLogInteraction.class, "coreprotectlog interaction [at] %location% [by] %player%");
                Skript.registerEffect(CoreProtectLogBreak.class, "coreprotectlog break %block% [at] %location% [by] %player%");
                Skript.registerEffect(CoreProtectLogPlace.class, "coreprotectlog place %block% [at] %location% [by] %player%");
            } else {
                    Bukkit.getLogger().info("WinterSk: CoreProtect not found... not registering hooks");
            }
            Skript.registerExpression(ExprEnchBookWithEnch.class,ItemStack.class, ExpressionType.PROPERTY,"%itemstack% containing %enchantmenttypes%");
            Skript.registerExpression(ExprEnchantLevelInEnchBook.class,Integer.class,ExpressionType.PROPERTY,"level of %enchantmenttype% within %itemstack%");
            Skript.registerExpression(ExprEnchantsInEnchBook.class,EnchantmentType.class,ExpressionType.PROPERTY,"enchants within %itemstack%");
            Bukkit.getLogger().info("WinterSk: Loaded successfully!");
        } else {
            Bukkit.getLogger().info("WinterSk: Failed to load - Skript is not installed!");
        }
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
        
    }
    
}
