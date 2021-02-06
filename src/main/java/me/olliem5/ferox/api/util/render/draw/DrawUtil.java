package me.olliem5.ferox.api.util.render.draw;

import me.olliem5.ferox.api.traits.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public final class DrawUtil implements Minecraft {
    public static void gradient(int minX, int minY, int maxX, int maxY, int startColor, int endColor, boolean left) {
        if (left) {
            float startA = (startColor >> 24 & 0xFF) / 255.0f;
            float startR = (startColor >> 16 & 0xFF) / 255.0f;
            float startG = (startColor >> 8 & 0xFF) / 255.0f;
            float startB = (startColor & 0xFF) / 255.0f;
            float endA = (endColor >> 24 & 0xFF) / 255.0f;
            float endR = (endColor >> 16 & 0xFF) / 255.0f;
            float endG = (endColor >> 8 & 0xFF) / 255.0f;
            float endB = (endColor & 0xFF) / 255.0f;

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glBegin(GL11.GL_POLYGON);
            GL11.glColor4f(startR, startG, startB, startA);
            GL11.glVertex2f(minX, minY);
            GL11.glVertex2f(minX, maxY);
            GL11.glColor4f(endR, endG, endB, endA);
            GL11.glVertex2f(maxX, maxY);
            GL11.glVertex2f(maxX, minY);
            GL11.glEnd();
            GL11.glShadeModel(GL11.GL_FLAT);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        } else {
            drawGradientRect(minX, minY, maxX, maxY, startColor, endColor);
        }
    }

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(right, top, 0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(left, top, 0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(left, bottom, 0).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos(right, bottom, 0).color(f5, f6, f7, f4).endVertex();

        tessellator.draw();

        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawLeftGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(right, top, 0).color(f4, f5, f6, f4).endVertex();
        bufferbuilder.pos(left, top, 0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(left, bottom, 0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(right, bottom, 0).color(f4, f5, f6, f4).endVertex();

        tessellator.draw();

        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawPickerBase(int pickerX, int pickerY, int pickerWidth, int pickerHeight, float red, float green, float blue, float alpha) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glVertex2f(pickerX, pickerY);
        GL11.glVertex2f(pickerX, pickerY + pickerHeight);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glVertex2f(pickerX + pickerWidth, pickerY + pickerHeight);
        GL11.glVertex2f(pickerX + pickerWidth, pickerY);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f(pickerX, pickerY);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(pickerX, pickerY + pickerHeight);
        GL11.glVertex2f(pickerX + pickerWidth, pickerY + pickerHeight);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f(pickerX + pickerWidth, pickerY);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase entityLivingBase) {
        RenderManager renderManager = mc.getRenderManager();

        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);

        net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();

        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float) Math.atan(mouseY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0.0F, 0.0F, 0.0F);

        renderManager.setPlayerViewY(180.0F);
        renderManager.setRenderShadow(false);
        renderManager.renderEntity(entityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        renderManager.setRenderShadow(true);

        GlStateManager.popMatrix();

        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();

        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}