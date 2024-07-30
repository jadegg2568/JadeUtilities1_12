package ru.jadegg2568.jadeutilities.managers;

import com.google.common.collect.Lists;
import ru.jadegg2568.jadeutilities.util.PlayerUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {

    public static void setup() {
        makeFileIfNotExists(new File("jadeutilities/autochat.txt"));
    }

    public static Map<String, List<String>> readAutoChatData(File file) {
        Map<String, List<String>> autoChatData = new HashMap<>();
        if (!file.exists()) {
            makeFile(file);
            return autoChatData;
        }
        List<String> lines = read(file);
        for (String line : lines) {
            if (line.startsWith("#") || !line.contains("::") || line.length() < 8) continue;
            String[] parts = line.split("::");
            String triggerMessage = parts[0].replace("%nickname%", PlayerUtils.getUsername());
            List<String> responses = Arrays.asList(parts[1].split("//"));
            autoChatData.put(triggerMessage, responses);
        }
        return autoChatData;
    }

    public static void makeFileIfNotExists(File file) {
        if (!file.exists())
            makeFile(file);
    }

    public static void makeFile(File file) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> read(File file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
