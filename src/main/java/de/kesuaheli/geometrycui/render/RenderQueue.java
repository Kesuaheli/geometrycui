package de.kesuaheli.geometrycui.render;

import de.kesuaheli.geometrycui.config.Session;
import me.x150.renderer.render.Renderer3d;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;

public class RenderQueue {

    public static void render(MatrixStack matrices) {
        Renderer3d.renderThroughWalls();

        List<RenderShape> list = Session.getInstance().render();
        list.forEach(renderShape -> renderShape.render(matrices));
    }
}
