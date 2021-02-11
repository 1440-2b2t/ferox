package me.olliem5.ferox.api.util.player;

import me.olliem5.ferox.api.traits.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 * @author olliem5
 */

public final class PlayerUtil implements Minecraft {
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }

    public static boolean isMoving(EntityPlayer entityPlayer) {
        return entityPlayer.moveForward != 0.0f || entityPlayer.moveStrafing != 0.0f;
    }

    public static void setSpeed(EntityPlayer entityPlayer, double speed) {
        double[] dir = MotionUtil.forward(speed);

        entityPlayer.motionX = dir[0];
        entityPlayer.motionZ = dir[1];
    }

    public static boolean isInViewFrustrum(BlockPos blockPos) {
        return mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ()), false, true, false) == null;
    }
}