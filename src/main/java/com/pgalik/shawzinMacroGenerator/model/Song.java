package com.pgalik.shawzinMacroGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private String scale;
    private List<Note> notes;
    private String title;
}
