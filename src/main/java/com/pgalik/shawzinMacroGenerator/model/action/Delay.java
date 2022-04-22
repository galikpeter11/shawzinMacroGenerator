package com.pgalik.shawzinMacroGenerator.model.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delay implements Action {
    private Integer amount;

    @Override
    public String toString() {
        return "Delay " + amount + " ms";
    }
}
