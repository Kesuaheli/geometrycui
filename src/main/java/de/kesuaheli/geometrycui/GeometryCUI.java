package de.kesuaheli.geometrycui;

import com.mojang.brigadier.CommandDispatcher;
import de.kesuaheli.geometrycui.command.PositionCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeometryCUI implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("geometrycui");

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(GeometryCUI::registerCommands);
        LOGGER.info("Hello client!");
    }

    static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        PositionCommand.register(dispatcher);
    }
}
