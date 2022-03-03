package core;

import cmds.music.Play;
import cmds.music.Skip;
import cmds.music.Stop;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.IGuildChannelContainer;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.HashMap;
import java.util.List;

import static core.Bot.jda;

public class SlashCmdsHandler {
    private static HashMap<String, Command> commands = new HashMap<>();

    public static void onSlashCommandInteraction(SlashCommandInteraction event){
        commands.get(event.getName()).call(event);
    }

    public static void init(){

        commands.put("play", new Play());
        commands.put("stop", new Stop());
        commands.put("list", new cmds.music.List());
        commands.put("skip", new Skip());
    }


    public static void updateSlashCommands(){
        final Guild zeGuild = jda.getGuilds().get(0);


//        updateSlashCommands(zeGuild);// for updating slash command only for above guild
//        updateSlashCommands(0); // for updating slash commands globally
    }

    private static void deleteSlashCommands(List<net.dv8tion.jda.api.interactions.commands.Command> commands){
        List<net.dv8tion.jda.api.interactions.commands.Command> commands1 = commands;

        for(net.dv8tion.jda.api.interactions.commands.Command command: commands1){
            if(command.getType() == net.dv8tion.jda.api.interactions.commands.Command.Type.SLASH)
                command.delete().queue();
        }
    }


    private static void updateSlashCommands(Guild guild){
        Guild guild1 = guild;
        deleteSlashCommands((List<net.dv8tion.jda.api.interactions.commands.Command>) guild1.retrieveCommands().complete());

        for(Command command: commands.values()){
            guild1.upsertCommand(command.getSlashCommand()).queue();
        }

    }

    private static void updateSlashCommands(int global){
        deleteSlashCommands((List<net.dv8tion.jda.api.interactions.commands.Command>) jda.retrieveCommands().complete());

        for(Command command: commands.values()){
            jda.upsertCommand(command.getSlashCommand()).queue();
        }
    }



}
