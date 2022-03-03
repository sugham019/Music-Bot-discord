package cmds.music;

import cmds.music.lavaplayer.GuildMusicManager;
import cmds.music.lavaplayer.PlayerManager;
import core.Command;
import core.Info;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Stop implements Command {
    @Override
    public void call(SlashCommandInteraction event) {
        GuildMusicManager musicManager = PlayerManager.instance.getGuildAudioPlayer(event.getGuild());

        if (PlayerManager.vcCheck(event)){
            musicManager.player.destroy();
            event.getGuild().getAudioManager().closeAudioConnection();

            event.reply("Disconnected").queue();
        }
    }

    @Override
    public CommandData getSlashCommand() {
        return Commands.slash("stop", "Stop playing song and disconnect the bot");
    }

    @Override
    public Info getInfo() {
        return null;
    }
}
