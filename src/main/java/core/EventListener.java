package core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EventListener extends ListenerAdapter {

    public EventListener(){
        SlashCmdsHandler.init();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        SlashCmdsHandler.onSlashCommandInteraction(event);
    }
}
