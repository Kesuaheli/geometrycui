package de.kesuaheli.geometrycui.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.kesuaheli.geometrycui.Helper;
import de.kesuaheli.geometrycui.config.Session;
import dev.xpple.clientarguments.arguments.CBlockPosArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class PositionCommand {
    public static ArgumentBuilder<FabricClientCommandSource, ?> register() {
        return literal("position")
                .then(literal("set").then(argument("position", CBlockPosArgumentType.blockPos())
                        .executes(PositionCommand::executeSet)))
                .then(literal("get")
                        .executes(PositionCommand::executeGet))
                .then(literal("clear")
                        .executes(PositionCommand::executeClear));
    }

    private static int executeSet(CommandContext<FabricClientCommandSource> ctx) {
        BlockPos position = CBlockPosArgumentType.getCBlockPos(ctx, "position");
        Session.getInstance().setOrigin(position);

        String positionString = position.getX() + ", " + position.getY() + ", " + position.getZ();
        Helper.sendMessage(Text.translatable("geometrycui.command.position.set", positionString));
        return 0;
    }

    private static int executeGet(CommandContext<FabricClientCommandSource> ctx) {
        BlockPos position = Session.getInstance().getOrigin();
        if (position == null) {
            Helper.sendMessage(Text.translatable("geometrycui.command.position.null"));
        } else {
            String positionString = position.getX() + ", " + position.getY() + ", " + position.getZ();
            Helper.sendMessage(Text.translatable("geometrycui.command.position.get", positionString).formatted(Formatting.DARK_AQUA));
        }
        return 0;
    }

    private static int executeClear(CommandContext<FabricClientCommandSource> ctx) {
        Session.getInstance().clearOrigin();
        Helper.sendMessage(Text.translatable("geometrycui.command.position.clear"));
        return 0;
    }
}
