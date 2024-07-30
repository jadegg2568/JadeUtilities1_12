package ru.jadegg2568.jadeutilities.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.command.CommandTitle;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;

public class PlayerUtils {

    private static final Minecraft minecraft = Minecraft.getMinecraft();

    public static String getUsername() {
        return minecraft.player.getName();
    }

    public static void sendMessage(String msg) {
        if (minecraft.player == null) return;
        minecraft.player.sendChatMessage(msg);
    }

    public static void displayMessage(String msg) {
        minecraft.player.sendMessage(new TextComponentString(msg));
    }

    public static void playSound(SoundEvent sound) {
        minecraft.player.playSound(sound, 1, 1);
    }

    public static void playSuccessSound() {
        minecraft.player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }

    public static void playFailedSound() {
        minecraft.player.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1, 1);
    }
}
