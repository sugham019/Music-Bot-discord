package core;

import cmds.Test;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.collections4.map.HashedMap;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SlashCmdsHandler extends SlashCommandEvent {
    private static HashMap<String, Command> commands = new HashMap<>();

    public SlashCmdsHandler(){}

    public SlashCmdsHandler(String a){
        setCommands();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commands.get(event.getName()).call(event);
    }

    private void setCommands(){
        commands.put("test", new Test());
        // Todo
    }




}
