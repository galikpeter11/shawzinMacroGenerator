package com.pgalik.shawzinMacroGenerator.service;

import com.pgalik.shawzinMacroGenerator.model.Macro;
import com.pgalik.shawzinMacroGenerator.model.Song;
import com.pgalik.shawzinMacroGenerator.model.action.Action;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MacroService {
    private final SongService songService;
    private final ActionService actionService;
    @Value("${app.song.defaultDescription:}")
    private String defaultDescription;

    public MacroService(SongService songService, ActionService actionService) {
        this.songService = songService;
        this.actionService = actionService;
    }

    public Macro createMacroFromString(final String shawzinString) {
        final Song song = songService.createSongFromString(shawzinString);
        List<Action> actions = actionService.createActionsFromSong(song);
        return new Macro(song.getTitle(), defaultDescription, actions);
    }
}
