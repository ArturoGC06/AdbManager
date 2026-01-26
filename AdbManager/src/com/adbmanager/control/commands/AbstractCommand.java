package com.adbmanager.control.commands;

public abstract class AbstractCommand implements Command {

    protected final String name;
    protected final String shortcut;
    protected final String details;
    protected final String help;

    protected AbstractCommand(String name, String shortcut, String details, String help) {
        this.name = name;
        this.shortcut = shortcut;
        this.details = details;
        this.help = help;
    }

    protected boolean matchCommandName(String word) {
        return word.equalsIgnoreCase(name) || word.equalsIgnoreCase(shortcut);
    }

    @Override
    public String helpText() {
        return String.format("%s (%s): %s%n%s", name, shortcut, details, help);
    }
}