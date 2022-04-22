package com.pgalik.shawzinMacroGenerator.model.action;

public record KeyDown(Integer keyIdentifier) implements Action {
    @Override
    public String toString() {
        return "KeyDown " + keyIdentifier + " 1";
    }
}
