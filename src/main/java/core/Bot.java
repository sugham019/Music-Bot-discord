package core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

import javax.security.auth.login.LoginException;

public class Bot {
    static JDA jda;

    static JDA getJda(String token) throws LoginException {
        return JDABuilder.createDefault(token)
                .addEventListeners(new SlashCmdsHandler(""))
                .build();
    }

    static void upsertSlashCommands(){
        final Guild zeGuild = jda.getGuildById("921764492990373939");

        zeGuild.upsertCommand("test", "Testing..");
    }

    public static void main(String[] args){

        try {
            jda = getJda(args[0]);
            jda.awaitReady();
        } catch (Exception e) {
            e.printStackTrace();
        }

        upsertSlashCommands();
    }
}
