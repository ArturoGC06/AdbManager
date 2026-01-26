package com.adbmanager.exceptions;

public class CommandExecuteException extends CommandException {
    public CommandExecuteException(String message) { super(message); }
    public CommandExecuteException(String message, Throwable cause) { super(message, cause); }
}