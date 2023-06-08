package de.kesuaheli.geometrycui.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.kesuaheli.geometrycui.Helper;
import de.kesuaheli.geometrycui.config.Session;
import dev.xpple.clientarguments.arguments.CNumberRangeArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.predicate.NumberRange;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SizeCommand {
    public static ArgumentBuilder<FabricClientCommandSource, ?> register() {
        return literal("size")
                .then(literal("set")
                        .then(argument("sizeX", CNumberRangeArgumentType.intRange())
                        .then(argument("sizeZ", CNumberRangeArgumentType.intRange())
                        .executes(SizeCommand::executeSet))))
                .then(literal("get")
                        .executes(SizeCommand::executeGet))
                .then(literal("clear")
                        .executes(SizeCommand::executeClear));
    }

    private static int executeSet(CommandContext<FabricClientCommandSource> ctx) {
        NumberRange.IntRange rangeX = CNumberRangeArgumentType.IntRangeArgumentType.getCRangeArgument(ctx, "sizeX");
        NumberRange.IntRange rangeZ = CNumberRangeArgumentType.IntRangeArgumentType.getCRangeArgument(ctx, "sizeZ");
        int sizeX, sizeZ;
        try {
            sizeX = rangeX.getMin();
            sizeZ = rangeZ.getMin();
        } catch (NullPointerException e) {
            return 1;
        }

        if (!Session.getInstance().setSize(sizeX, sizeZ)) {
            Helper.sendMessage(Text.translatable("geometrycui.command.size.set.invalid"));
        } else {
            Helper.sendMessage(Text.translatable("geometrycui.command.size.set", sizeX, sizeZ));
        }
        return 0;
    }

    private static int executeGet(CommandContext<FabricClientCommandSource> ctx) {
        int sizeX = Session.getInstance().getSizeX();
        int sizeZ = Session.getInstance().getSizeZ();

        if (sizeX == 0 && sizeZ == 0) {
            Helper.sendMessage(Text.translatable("geometrycui.command.size.null"));
        } else {
            Helper.sendMessage(Text.translatable("geometrycui.command.size.get", sizeX, sizeZ));
        }
        return 0;
    }

    private static int executeClear(CommandContext<FabricClientCommandSource> ctx) {
        Session.getInstance().clearSize();
        Helper.sendMessage(Text.translatable("geometrycui.command.size.clear"));
        return 0;
    }
}
