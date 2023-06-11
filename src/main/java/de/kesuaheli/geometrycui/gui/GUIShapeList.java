package de.kesuaheli.geometrycui.gui;

import de.kesuaheli.geometrycui.Helper;
import de.kesuaheli.geometrycui.config.Session;
import de.kesuaheli.geometrycui.geometry.Shape;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class GUIShapeList extends WPlainPanel {

    WButton button;

    private GUIShapeList() {
        button = new WButton();
        this.add(button, 0, 0, 7*18, 20);
        this.setSize(7*18, 25);
    }

    public static WListPanel<Shape, GUIShapeList> ListPanel() {
        return new WListPanel<>(Arrays.asList(Shape.values()), GUIShapeList::new, configurator())
                .setListItemHeight(25);
    }

    private static BiConsumer<Shape, GUIShapeList> configurator() {
        return (Shape s, GUIShapeList item) -> {
            item.button.setIcon(new TextureIcon(s.getImage()));
            item.button.setLabel(s.toText());
            item.button.setOnClick(() -> onSelect(s));
        };
    }

    private static void onSelect(Shape s) {
        Session.getInstance().setShape(s);
        Helper.sendMessage(Text.translatable("geometrycui.command.shape.set", s.toString()));
    }
}
