package de.kesuaheli.geometrycui;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.kesuaheli.geometrycui.command.PositionCommand;
import de.kesuaheli.geometrycui.command.ShapeCommand;
import de.kesuaheli.geometrycui.command.SizeCommand;
import de.kesuaheli.geometrycui.gui.GeometryGUI;
import de.kesuaheli.geometrycui.gui.GeometryScreen;
import de.kesuaheli.geometrycui.render.RenderQueue;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
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
        LiteralArgumentBuilder<FabricClientCommandSource> cmd = ClientCommandManager.literal("geometrycui");

        cmd.then(PositionCommand.register());
        cmd.then(ShapeCommand.register());
        cmd.then(SizeCommand.register());
        cmd.executes(this::executeCommand);

        dispatcher.register(cmd);
    }

    private void registerRenderer(WorldRenderContext ctx) {
        RenderQueue.render(ctx.matrixStack());
    }

    private int executeCommand(CommandContext<FabricClientCommandSource> ctx) {
        Screen screen = new GeometryScreen(new GeometryGUI());

        // MinecraftClient.setScreen() doesn't work, because the screen
        // will be set to null after the command got executed.
        MinecraftClient client = ctx.getSource().getClient();
        client.send(() -> client.setScreen(screen));
        return 0;
    }
}
