package com.xiaoace.mctokook.listener.minecraft;

import com.xiaoace.mctokook.McToKook;
import com.xiaoace.mctokook.settings.Settings;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import snw.jkook.entity.channel.Channel;
import snw.jkook.entity.channel.TextChannel;
import snw.kookbc.impl.KBCClient;

import java.util.concurrent.CompletableFuture;

public class OnPlayerMessage {

    static KBCClient kbcClient = McToKook.getKbcClient();

    @SubscribeEvent
    public void onChat(ServerChatEvent event){

        //我用 MinecraftServer#addScheduledTask 会让聊天消息有延迟
        //等一个佬的回答

        CompletableFuture.runAsync(() ->{

            String messageString = event.getMessage();
            String playerName = event.getUsername();

            String needFormatString = "玩家: %s 说: %s";

            String formattedMessage = String.format(needFormatString,playerName,messageString);

            Channel channel =  kbcClient.getCore().getHttpAPI().getChannel(Settings.channel_ID);

            if (channel instanceof TextChannel){
                TextChannel textChannel = (TextChannel) channel;
                textChannel.sendComponent(formattedMessage);
            }
        });
    }

}
