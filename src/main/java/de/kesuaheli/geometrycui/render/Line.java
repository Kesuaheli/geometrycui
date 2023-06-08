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
    public Line(double startX, double startY, double startZ, double endX, double endY, double endZ, Color color) {
        this(new Vec3d(startX, startY, startZ), new Vec3d(endX, endY, endZ), color);
    }

    @Override
    public void render(MatrixStack matrices) {
        Renderer3d.renderLine(matrices, this.color, this.start, this.end);
    }
}
