package com.adbmanager.control.commands;

import com.adbmanager.control.CommandResult;
import com.adbmanager.logic.AdbModel;

public class ExitCommand extends NoParamsCommand {

    public ExitCommand() {
        super("exit", "e", "Salir del programa", "Uso: exit");
    }
    
    @Override
    public CommandResult execute(AdbModel model) {
        return CommandResult.exit();
    }
}