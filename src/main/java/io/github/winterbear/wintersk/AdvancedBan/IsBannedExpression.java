package io.github.winterbear.wintersk.AdvancedBan;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.leoko.advancedban.manager.PunishmentManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by WinterBear on 28/01/2017.
 */
public class IsBannedExpression extends SimpleExpression<Boolean> {
    private Expression<Player> player;

    @Override
    protected Boolean[] get(Event event) {
        Boolean[] banStatus = new Boolean[1];
        banStatus[0] = PunishmentManager.get().isBanned(player.getSingle(event).getUniqueId().toString());
        return banStatus;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Ban Status";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        return true;
    }
}
