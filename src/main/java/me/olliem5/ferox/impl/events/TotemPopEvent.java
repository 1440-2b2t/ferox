package me.olliem5.ferox.impl.events;

import me.olliem5.ferox.api.event.Event;
import net.minecraft.entity.Entity;

public class TotemPopEvent extends Event {
    public final Entity entity;

    public TotemPopEvent(Entity entity) {
        this.entity = entity;
    }
}