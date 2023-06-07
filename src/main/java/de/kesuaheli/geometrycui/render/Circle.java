package de.kesuaheli.geometrycui.render;

import me.x150.renderer.render.Renderer2d;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;

import java.awt.*;

public class Circle extends RenderShape {

    private final Vec2f start;
    private final double radius;
    private final Color color;

    public Circle(Vec2f start, double radius, Color color) {
        this.start = start;
        this.radius = radius;
        this.color = color;
    }
    @Override
    public void render(MatrixStack matrices) {
        Renderer2d.renderCircle(matrices, this.color, this.start.x, this.start.y, this.radius, 50);
    }
}
