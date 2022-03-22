package io.github.icrazyblaze.modlistpaste.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
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
                .requires(cs -> cs.hasPermission(0))
                .executes(c -> {
                	ModListToClipboard.copyModList(false);
                    c.getSource().sendSuccess(new StringTextComponent("Copied text to clipboard!"), false);
                	return SINGLE_SUCCESS;
                })
                .then(Commands.argument("alphabetical", BoolArgumentType.bool())
                .executes(CMD));
    }

    @Override
    public int run(CommandContext<CommandSource> context) {

        boolean alphabetical = BoolArgumentType.getBool(context, "alphabetical");

        ModListToClipboard.copyModList(alphabetical);
        context.getSource().sendSuccess(new StringTextComponent("Copied text to clipboard!"), false);

        return SINGLE_SUCCESS;
    }
}