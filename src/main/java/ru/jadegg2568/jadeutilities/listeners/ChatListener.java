package ru.jadegg2568.jadeutilities.listeners;

import net.minecraft.util.text.*;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.jadegg2568.jadeutilities.Main;
import ru.jadegg2568.jadeutilities.managers.ChatManager;
import ru.jadegg2568.jadeutilities.util.TextSerializer;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class ChatListener {

    @SubscribeEvent
    public static void onChat(ClientChatReceivedEvent e) {
        ITextComponent textComponent = e.getMessage();
        if (textComponent == null) {
            return;
        }
        String msg = TextSerializer.deserialize(textComponent.getUnformattedText().replace("\n", ""));
        String result = ChatManager.handleMessage(msg);

        if (result == null) {
            e.setMessage(new TextComponentString(TextSerializer.serialize("")));
            e.setResult(Event.Result.DENY);
            e.setCanceled(true);
        } else if (!result.equals(msg)) {
            e.setMessage(new TextComponentString(TextSerializer.serialize(result)));
        }
    }
}
