package io.github.icrazyblaze.modlistpaste;

import io.github.icrazyblaze.modlistpaste.util.ModListHelper;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;

public class ModListToClipboard {

    public static void copyModList(boolean alphabetical) {
        try {
            String text = ModListHelper.getModList(alphabetical);

            // Use KeyboardListener to easily set clipboard content
            KeyboardHandler kb = new KeyboardHandler(Minecraft.getInstance());
            kb.setClipboard(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
