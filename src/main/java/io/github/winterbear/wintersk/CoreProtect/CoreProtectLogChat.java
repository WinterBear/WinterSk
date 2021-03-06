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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 *
 * @author WinterBear | winterbear.github.io
 */
public class CoreProtectLogChat extends CoreProtectEffect {
    
    private Expression<Player> player;
    private Expression<String> message;

    @Override
    protected void execute(Event event) {
        getCoreProtect().logChat(player.getSingle(event), message.getSingle(event));
    }

    @Override
    public String toString(Event event, boolean bln) {
        return "Logs a message in CoreProtect";
    }

    @Override
    public boolean init(Expression<?>[] exprsns, int i, Kleenean kln, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) exprsns[1];
        message = (Expression<String>) exprsns[0];
        return true;
    }
    
    
    
    
}
