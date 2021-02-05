package me.olliem5.ferox.api.hud;

import git.littledraily.eventsystem.Listener;
import git.littledraily.eventsystem.event.Priority;
import me.olliem5.ferox.api.traits.Minecraft;
import me.olliem5.ferox.impl.events.GameOverlayRenderEvent;
import me.olliem5.ferox.impl.hud.InventoryComponent;
import me.olliem5.ferox.impl.hud.PlayerComponent;
import me.olliem5.ferox.impl.hud.WatermarkComponent;
import me.olliem5.ferox.impl.hud.WelcomerComponent;

import java.util.ArrayList;
import java.util.Arrays;

public final class HudManager implements Minecraft {
    private static ArrayList<HudComponent> components = new ArrayList<>();

    public static void init() {
        components.addAll(Arrays.asList(
                new InventoryComponent(),
                new PlayerComponent(),
                new WatermarkComponent(),
                new WelcomerComponent()
        ));
    }

    public static ArrayList<HudComponent> getComponents() {
        return components;
    }

    @Listener(priority = Priority.LOWEST)
    public void onGameOverlayRender(GameOverlayRenderEvent event) {
        if (mc.world == null || mc.player == null) return;

        for (HudComponent hudComponent : components) {
            if (hudComponent.isVisible()) {
                hudComponent.render();
            }
        }
    }
}
