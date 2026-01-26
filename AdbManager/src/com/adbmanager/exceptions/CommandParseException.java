package com.adbmanager.exceptions;

public class CommandParseException extends CommandException {
    public CommandParseException(String message) { super(message); }
    public CommandParseException(String message, Throwable cause) { super(message, cause); }
}