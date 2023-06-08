package de.kesuaheli.geometrycui.render;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cuboid extends RenderShape {

    private final Vec3d start;
    private final Vec3d end;
    private final Color color;

    public Cuboid(Vec3d start, Vec3d end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    public Cuboid(BlockPos center, double sizeX, double sizeY, double sizeZ, Color color) {
        this.start = new Vec3d(
                center.toCenterPos().x - sizeX/2,
                center.toCenterPos().y - sizeY/2,
                center.toCenterPos().z - sizeZ/2);
        this.end = new Vec3d(
                center.toCenterPos().x + sizeX/2,
                center.toCenterPos().y + sizeY/2,
                center.toCenterPos().z + sizeZ/2);
        this.color = color;
    }
    
    @Override
    public void render(MatrixStack matrices) {
        List<Line> lines = new ArrayList<>();
        // lower rectangle
        lines.add(new Line(start.x, start.y, start.z, end.x, start.y, start.z, color));
        lines.add(new Line(start.x, start.y, start.z, start.x, start.y, end.z, color));
        lines.add(new Line(end.x, start.y, start.z, end.x, start.y, end.z, color));
        lines.add(new Line(start.x, start.y, end.z, end.x, start.y, end.z, color));
        // vertical lines
        lines.add(new Line(start.x, start.y, start.z, start.x, end.y, start.z, color));
        lines.add(new Line(end.x, start.y, start.z, end.x, end.y, start.z, color));
        lines.add(new Line(start.x, start.y, end.z, start.x, end.y, end.z, color));
        lines.add(new Line(end.x, start.y, end.z, end.x, end.y, end.z, color));
        // upper rectangle
        lines.add(new Line(start.x, end.y, start.z, end.x, end.y, start.z, color));
        lines.add(new Line(start.x, end.y, start.z, start.x, end.y, end.z, color));
        lines.add(new Line(end.x, end.y, start.z, end.x, end.y, end.z, color));
        lines.add(new Line(start.x, end.y, end.z, end.x, end.y, end.z, color));
        
        lines.forEach(line -> line.render(matrices));
    }
}
