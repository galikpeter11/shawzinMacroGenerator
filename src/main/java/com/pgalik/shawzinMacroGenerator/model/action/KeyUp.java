package com.pgalik.shawzinMacroGenerator.model.action;

public record KeyUp(Integer keyIdentifier) implements Action {
    @Override
    public String toString() {
        return "KeyUp " + keyIdentifier + " 1";
    }
}
