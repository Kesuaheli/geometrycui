package de.kesuaheli.geometrycui.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.kesuaheli.geometrycui.Helper;
import dev.xpple.clientarguments.arguments.CEnumArgumentType;
import de.kesuaheli.geometrycui.config.Session;
import de.kesuaheli.geometrycui.geometry.Shape;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ShapeCommand {
    public static ArgumentBuilder<FabricClientCommandSource, ?> register() {
        return literal("shape")
                .then(literal("set").then(argument("shape", CEnumArgumentType.enumArg(Shape.class))
                        .executes(ShapeCommand::executeSet)))
                .then(literal("get")
                        .executes(ShapeCommand::executeGet))
                .then(literal("clear")
                        .executes(ShapeCommand::executeClear));
    }

    private static int executeSet(CommandContext<FabricClientCommandSource> ctx) {
        Shape shape = CEnumArgumentType.getEnum(ctx, "shape");
        Session.getInstance().setShape(shape);
        Helper.sendMessage(Text.translatable("geometrycui.command.shape.set", shape.toString()));
        return 0;
    }

    private static int executeGet(CommandContext<FabricClientCommandSource> ctx) {
        Shape shape = Session.getInstance().getShape();
        if (shape == null) {
            Helper.sendMessage(Text.translatable("geometrycui.command.shape.null"));
        } else {
            Helper.sendMessage(Text.translatable("geometrycui.command.shape.get", shape.toString()));
        }
        return 0;
    }

    private static int executeClear(CommandContext<FabricClientCommandSource> ctx) {
        Session.getInstance().clearShape();
        Helper.sendMessage(Text.translatable("geometrycui.command.shape.clear"));
        return 0;
    }
}
