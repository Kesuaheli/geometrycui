package de.kesuaheli.geometrycui.command.arguments;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.util.StringIdentifiable;

import java.util.function.Supplier;

public class CEnumArgumentType<T extends Enum<T> & StringIdentifiable> extends EnumArgumentType<T> {

    protected CEnumArgumentType(Codec<T> codec, Supplier<T[]> valuesSupplier) {
        super(codec, valuesSupplier);
    }

    public static <T extends Enum<T> & StringIdentifiable> CEnumArgumentType<T> enumArg(Class<T> enumClass) {
        return new CEnumArgumentType<>(StringIdentifiable.createCodec(enumClass::getEnumConstants), enumClass::getEnumConstants);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T> & StringIdentifiable> T getEnum(CommandContext<FabricClientCommandSource> context, String name) {
        return (T) context.getArgument(name, Enum.class);
    }
}
