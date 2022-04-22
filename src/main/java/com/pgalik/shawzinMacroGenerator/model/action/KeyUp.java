package com.pgalik.shawzinMacroGenerator.model.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyUp implements Action {
    private Integer keyIdentifier;

    @Override
    public String toString() {
        return "KeyUp " + keyIdentifier + " 1";
    }
}
