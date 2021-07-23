package io.github.icrazyblaze.modlistpaste;

import io.github.icrazyblaze.modlistpaste.util.ModListHelper;
import net.minecraft.client.KeyboardListener;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModListToClipboard {

    public static void copyModList(boolean alphabetical) {
        try {
            String text = ModListHelper.getModList(alphabetical);

            // Use KeyboardListener to easily set clipboard content
            KeyboardListener kb = new KeyboardListener(Minecraft.getInstance());
            kb.setClipboard(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
