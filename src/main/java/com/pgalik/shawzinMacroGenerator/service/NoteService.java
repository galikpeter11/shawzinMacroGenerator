package com.pgalik.shawzinMacroGenerator.service;

import com.pgalik.shawzinMacroGenerator.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private static final String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private static Integer base64ToInt(Character c) {
        var index = base64Chars.indexOf(c);
        if (index < 0) {
            throw new UnsupportedOperationException("Invalid character: \"" + c + "\"");
        }
        return index;
    }

    public Note fromString(final String code) {
        // sanity check
        if (code.length() != 3) {
            throw new UnsupportedOperationException("Invalid note code: \"" + code + "\"");
        }
        // split three base64 chars and convert to ints
        int noteInt = base64ToInt(code.charAt(0));
        int measure = base64ToInt(code.charAt(1));
        int tick = base64ToInt(code.charAt(2));

        // bits 1-3 of the note int are the strings
        String string = "";
        if ((noteInt & (1L)) != 0) string += "1";
        if ((noteInt & (1L << 1)) != 0) string += "2";
        if ((noteInt & (1L << 2)) != 0) string += "3";

        String fret = "";
        if ((noteInt & (1L << 3)) != 0) fret += "1";
        if ((noteInt & (1L << 4)) != 0) fret += "2";
        if ((noteInt & (1L << 5)) != 0) fret += "3";

        int time = (measure * 64) + tick;

        return new Note(string, fret, time);
    }
}
