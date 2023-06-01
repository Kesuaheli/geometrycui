package de.kesuaheli.geometrycui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Helper {
    public static void sendMessage(String msg) {
        sendMessage(Text.of(msg));
    }
    public static void sendMessage(Text msg) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(msg);
    }
}
