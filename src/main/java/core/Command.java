package core;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface Command {

    public void call(SlashCommandInteraction event);

    public Info getInfo();

}
