package io.github.icrazyblaze.modlistpaste.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.icrazyblaze.modlistpaste.ModListToFile;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SaveCommand implements Command<CommandSourceStack> {

    private static final SaveCommand CMD = new SaveCommand();

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("save")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("alphabetical", BoolArgumentType.bool())
                        .executes(CMD));
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {

        boolean alphabetical = BoolArgumentType.getBool(context, "alphabetical");

        ModListToFile.saveModList(alphabetical);

        MutableComponent itextcomponent = (new TextComponent(ModListToFile.textFile.getName())).withStyle(ChatFormatting.UNDERLINE).withStyle((style) -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ModListToFile.textFile.getAbsolutePath())));
        context.getSource().sendSuccess(new TextComponent("Saved mod list as ").append(itextcomponent), false);

        return SINGLE_SUCCESS;
    }
}