package core;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface Command {

    public void call(SlashCommandInteraction event);

    public CommandData getSlashCommand();

    public Info getInfo();

}
