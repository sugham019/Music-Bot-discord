package core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

abstract public class SlashCommandEvent extends ListenerAdapter {

    @Override
    abstract public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event);
}
