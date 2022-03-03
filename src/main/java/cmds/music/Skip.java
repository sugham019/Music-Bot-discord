package cmds.music;

import cmds.music.lavaplayer.GuildMusicManager;
import cmds.music.lavaplayer.PlayerManager;
import core.Command;
import core.Info;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Skip implements Command {
    @Override
    public void call(SlashCommandInteraction event) {
        if(PlayerManager.vcCheck(event)){
            int no = event.getOption("no").getAsInt();

            final GuildMusicManager musicManager = PlayerManager.instance.getGuildAudioPlayer(event.getGuild());

            try {
                for (int i = 0; i < no; i++) {
                    musicManager.scheduler.nextTrack();
                }
                event.reply("Skipped tracks : "+ no).queue();
            }catch (Exception e){
                event.reply("Invalid no of tracks").queue();
            }
        }
    }

    @Override
    public CommandData getSlashCommand() {
        return Commands.slash("skip", "Skip song")
                .addOption(OptionType.INTEGER, "no", "No of songs you wanna skip");
    }

    @Override
    public Info getInfo() {
        return null;
    }
}
