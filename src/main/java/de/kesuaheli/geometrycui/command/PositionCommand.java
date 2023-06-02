package de.kesuaheli.geometrycui.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import de.kesuaheli.geometrycui.Helper;
import dev.xpple.clientarguments.arguments.CBlockPosArgumentType;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class PositionCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("gpos")
                .then(argument("position", CBlockPosArgumentType.blockPos()).executes(PositionCommand::executePosition))
        );
    }

    private static int executePosition(CommandContext<FabricClientCommandSource> ctx) {
        BlockPos position = CBlockPosArgumentType.getCBlockPos(ctx, "position");
        Helper.sendMessage(Text.translatable("geometrycui.command.position.set", position.toString()));

        return 0;
    }
}
