package com.adbmanager.control;

public record CommandResult (boolean exitRequested, String output) {
    public static CommandResult ok(String output) { return new CommandResult(false, output); }
    public static CommandResult exit() { return new CommandResult(true, null); }
}
