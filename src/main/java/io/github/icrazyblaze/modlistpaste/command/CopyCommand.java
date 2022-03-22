package io.github.icrazyblaze.modlistpaste.command;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;


import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.icrazyblaze.modlistpaste.ModListToClipboard;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

public class CopyCommand implements Command<CommandSourceStack> {

    private static final CopyCommand CMD = new CopyCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("copy")
                .requires(cs -> cs.hasPermission(0))
                .executes(c -> {
                    ModListToClipboard.copyModList(false);
                    c.getSource().sendSuccess(new TextComponent("Copied text to clipboard!"), false);
                    return SINGLE_SUCCESS;
                })
                .then(Commands.argument("alphabetical", BoolArgumentType.bool())
                        .executes(CMD));
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {

        boolean alphabetical = BoolArgumentType.getBool(context, "alphabetical");

        ModListToClipboard.copyModList(alphabetical);
        context.getSource().sendSuccess(new TextComponent("Copied text to clipboard!"), false);

        return SINGLE_SUCCESS;
    }
}