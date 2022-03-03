package cmds.music;

import cmds.music.lavaplayer.GuildMusicManager;
import cmds.music.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import core.Command;
import core.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class List implements Command {
    @Override
    public void call(SlashCommandInteraction event) {
        final GuildMusicManager manager = PlayerManager.instance.getGuildAudioPlayer(event.getGuild());

        final AudioTrack currentlyPlaying = manager.player.getPlayingTrack();
        final BlockingQueue<AudioTrack> queuedTracks = manager.scheduler.getQueue();

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Track list");
        embedBuilder.setColor(Color.CYAN);

        embedBuilder.appendDescription("**Currently Playing : **" + currentlyPlaying.getInfo().title);
        embedBuilder.appendDescription("\n **Queue : **");
        int sn = 1;
        for (AudioTrack track : queuedTracks){
            embedBuilder.appendDescription("\n"+sn + ")" + track.getInfo().title);
            sn++;
        }
        event.replyEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public CommandData getSlashCommand() {
        return Commands.slash("list", "list of queued songs");
    }

    @Override
    public Info getInfo() {
        return null;
    }
}
