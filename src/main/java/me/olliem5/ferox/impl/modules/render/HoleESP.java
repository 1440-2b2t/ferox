package me.olliem5.ferox.impl.modules.render;

import git.littledraily.eventsystem.Listener;
import me.olliem5.ferox.api.module.Category;
import me.olliem5.ferox.api.module.Module;
import me.olliem5.ferox.api.module.ModuleInfo;
import me.olliem5.ferox.api.setting.NumberSetting;
import me.olliem5.ferox.api.setting.Setting;
import me.olliem5.ferox.api.util.minecraft.BlockUtil;
import me.olliem5.ferox.api.util.minecraft.PlayerUtil;
import me.olliem5.ferox.api.util.module.HoleUtil;
import me.olliem5.ferox.api.util.render.RenderUtil;
import me.olliem5.ferox.impl.events.WorldRenderEvent;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@ModuleInfo(name = "HoleESP", description = "Highlights safe holes for crystal pvp", category = Category.RENDER)
public class HoleESP extends Module {
    public static NumberSetting<Integer> holeRange = new NumberSetting<>("Hole Range", 1, 5, 10, 0);

    public static Setting<Boolean> obsidian = new Setting<>("Obsidian Holes", true);
    public static Setting<Color> obsidianHoleColour = new Setting<>(obsidian, "Obsidian Hole Colour", new Color(222, 38, 38, 178));

    public static Setting<Boolean> bedrock = new Setting<>("Bedrock Holes", true);
    public static Setting<Color> bedrockHoleColour = new Setting<>(bedrock, "Bedrock Hole Colour", new Color(61, 194, 46, 169));

    public static Setting<Boolean> renderSettings = new Setting<>("Render Settings", true);
    public static Setting<RenderModes> renderMode = new Setting<>(renderSettings, "Render Mode", RenderModes.Full);
    public static NumberSetting<Double> boxHeight = new NumberSetting<>(renderSettings, "Box Height", -1.0, 0.0, 2.0, 1);
    public static NumberSetting<Double> outlineHeight = new NumberSetting<>(renderSettings, "Outline Height", -1.0, 0.0, 2.0, 1);
    public static NumberSetting<Double> outlineWidth = new NumberSetting<>(renderSettings, "Outline Width", 1.0, 2.0, 5.0, 1);

    public HoleESP() {
        this.addSetting(holeRange);
        this.addSetting(obsidian);
        this.addSetting(obsidianHoleColour);
        this.addSetting(bedrock);
        this.addSetting(bedrockHoleColour);
        this.addSetting(renderSettings);
        this.addSetting(renderMode);
        this.addSetting(boxHeight);
        this.addSetting(outlineHeight);
        this.addSetting(outlineWidth);
    }

    @Listener
    public void onWorldRender(WorldRenderEvent event) {
        if (nullCheck()) return;

        List<BlockPos> obsidianHoles = findObsidianHoles();
        List<BlockPos> bedrockHoles = findBedrockHoles();

        GL11.glLineWidth(outlineWidth.getValue().floatValue());

        if (obsidian.getValue()) {
            if (obsidianHoles != null) {
                for (BlockPos obsidianHole : findObsidianHoles()) {
                    switch (renderMode.getValue()) {
                        case Box:
                            RenderUtil.draw(RenderUtil.generateBB(obsidianHole.getX(), obsidianHole.getY(), obsidianHole.getZ()), true, false, boxHeight.getValue(), outlineHeight.getValue(), obsidianHoleColour.getValue());
                            break;
                        case Outline:
                            RenderUtil.draw(RenderUtil.generateBB(obsidianHole.getX(), obsidianHole.getY(), obsidianHole.getZ()), false, true, boxHeight.getValue(), outlineHeight.getValue(), obsidianHoleColour.getValue());
                            break;
                        case Full:
                            RenderUtil.draw(RenderUtil.generateBB(obsidianHole.getX(), obsidianHole.getY(), obsidianHole.getZ()), true, true, boxHeight.getValue(), outlineHeight.getValue(), obsidianHoleColour.getValue());
                            break;
                    }
                }
            }
        }

        if (bedrock.getValue()) {
            if (bedrockHoles != null) {
                for (BlockPos bedrockHole : findBedrockHoles()) {
                    switch (renderMode.getValue()) {
                        case Box:
                            RenderUtil.draw(RenderUtil.generateBB(bedrockHole.getX(), bedrockHole.getY(), bedrockHole.getZ()), true, false, boxHeight.getValue(), outlineHeight.getValue(), bedrockHoleColour.getValue());
                            break;
                        case Outline:
                            RenderUtil.draw(RenderUtil.generateBB(bedrockHole.getX(), bedrockHole.getY(), bedrockHole.getZ()), false, true, boxHeight.getValue(), outlineHeight.getValue(), bedrockHoleColour.getValue());
                            break;
                        case Full:
                            RenderUtil.draw(RenderUtil.generateBB(bedrockHole.getX(), bedrockHole.getY(), bedrockHole.getZ()), true, true, boxHeight.getValue(), outlineHeight.getValue(), bedrockHoleColour.getValue());
                            break;
                    }
                }
            }
        }
    }

    public List<BlockPos> findObsidianHoles() {
        NonNullList positions = NonNullList.create();

        positions.addAll(BlockUtil.getSphere(PlayerUtil.getPlayerPos(), holeRange.getValue(), holeRange.getValue(), false, true, 0).stream()
                .filter(HoleUtil::isObsidianHole)
                .collect(Collectors.toList()));

        return positions;
    }

    public List<BlockPos> findBedrockHoles() {
        NonNullList positions = NonNullList.create();

        positions.addAll(BlockUtil.getSphere(PlayerUtil.getPlayerPos(), holeRange.getValue(), holeRange.getValue(), false, true, 0).stream()
                .filter(HoleUtil::isBedrockHole)
                .collect(Collectors.toList()));

        return positions;
    }

    public enum RenderModes {
        Box,
        Outline,
        Full
    }
}