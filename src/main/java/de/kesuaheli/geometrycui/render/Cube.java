package de.kesuaheli.geometrycui.render;

import me.x150.renderer.render.Renderer3d;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class Cube extends RenderShape {

    private final Vec3d start;
    private final double size;
    private final Color color;

    public Cube(BlockPos start, double size, Color color) {
        this.start = start.toCenterPos().subtract(0.5, 0.5, 0.5);
        this.size = size;
        this.color = color;
    }
    public Cube(BlockPos start, double size, int red, int green, int blue, int alpha) {
        this(start, size, new Color(red, green, blue, alpha));
    }
    public Cube(BlockPos start, double size, int red, int green, int blue) {
        this(start, size, new Color(red, green, blue));
    }
    public Cube(BlockPos start, double size) {
        this(start, size, Color.WHITE);
    }

    @Override
    public void render(MatrixStack matrices) {
        Renderer3d.renderOutline(matrices, this.color, this.start, new Vec3d(size,size,size));
    }
}
