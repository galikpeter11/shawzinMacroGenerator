package com.pgalik.shawzinMacroGenerator.service;

import com.pgalik.shawzinMacroGenerator.model.Note;
import com.pgalik.shawzinMacroGenerator.model.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public final class SongService {
    private static final Map<Character, String> scaleMap = Map.of(
            '1', "Pentatonic Minor",
            '2', "Pentatonic Major",
            '3', "Chromatic",
            '4', "Hexatonic",
            '5', "Major",
            '6', "Minor",
            '7', "Hirajoshi",
            '8', "Phrygian",
            '9', "Yo"
    );
    private final NoteService noteService;
    @Value("${app.song.defaultTitle}")
    private String defaultTitle;

    public SongService(NoteService noteService) {
        this.noteService = noteService;
    }

    public Song createSongFromString(final String code) {
        return createSongFromString(code, "No title.");
    }

    public Song createSongFromString(final String code, final String title) {
        // should be 3n+1 chars long
        if (code.length() % 3 != 1) {
            throw new UnsupportedOperationException("Code is an invalid length");
        }

        // pull out the scale code and map it
        Character scaleCode = code.charAt(0);
        final String scale = scaleMap.getOrDefault(scaleCode, "");
        if (scale.isEmpty()) {
            throw new UnsupportedOperationException("Invalid scale code: \"" + scaleCode + "\"");
        }

        // note array
        // pull out each three-char substring and parse it into a note string, fret, and time
        final List<Note> notes = new ArrayList<>();
        for (var i = 1; i < code.length(); i += 3) {
            notes.add(noteService.fromString(code.substring(i, i + 3)));
        }

        return new Song(scale, notes, title);
    }
}
