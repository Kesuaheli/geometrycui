package de.kesuaheli.geometrycui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.function.Predicate;

public class Helper {
    public static void sendMessage(String msg) {
        sendMessage(Text.of(msg));
    }
    public static void sendMessage(Text msg) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(msg);
    }

    public static Predicate<String> isPositiveInt() {
        return s -> {
            try {
                return Integer.parseInt(s) >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        };
    }
}
