/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.winterbear.wintersk;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 *
 * @author WinterBear | winterbear.github.io
 */
public class CoreProtectLogInteraction extends CoreProtectEffect{
    
    Expression<Player> player;
    Expression<Location> location;

    @Override
    protected void execute(Event event) {
        getCoreProtect().logInteraction(player.getSingle(event).getName(), location.getSingle(event));
    }

    @Override
    public String toString(Event event, boolean bln) {
        return "Logs a coreprotect interaction";
    }

    @Override
    public boolean init(Expression<?>[] exprsns, int i, Kleenean kln, SkriptParser.ParseResult pr) {
        location = (Expression<Location>) exprsns[0];
        player = (Expression<Player>) exprsns[1];
        return true;
    }
    
}
