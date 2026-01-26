package com.adbmanager.control.commands;

import com.adbmanager.control.CommandResult;
import com.adbmanager.exceptions.CommandExecuteException;
import com.adbmanager.logic.AdbModel;

public class HelpCommand extends NoParamsCommand {

    public HelpCommand() {
        super("help", "h", "Muestra ayuda", "Uso: help");
    }

    @Override
    public CommandResult execute(AdbModel model) {
        return CommandResult.ok(CommandGenerator.helpText());
    }
}