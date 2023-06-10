package de.kesuaheli.geometrycui.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.text.Text;

public class GeometryGUI extends LightweightGuiDescription {

    public GeometryGUI() {
        WGridPanel root = new WGridPanel();
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel label = new WLabel(Text.literal("W.I.P."), 0x000000);
        root.add(label, 0, 4, 2, 1);

        root.validate(this);
        this.setRootPanel(root);
    }
}
