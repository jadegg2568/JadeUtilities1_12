package ru.jadegg2568.jadeutilities.commands;

import com.google.common.collect.Lists;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import ru.jadegg2568.jadeutilities.managers.ChatManager;
import ru.jadegg2568.jadeutilities.managers.FileManager;
import ru.jadegg2568.jadeutilities.util.PlayerUtils;
import ru.jadegg2568.jadeutilities.util.TextSerializer;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ChatUtilCommand implements ICommand {

    @Override
    public String getName() {
        return "chatutil";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "chatutil <utility>";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("chatutil", "chatmanager");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sendMessageHelp(sender);
            return;
        }
        String utility = args[0];
        switch (utility) {
            case "autochat":
                ChatManager.AUTO_CHAT = !ChatManager.AUTO_CHAT;
                if (ChatManager.AUTO_CHAT)
                    sender.sendMessage(new TextComponentString(TextSerializer.serialize("§eАвто-Чат на заходы успешно §aвключен§e!")));
                else
                    sender.sendMessage(new TextComponentString(TextSerializer.serialize("§eАвто-Чат на заходы успешно §cвыключен§e.")));
                PlayerUtils.playSuccessSound();
                break;
            case "load":
                if (args.length < 2) {
                    sender.sendMessage(new TextComponentString(TextSerializer.serialize("§cОшибка: Введите название файла.")));
                    return;
                }
                String fileName = args[1];
                File file = new File("jadeutilities/" + fileName);
                if (!file.exists()) {
                    sender.sendMessage(new TextComponentString(TextSerializer.serialize("§cОшибка: Файл не найден.")));
                    break;
                }
                ChatManager.AUTO_CHAT_DATA = FileManager.readAutoChatData(file);
                sender.sendMessage(new TextComponentString(TextSerializer.serialize("§eУспешно загружен файл с авто-чатом!")));
                for (String triggerMessage : ChatManager.AUTO_CHAT_DATA.keySet())
                    sender.sendMessage(new TextComponentString(TextSerializer.serialize("§e- §f" + triggerMessage + " §e:: §f" + String.join("||", ChatManager.AUTO_CHAT_DATA.get(triggerMessage)))));
                PlayerUtils.playSuccessSound();
                break;
            default:
                sendMessageHelp(sender);
                PlayerUtils.playFailedSound();
                break;
        }
    }

    public void sendMessageHelp(ICommandSender sender) {
        sender.sendMessage(new TextComponentString(TextSerializer.serialize("§e/chatutil autochat§f - включить/выключить авто-чат.")));
        sender.sendMessage(new TextComponentString(TextSerializer.serialize("§e/chatutil load§f <файл> - загрузить файл авто-чата из папки jadeutilities.")));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return Lists.newArrayList("autochat", "load");
        } else if (args.length == 2) {
            if (args[0].equals("autochat")) {
                return Lists.newArrayList();
            } else {
                File autoChatFilesFolder = new File("jadeutilities/");
                String[] files = autoChatFilesFolder.list((dir, name) -> name.endsWith(".txt"));
                if (files == null)
                    return Lists.newArrayList();
                return Lists.newArrayList(files);
            }
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
