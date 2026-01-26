package com.adbmanager.control.commands;

import com.adbmanager.exceptions.CommandExecuteException;
import com.adbmanager.exceptions.CommandParseException;
import com.adbmanager.control.CommandResult;
import com.adbmanager.logic.AdbModel;

public interface Command {
    Command parse(String[] words) throws CommandParseException;
    public CommandResult execute(AdbModel model) throws CommandExecuteException; // por ahora sin modelo/vista, lo simple
    String helpText();
}
