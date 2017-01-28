/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.winterbear.wintersk.CoreProtect;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.winterbear.wintersk.CoreProtect.CoreProtectEffect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 *
 * @author WinterBear | winterbear.github.io
 */
public class CoreProtectLogBreak extends CoreProtectEffect {

    private Expression<Player> player;
    private Expression<Location> location;
    private Expression<Block> block;
    
    @Override
    protected void execute(Event event) {
        getCoreProtect().logRemoval(player.getSingle(event).getName(), location.getSingle(event), block.getSingle(event).getType(), block.getSingle(event).getData());
    }

    @Override
    public String toString(Event event, boolean bln) {
        return "Logs a break event to coreprotect";
    }

    @Override
    public boolean init(Expression<?>[] exprsns, int i, Kleenean kln, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) exprsns[0];
        location = (Expression<Location>) exprsns[1];
        player = (Expression<Player>) exprsns[2];
        return true;
        
    }
    
}
