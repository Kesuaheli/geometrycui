package de.kesuaheli.geometrycui.config;

import de.kesuaheli.geometrycui.geometry.Shape;
import de.kesuaheli.geometrycui.render.Cube;
import de.kesuaheli.geometrycui.render.RenderShape;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Session {

    private BlockPos origin;
    private Shape shape;
    private int sizeX;
    private int sizeZ;

    private static final HashMap<String, Session> sessions = new HashMap<>();
    
    public static Session getInstance() {
        Session session = sessions.get(getSessionID());
        if (session == null) {
            session = new Session();
            sessions.put(getSessionID(), session);
        }
        return session;
    }
    private static String getSessionID() {
        MinecraftClient mc = MinecraftClient.getInstance();
        String scope, id = "";
        if (mc.isInSingleplayer()) {
            scope = "singleplayer";
            IntegratedServer world = mc.getServer();
            assert world != null;
            id = world.getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString();
        }
        else if (mc.getCurrentServerEntry() != null) {
            scope = "server";
            id = mc.getCurrentServerEntry().address;
        }
        else {
            scope = "unknown";
        }

        return scope + "/" + id;
    }

    public List<RenderShape> render() {
        List<RenderShape> list = new ArrayList<>();

        if (this.origin != null) list.add(new Cube(this.origin, 1, Color.GREEN));

        return list;
    }

    public void setOrigin(BlockPos pos) {
        this.origin = pos;
    }
    public @Nullable BlockPos getOrigin() {
        return this.origin;
    }
    public void clearOrigin() {
        this.origin = null;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
    public @Nullable Shape getShape() {
        return this.shape;
    }
    public void clearShape() {
        this.shape = null;
    }

    public boolean setSize(int sizeX, int sizeZ) {
        if (sizeX <= 0 || sizeZ <= 0) {
            return false;
        }

        this.sizeX = sizeX;
        this.sizeZ = sizeZ;
        return true;
    }
    public int getSizeX() {
        return this.sizeX;
    }
    public int getSizeZ() {
        return this.sizeZ;
    }
    public void clearSize() {
        this.sizeX = 0;
        this.sizeZ = 0;
    }
}
