package io.github.icrazyblaze.modlistpaste.util;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class ModListHelper {
    public static String getModList(boolean alphabetical) {

        List<ModInfo> mods = ModList.get().getMods();
        ArrayList<String> modStrings = new ArrayList<>();

        // Build an array of mod names and mod id's (fixed to have comma at end)
        for (ModInfo mod : mods) {
            modStrings.add(String.format("'%s' (%s:%s), %s", mod.getDisplayName(), mod.getModId(), mod.getVersion(), System.lineSeparator()));
        }

        // Sort if specified
        if (alphabetical)
            modStrings.sort(Collator.getInstance());

        // Create one large string from the array
        StringBuilder builder = new StringBuilder();
        for (String value : modStrings) {
            builder.append(value);
        }

        String str = builder.toString();
        str = str.substring(0, str.length() - (System.lineSeparator().length() + 2)); // Remove newline, space, comma from end

        return str;
    }

}
