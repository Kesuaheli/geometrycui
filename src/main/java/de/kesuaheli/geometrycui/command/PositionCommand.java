package de.kesuaheli.geometrycui.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import de.kesuaheli.geometrycui.Helper;
import de.kesuaheli.geometrycui.config.Session;
import dev.xpple.clientarguments.arguments.CBlockPosArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

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
        Session.getInstance().setOrigin(position);

        String positionString = position.getX() + ", " + position.getY() + ", " + position.getZ();
        Helper.sendMessage(Text.translatable("geometrycui.command.position.set", positionString));
        return 0;
    }
}
