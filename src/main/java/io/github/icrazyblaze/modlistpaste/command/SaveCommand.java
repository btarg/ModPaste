package io.github.icrazyblaze.modlistpaste.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.icrazyblaze.modlistpaste.ModListToFile;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SaveCommand implements Command<CommandSource> {

    private static final SaveCommand CMD = new SaveCommand();

    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("save")
                .requires(cs -> cs.hasPermission(0))
                .then(Commands.argument("alphabetical", BoolArgumentType.bool())
                .executes(CMD));
    }

    @Override
    public int run(CommandContext<CommandSource> context) {

        boolean alphabetical = BoolArgumentType.getBool(context, "alphabetical");

        ModListToFile.saveModList(alphabetical);

        ITextComponent itextcomponent = (new StringTextComponent(ModListToFile.textFile.getName())).withStyle(TextFormatting.UNDERLINE).withStyle((style) -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ModListToFile.textFile.getAbsolutePath())));
        context.getSource().sendSuccess(new StringTextComponent("Saved mod list as ").append(itextcomponent), false);

        return SINGLE_SUCCESS;
    }
}