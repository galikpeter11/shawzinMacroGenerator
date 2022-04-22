package com.pgalik.shawzinMacroGenerator.service;

import com.pgalik.shawzinMacroGenerator.model.Note;
import com.pgalik.shawzinMacroGenerator.model.Song;
import com.pgalik.shawzinMacroGenerator.model.action.Action;
import com.pgalik.shawzinMacroGenerator.model.action.Delay;
import com.pgalik.shawzinMacroGenerator.model.action.KeyDown;
import com.pgalik.shawzinMacroGenerator.model.action.KeyUp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ActionService {
    private static final Map<String, Integer> stringMap = Map.of(
            "1", 30,
            "2", 31,
            "3", 32
    );
    private static final Map<String, Integer> fretMap = Map.of(
            "1", 80,
            "2", 81,
            "3", 79
    );
    private String currentFret = "";
    @Value("${app.song.tickRate}")
    private Integer tickRate;

    public List<Action> createActionsFromSong(final Song song) {
        List<Action> actions = new ArrayList<>();

        for (int i = 1; i < song.getNotes().size(); i++) {
            List<Action> newActions = fromNotes(song.getNotes().get(i - 1), song.getNotes().get(i));
            actions.addAll(newActions);
        }
        currentFret = "";
        return actions;
    }

    private List<Action> fromNotes(final Note current, final Note next) {
        if (current == null) {
            throw new UnsupportedOperationException("The first note MUST be provided");
        }
        final List<Action> result = new ArrayList<>();

        //Handle fret(s)
        result.addAll(getFretActions(current));

        //Handle string(s)
        result.addAll(getStringActions(current));

        //Handle delay until next note (if present)
        result.addAll(getNextNoteActions(current, next));

        return result;
    }

    private List<Action> getFretActions(final Note current) {
        final List<Action> result = new ArrayList<>();
        if (!current.getFret().equals(currentFret)) {
            if (!currentFret.isEmpty()) {
                for (int i = 0; i < currentFret.length(); i++) {
                    char c = currentFret.charAt(i);
                    result.add(new KeyUp(fretMap.get(c + "")));
                }
            }
            if (!current.getFret().isEmpty()) {
                for (int i = 0; i < current.getFret().length(); i++) {
                    char c = current.getFret().charAt(i);
                    result.add(new KeyDown(fretMap.get(c + "")));
                }
            }
            currentFret = current.getFret();
        }
        return result;
    }

    private List<Action> getStringActions(final Note current) {
        final List<Action> result = new ArrayList<>();
        if (!current.getString().isEmpty())
            for (int i = 0; i < current.getString().length(); i++) {
                char c = current.getString().charAt(i);
                result.add(new KeyDown(stringMap.get(c + "")));
            }

        result.add(new Delay(16));

        if (!current.getString().isEmpty())
            for (int i = 0; i < current.getString().length(); i++) {
                char c = current.getString().charAt(i);
                result.add(new KeyUp(stringMap.get(c + "")));
            }
        return result;
    }

    private List<Action> getNextNoteActions(final Note current, final Note next) {
        final List<Action> result = new ArrayList<>();
        if (next != null) {
            result.add(new Delay((next.getTime() - current.getTime()) * tickRate));
        }

        if (next == null && !currentFret.isEmpty()) {
            result.add(new KeyUp(fretMap.get(currentFret)));
        }
        return result;
    }
}
