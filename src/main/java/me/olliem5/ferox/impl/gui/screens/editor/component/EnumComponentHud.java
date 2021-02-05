package me.olliem5.ferox.impl.gui.screens.editor.component;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.olliem5.ferox.api.setting.Setting;
import me.olliem5.ferox.api.util.render.font.FontUtil;
import me.olliem5.ferox.impl.gui.Component;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public final class EnumComponentHud extends Component {
    private Setting<Enum> op;
    private HudComponentButton parent;
    private int offset;
    private int x;
    private int y;

    public EnumComponentHud(Setting<Enum> op, HudComponentButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }

    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + parent.parent.getWidth(), parent.parent.getY() + offset + 16, new Color(20, 20, 20, 150).getRGB());
        Gui.drawRect(parent.parent.getX() + 1, parent.parent.getY() + offset, parent.parent.getX() + parent.parent.getWidth() -1, parent.parent.getY() + offset + 15, new Color(50, 50, 50, 150).getRGB());

        FontUtil.drawText(op.getName() + " " + ChatFormatting.GRAY + op.getValue().toString().toUpperCase(), parent.parent.getX() + 4, parent.parent.getY() + offset + 4, -1);
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.y = parent.parent.getY() + this.offset;
        this.x = parent.parent.getX();
    }

    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && this.parent.isOpen()) {
            setEnumValue(GetNextEnumValue(button == 1));
        }
    }

    public String GetNextEnumValue(boolean reverse) {
        final Enum currentValue = this.op.getValue();

        int i = 0;

        for (; i < this.op.getValue().getClass().getEnumConstants().length; i++) {
            final Enum e = this.op.getValue().getClass().getEnumConstants()[i];

            if (e.name().equalsIgnoreCase(currentValue.name())) break;
        }

        return this.op.getValue().getClass().getEnumConstants()[(reverse ? (i != 0 ? i - 1 : op.getValue().getClass().getEnumConstants().length - 1) : i + 1) % op.getValue().getClass().getEnumConstants().length].toString();
    }

    public void setEnumValue(String value) {
        for (Enum e : this.op.getValue().getClass().getEnumConstants()) {
            if (e.name().equalsIgnoreCase(value)) {
                op.setValue(e);
                break;
            }
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        if (x > this.x && x < this.x + 100 && y > this.y && y < this.y + 16) {
            return true;
        } else {
            return false;
        }
    }
}
