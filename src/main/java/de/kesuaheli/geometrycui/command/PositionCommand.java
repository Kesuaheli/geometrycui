package de.kesuaheli.geometrycui.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import de.kesuaheli.geometrycui.GeometryCUI;
import de.kesuaheli.geometrycui.Helper;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.DefaultPosArgument;
import net.minecraft.command.argument.PosArgument;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class PositionCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("gpos")
                .then(argument("position", Vec3ArgumentType.vec3()).executes(PositionCommand::executePosition))
        );
    }

    private static int executePosition(CommandContext<FabricClientCommandSource> ctx) {
        GeometryCUI.LOGGER.info("Command executed");
        Helper.sendMessage("Command executed");
        return 0;
    }
}
