package me.olliem5.ferox.impl.events;

import me.olliem5.ferox.api.event.Event;
import net.minecraft.entity.MoverType;

/**
 * @author olliem5
 */

public final class PlayerMoveEvent extends Event {
    private MoverType type;
    private double x;
    private double y;
    private double z;

    public PlayerMoveEvent(MoverType type, double x, double y, double z) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MoverType getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}