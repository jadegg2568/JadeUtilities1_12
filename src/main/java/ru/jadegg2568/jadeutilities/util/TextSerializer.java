package ru.jadegg2568.jadeutilities.util;

import javax.sound.sampled.AudioFormat;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TextSerializer {

    private static final Map<Character, Character> unicodeMap = new HashMap<>();

    static {
        unicodeMap.put('\u0430', 'а');
        unicodeMap.put('\u0410', 'А');
        unicodeMap.put('\u0431', 'б');
        unicodeMap.put('\u0411', 'Б');
        unicodeMap.put('\u0432', 'в');
        unicodeMap.put('\u0412', 'В');
        unicodeMap.put('\u0433', 'г');
        unicodeMap.put('\u0413', 'Г');
        unicodeMap.put('\u0434', 'д');
        unicodeMap.put('\u0414', 'Д');
        unicodeMap.put('\u0435', 'е');
        unicodeMap.put('\u0415', 'Е');
        unicodeMap.put('\u0451', 'ё');
        unicodeMap.put('\u0401', 'Ё');
        unicodeMap.put('\u0436', 'ж');
        unicodeMap.put('\u0416', 'Ж');
        unicodeMap.put('\u0437', 'з');
        unicodeMap.put('\u0417', 'З');
        unicodeMap.put('\u0438', 'и');
        unicodeMap.put('\u0418', 'И');
        unicodeMap.put('\u0439', 'й');
        unicodeMap.put('\u0419', 'Й');
        unicodeMap.put('\u043A', 'к');
        unicodeMap.put('\u041A', 'К');
        unicodeMap.put('\u043B', 'л');
        unicodeMap.put('\u041B', 'Л');
        unicodeMap.put('\u043C', 'м');
        unicodeMap.put('\u041C', 'М');
        unicodeMap.put('\u043D', 'н');
        unicodeMap.put('\u041D', 'Н');
        unicodeMap.put('\u043E', 'о');
        unicodeMap.put('\u041E', 'О');
        unicodeMap.put('\u043F', 'п');
        unicodeMap.put('\u041F', 'П');
        unicodeMap.put('\u0440', 'р');
        unicodeMap.put('\u0420', 'Р');
        unicodeMap.put('\u0441', 'с');
        unicodeMap.put('\u0421', 'С');
        unicodeMap.put('\u0442', 'т');
        unicodeMap.put('\u0422', 'Т');
        unicodeMap.put('\u0443', 'у');
        unicodeMap.put('\u0423', 'У');
        unicodeMap.put('\u0444', 'ф');
        unicodeMap.put('\u0424', 'Ф');
        unicodeMap.put('\u0445', 'х');
        unicodeMap.put('\u0425', 'Х');
        unicodeMap.put('\u0446', 'ц');
        unicodeMap.put('\u0426', 'Ц');
        unicodeMap.put('\u0447', 'ч');
        unicodeMap.put('\u0427', 'Ч');
        unicodeMap.put('\u0448', 'ш');
        unicodeMap.put('\u0428', 'Ш');
        unicodeMap.put('\u0449', 'щ');
        unicodeMap.put('\u0429', 'Щ');
        unicodeMap.put('\u044A', 'ъ');
        unicodeMap.put('\u042A', 'Ъ');
        unicodeMap.put('\u044B', 'ы');
        unicodeMap.put('\u042B', 'Ы');
        unicodeMap.put('\u044C', 'ь');
        unicodeMap.put('\u042C', 'Ь');
        unicodeMap.put('\u044D', 'э');
        unicodeMap.put('\u042D', 'Э');
        unicodeMap.put('\u044E', 'ю');
        unicodeMap.put('\u042E', 'Ю');
        unicodeMap.put('\u044F', 'я');
        unicodeMap.put('\u042F', 'Я');
    }

    public static String serialize(String text) {
        String newText = text;
        for (char value : unicodeMap.keySet()) {
            char ch = unicodeMap.get(value);
            if (newText.contains(String.valueOf(ch)))
                newText = newText.replace(ch, value);
        }
        return newText;
    }

    public static String deserialize(String text) {
        new String(text.getBytes(), StandardCharsets.UTF_8);
        return text;
    }

    public static String fromJsonTextChar(char ch) {
        switch (String.valueOf(ch)) {
            default: return String.valueOf(ch);
        }
    }
}
