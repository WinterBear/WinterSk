package io.github.winterbear.wintersk.Mobs;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Event;

import javax.naming.ldap.ExtendedRequest;

/**
 * Created by WinterBear on 28/01/2017.
 */
public class OwnerOfTameableMobExpression extends SimpleExpression<Player> {
    private Expression<Entity> tamedEntity;

    @Override
    protected Player[] get(Event event) {
        if(tamedEntity.getSingle(event) instanceof Tameable) {
            return new Player[]{Bukkit.getPlayer(((Tameable)tamedEntity.getSingle(event)).getOwner().getUniqueId())};
        } else {
            return null;
        }
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Owner of Tamed Entity";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.tamedEntity = (Expression<Entity>) expressions[0];
        return true;
    }
}
