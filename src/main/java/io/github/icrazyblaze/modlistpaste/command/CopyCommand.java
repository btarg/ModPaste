package io.github.icrazyblaze.modlistpaste.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.icrazyblaze.modlistpaste.ModListToClipboard;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopyCommand implements Command<CommandSource> {

    private static final CopyCommand CMD = new CopyCommand();

    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("copy")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) {

        ModListToClipboard.copyModList();
        context.getSource().sendFeedback(new StringTextComponent("Copied text to clipboard!"), false);

        return SINGLE_SUCCESS;
    }
}