package de.kesuaheli.geometrycui;

import com.mojang.brigadier.CommandDispatcher;
import de.kesuaheli.geometrycui.command.PositionCommand;
import de.kesuaheli.geometrycui.render.RenderQueue;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.command.CommandRegistryAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeometryCUI implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("geometrycui");

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(this::registerCommands);
        WorldRenderEvents.AFTER_ENTITIES.register(this::registerRenderer);
        LOGGER.info("Hello client!");
    }

    private void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        PositionCommand.register(dispatcher);
    }

    private void registerRenderer(WorldRenderContext ctx) {
        RenderQueue.render(ctx.matrixStack());
    }
}
