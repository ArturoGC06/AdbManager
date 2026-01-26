package com.adbmanager.control.commands;

import com.adbmanager.exceptions.CommandParseException;

public abstract class NoParamsCommand extends AbstractCommand {

    protected NoParamsCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public Command parse(String[] words) throws CommandParseException {
        if (words.length > 1 && matchCommandName(words[0])) {
            throw new CommandParseException("Este comando no admite parámetros.");
        }
        if (words.length == 1 && matchCommandName(words[0])) {
            return this; // el “this” es HelpCommand o ExitCommand, según cuál sea
        }
        return null; // no es este comando
    }
}