package de.kesuaheli.geometrycui.config;

import de.kesuaheli.geometrycui.render.Cube;
import de.kesuaheli.geometrycui.render.Shape;
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

    public List<Shape> render() {
        List<Shape> list = new ArrayList<>();

        if (this.origin != null) list.add(new Cube(this.origin, 1, Color.GREEN));

        return list;
    }

    public void setOrigin(BlockPos pos) {
        this.origin = pos;
    }

    public @Nullable BlockPos getOrigin() {
        return this.origin;
    }
    public void removeOrigin() {
        this.origin = null;
    }
}
