package com.pgalik.shawzinMacroGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private String string;
    private String fret;
    private Integer time;

}