package com.pgalik.shawzinMacroGenerator.model;

import java.util.List;

public record Song(
        String scale,
        List<Note> notes,
        String title
) {
}
