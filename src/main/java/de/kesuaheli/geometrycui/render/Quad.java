package de.kesuaheli.geometrycui.render;

import me.x150.renderer.render.Renderer2d;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;

import java.awt.*;

public class Quad extends RenderShape {

    private final Vec2f start;
    private final Vec2f end;
    private final Color color;

    public Quad(Vec2f start, Vec2f end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    @Override
    public void render(MatrixStack matrices) {
        Renderer2d.renderQuad(matrices, this.color, this.start.x, this.start.y, this.end.x, this.end.y);
    }
}
