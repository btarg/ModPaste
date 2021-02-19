package io.github.icrazyblaze.modlistpaste;

import net.minecraft.client.KeyboardListener;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ModListToClipboard {

    public static void copyModList() {
        try {
            List<ModInfo> mods = ModList.get().getMods();
            ArrayList<String> modStrings = new ArrayList<>();

            // Build an array of mod names and mod id's
            for (ModInfo mod : mods) {
                modStrings.add(String.format("'%s' (%s:%s)%s", mod.getDisplayName(), mod.getModId(), mod.getVersion(), System.lineSeparator()));
            }

            // Create one large string from the array
            StringBuilder builder = new StringBuilder();
            for (String value : modStrings) {
                builder.append(value);
            }
            String text = builder.toString();

            // Use KeyboardListener to easily set clipboard content
            KeyboardListener kb = new KeyboardListener(Minecraft.getInstance());
            kb.setClipboardString(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
