package com.adbmanager.control;

import java.io.IOException;

import com.adbmanager.control.commands.Command;
import com.adbmanager.control.commands.CommandGenerator;
import com.adbmanager.exceptions.CommandException;
import com.adbmanager.logic.AdbModel;
import com.adbmanager.view.ConsoleView;

public class Controller {

    private final AdbModel model;
    private final ConsoleView view;

    public Controller(AdbModel model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                view.showPrompt();
                String line = view.readLine();
                if (line == null) break; // EOF

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] words = line.split("\\s+");

                Command cmd = CommandGenerator.parse(words);
                CommandResult result = cmd.execute(model);

                if (result.output() != null) view.show(result.output());
                if (result.exitRequested()) running = false;

            } catch (CommandException e) {
                // Mensaje “principal”
                view.showError(e.getMessage());

                // Causas encadenadas (muy útil cuando envolvemos excepciones)
                Throwable cause = e.getCause();
                while (cause != null) {
                    view.showError(cause.getMessage());
                    cause = cause.getCause();
                }

            } catch (IOException e) {
                view.showError("Error leyendo de consola: " + e.getMessage());
                running = false;
            }
        }
    }
}