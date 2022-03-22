package io.github.icrazyblaze.modlistpaste;

import com.mojang.brigadier.CommandDispatcher;
import io.github.icrazyblaze.modlistpaste.command.CopyCommand;
import io.github.icrazyblaze.modlistpaste.command.SaveCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author RonaRage / Btarg
 */
@Mod("modlistpaste")
public final class Main {

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
