package io.github.winterbear.wintersk.AdvancedBan;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.events.util.PlayerChatEventHandler;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import javax.annotation.Nullable;

/**
 * Created by WinterBear on 28/01/2017.
 */
public class KickReasonExpression extends SimpleExpression<String> {

    Expression<PlayerKickEvent> kickEvent;

    @SuppressWarnings("unchecked")
    public static enum MessageType {
        KICK("quit", "(quit|leave|log[ ]out|kick)( |-)reason", PlayerKickEvent.class) {
            @Override
            @Nullable
            String get(final Event e) {
                return ((PlayerKickEvent) e).getReason();
            }

            @Override
            void set(final Event e, final String message) {
                ((PlayerKickEvent) e).setLeaveMessage(message);
            }
        };

        final String name;
        private final String pattern;
        final Class<? extends Event>[] events;

        MessageType(final String name, final String pattern, final Class<? extends Event>... events) {
            this.name = name;
            this.pattern = "[the] " + pattern;
            this.events = events;
        }

        public static String[] patterns;
        static {
            patterns = new String[values().length];
            for (int i = 0; i < patterns.length; i++)
                patterns[i] = values()[i].pattern;
        }

        @Nullable
        abstract String get(Event e);

        abstract void set(Event e, String message);

    }

    @SuppressWarnings("null")
    private MessageType type;

    @SuppressWarnings("null")
    @Override
    public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final SkriptParser.ParseResult parseResult) {
        type = MessageType.values()[matchedPattern];
        if (!ScriptLoader.isCurrentEvent(type.events)) {
            Skript.error("The " + type.name + " message can only be used in a " + type.name + " event", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    protected String[] get(final Event e) {
        for (final Class<? extends Event> c : type.events) {
            if (c.isInstance(e))
                return new String[] {type.get(e)};
        }
        return new String[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET)
            return CollectionUtils.array(String.class);
        return null;
    }

    @Override
    public void change(final Event e, final @Nullable Object[] delta, final Changer.ChangeMode mode) {
        assert mode == Changer.ChangeMode.SET;
        assert delta != null;
        for (final Class<? extends Event> c : type.events) {
            if (c.isInstance(e))
                type.set(e, "" + delta[0]);
        }
    }


    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Kick Event Reason";
    }

}
