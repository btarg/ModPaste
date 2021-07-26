package io.github.icrazyblaze.modlistpaste;

import com.mojang.brigadier.CommandDispatcher;
import io.github.icrazyblaze.modlistpaste.command.CopyCommand;
import io.github.icrazyblaze.modlistpaste.command.SaveCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author iCrazyBlaze
 */
@Mod(Main.MOD_ID)
@OnlyIn(Dist.CLIENT)
public final class Main {

    public static final String MOD_ID = "modlistpaste";

    public Main() {
        MinecraftForge.EVENT_BUS.register(this.getClass());
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {

        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("modlist")
                .then(CopyCommand.register())
                .then(SaveCommand.register()));
    }
}
