package io.github.icrazyblaze.modlistpaste;

import io.github.icrazyblaze.modlistpaste.util.ModListHelper;
import net.minecraftforge.fml.loading.FMLPaths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ModListToFile {

    private static final DateFormat date = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
    public static File textFile;

    public static File getFile() {

        try {

            String nowDate = date.format(new Date());
            Path path = FMLPaths.GAMEDIR.get().resolve("mod_data/modlistpaste/modlist-" + nowDate + ".txt");

            return path.toFile();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void saveModList(boolean alphabetical) {
        try {

            textFile = getFile();
            textFile.getParentFile().mkdirs();

            textFile.createNewFile(); // Create file if it doesn't already exist
            FileWriter fr = new FileWriter(textFile, false);
            fr.write(ModListHelper.getModList(alphabetical));

            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
