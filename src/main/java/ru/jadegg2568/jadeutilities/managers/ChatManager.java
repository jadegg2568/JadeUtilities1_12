package ru.jadegg2568.jadeutilities.managers;

import net.minecraft.init.SoundEvents;
import ru.jadegg2568.jadeutilities.util.PlayerUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChatManager {

    public static Map<String, List<String>> AUTO_CHAT_DATA = new HashMap<>();

    public static boolean AUTO_CHAT = false;

    public static String handleMessage(String msg) {
        if (msg.contains("■") || msg.contains("www.mineblaze.net"))
            return null;
        handleAutoChatMessage(msg);
        return msg;
    }

    private static void handleAutoChatMessage(String msg) {
        for (String triggerMessage : AUTO_CHAT_DATA.keySet()) {
            List<String> responses = AUTO_CHAT_DATA.get(triggerMessage);
            if (responses == null || responses.isEmpty()) continue;
            if (startsWith(msg, triggerMessage)) {
                String randomResponse = responses.get(new Random().nextInt(responses.size()));
                String[] strs = msg.split(" ");
                for (int i = 0; i < strs.length; i++) {
                    if (randomResponse.contains("%str" + i + "%"))
                        randomResponse = randomResponse.replace("%str" + i + "%", strs[i]);
                }
                PlayerUtils.sendMessage(randomResponse);
                return;
            }
        }
    }

    private static boolean startsWith(String msg, String with) {
        String[] msgSplit = msg.split(" ");
        String[] withSplit = with.split(" ");
        if (msgSplit.length < withSplit.length)
            return false;
        for (int i = 0; i < withSplit.length; i++) {
            String word1 = msgSplit[i];
            String word2 = withSplit[i];
            if (!word2.equals("*") && !word1.equals(word2))
                return false;
        }
        return true;
    }
}