package cmds.music.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.HashMap;
import java.util.Map;

import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MESSAGES;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_VOICE_STATES;

public class PlayerManager{
    public static PlayerManager instance;
    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;

    public PlayerManager() {
        this.musicManagers = new HashMap<>();

        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    public synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
        long guildId = Long.parseLong(guild.getId());
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }


    public void loadAndPlay(SlashCommandInteraction event, final String trackUrl) {
        String song = trackUrl;
        GuildMusicManager musicManager = getGuildAudioPlayer(event.getGuild());

        if (!song.contains("https://www.youtube.com/")){
            song = "ytsearch: " + song;
        }
        playerManager.loadItemOrdered(musicManager, song, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                event.reply("Adding to queue " + track.getInfo().title).queue();
                musicManager.scheduler.queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();
                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                event.reply("Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")").queue();

                musicManager.scheduler.queue(firstTrack);
            }

            @Override
            public void noMatches() {
                event.reply("Nothing found by " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                event.reply("Could not play: " + exception.getMessage()).queue();
            }
        });
    }






    public static boolean vcCheck(SlashCommandInteraction event){
        final Member member = event.getMember();
        final Member bot = event.getGuild().getSelfMember();
        boolean Return = true;

        if(member.getVoiceState().getChannel() == null) {
            event.reply("You need to be in voice channel to use this command");
            Return = false;

        }else if(bot.getVoiceState().getChannel() == null){
            final AudioChannel channel = event.getMember().getVoiceState().getChannel();

            event.getGuild().getAudioManager().openAudioConnection(channel);

        }else if(bot.getVoiceState().getChannel() != member.getVoiceState().getChannel()){
            event.reply("We need to be in same voice channel");
            Return = false;
        }
        return Return;

    }

}