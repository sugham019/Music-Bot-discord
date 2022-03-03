package cmds.music;

import cmds.music.lavaplayer.PlayerManager;
import core.Command;
import core.Info;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.jetbrains.annotations.NotNull;

public class Play implements Command {
    @Override
    public void call(SlashCommandInteraction event) {
        if (PlayerManager.vcCheck(event)){
            PlayerManager.instance.loadAndPlay(event, event.getOption("song").getAsString());
        }
    }

    @Override
    public SlashCommandData getSlashCommand() {
        return Commands.slash("play", "Play song [ URL/Name ]!")
                .addOption(OptionType.STRING, "song", "Song Name or URL");
    }

    @Override
    public Info getInfo() {
        return null;
    }
}
