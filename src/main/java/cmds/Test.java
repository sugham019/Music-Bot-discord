package cmds;

import core.Command;
import core.Info;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public class Test implements Command {

    @Override
    public void call(SlashCommandInteraction event) {
        event.reply("Testing").queue();
    }

    @Override
    public Info getInfo() {
        return null;
    }
}
