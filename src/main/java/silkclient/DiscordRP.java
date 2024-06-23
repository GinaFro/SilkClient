package silkclient;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.DiscordRichPresence.Builder;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {
    private boolean running = true;
    private long created = 0;

    public void start() {

        this.created = System.currentTimeMillis();
        this.running = true;

        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {

            @Override
            public void apply(DiscordUser user) {
                System.out.println("Welcome " + user.username);
                update("Booting up...." , "");
            }
        }).build();

        DiscordRPC.discordInitialize("1192461573382471680", handlers, true);

        new Thread("Discord RPC Callback") {

            @Override
            public void run(){

                while(running) {
                    DiscordRPC.discordRunCallbacks();
                }

            };

        }.start();
    }

    public void shutdown() {
        this.running = false;
        DiscordRPC.discordShutdown();
    }

    public void update(String firstLine , String secondLine) {
        DiscordRichPresence.Builder b = new Builder(secondLine);
        b.setBigImage("large", "");
        b.setDetails(firstLine);
        b.setStartTimestamps(created);

        DiscordRPC.discordUpdatePresence(b.build());
    }


}
