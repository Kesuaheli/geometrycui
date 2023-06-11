package de.kesuaheli.geometrycui.gui;

import de.kesuaheli.geometrycui.Helper;
import de.kesuaheli.geometrycui.config.Session;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class GeometryGUI extends LightweightGuiDescription {

    public GeometryGUI() {
        WGridPanel root = new WGridPanel();
        root.setSize(20*18+14, 10*18+14);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel label = new WLabel(Text.literal("Geometry CUI"));
        root.add(label, 0, 0, 2, 1);

        // Position
        WButton buttonSetCenter = new WButton(Text.literal("Set Position"));
        buttonSetCenter.setIcon(new TextureIcon(new Identifier("geometrycui", "textures/gui/center.png")));
        buttonSetCenter.setOnClick(() -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            assert player != null;
            BlockPos position = BlockPos.ofFloored(player.getPos());
            Session.getInstance().setOrigin(position);
            String positionString = position.getX() + ", " + position.getY() + ", " + position.getZ();
            Helper.sendMessage(Text.translatable("geometrycui.command.position.set", positionString));
        });
        root.add(buttonSetCenter, 1, 1, 6, 1);

        // Shape
        WLabel shapeLabel = new WLabel(Text.literal("Select Shape"));
        shapeLabel.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(shapeLabel, 0, 3, 8, 1);

        root.add(GUIShapeList.ListPanel(), 0, 4, 8, 6);

        // Size X
        WLabel labelSizeX = new WLabel(Text.literal("Size X"));
        labelSizeX.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        labelSizeX.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(labelSizeX, 10, 1, 3, 1);

        WTextField textSizeX = new WTextField(Text.literal("Size X"));
        textSizeX.setText(String.valueOf(Session.getInstance().getSizeX()));
        textSizeX.setChangedListener(s -> Session.getInstance().setSizeX(Integer.parseInt(s)));
        textSizeX.setTextPredicate(Helper.isPositiveInt());
        root.add(textSizeX, 14, 1, 5, 1);

        // Size Z
        WLabel labelSizeZ = new WLabel(Text.literal("Size Z"));
        labelSizeZ.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        labelSizeZ.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(labelSizeZ, 10, 3, 3, 1);

        WTextField textSizeZ = new WTextField(Text.literal("Size Z"));
        textSizeZ.setText(String.valueOf(Session.getInstance().getSizeZ()));
        textSizeZ.setChangedListener(s -> Session.getInstance().setSizeZ(Integer.parseInt(s)));
        textSizeZ.setTextPredicate(Helper.isPositiveInt());
        root.add(textSizeZ, 14, 3, 5, 1);

        this.setRootPanel(root);
        root.validate(this);
    }

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }
}
