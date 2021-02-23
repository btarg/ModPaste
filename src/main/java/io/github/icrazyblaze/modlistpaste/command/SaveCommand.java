package io.github.icrazyblaze.modlistpaste.command;

import com.mojang.brigadier.Command;
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
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) {

        ModListToFile.saveModList();

        ITextComponent itextcomponent = (new StringTextComponent(ModListToFile.textFile.getName())).mergeStyle(TextFormatting.UNDERLINE).modifyStyle((style) -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ModListToFile.textFile.getAbsolutePath())));
        context.getSource().sendFeedback(new StringTextComponent("Saved mod list as ").append(itextcomponent), false);

        return SINGLE_SUCCESS;
    }
}