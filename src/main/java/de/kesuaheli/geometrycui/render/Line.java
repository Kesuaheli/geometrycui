package de.kesuaheli.geometrycui.render;

import me.x150.renderer.render.Renderer3d;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class Line extends RenderShape {

    private final Vec3d start;
    private final Vec3d end;
    private final Color color;

    public Line(Vec3d start, Vec3d end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    public Line(Vec3d start, Vec3d end, int red, int green, int blue, int alpha) {
        this(start, end, new Color(red, green, blue, alpha));
    }
    public Line(Vec3d start, Vec3d end, int red, int green, int blue) {
        this(start, end, new Color(red, green, blue));
    }
    public Line(Vec3d start, Vec3d end) {
        this(start, end, Color.WHITE);
    }

    @Override
    public void render(MatrixStack matrices) {
        Renderer3d.renderLine(matrices, this.color, this.start, this.end);
    }
}
