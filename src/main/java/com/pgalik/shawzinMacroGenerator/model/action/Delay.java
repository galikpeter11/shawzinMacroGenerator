package com.pgalik.shawzinMacroGenerator.model.action;

public record Delay(Integer amount) implements Action {
    @Override
    public String toString() {
        return "Delay " + amount + " ms";
    }
}
