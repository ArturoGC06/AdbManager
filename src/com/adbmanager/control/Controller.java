package com.adbmanager.control;

import java.io.IOException;
import java.util.List;

import com.adbmanager.logic.model.Device;
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
            	model.refreshDevices();
            	List<Device> deviceList = model.getDevices();
            	
            	// 2) Construye un texto “bonito”
            	StringBuilder sb = new StringBuilder();
            	sb.append("Dispositivos conectados: ").append(deviceList.size()).append("\n");

            	for (int i = 0; i < deviceList.size(); i++) {
            	    Device d = deviceList.get(i);
            	    String shownModel = (d.model() != null) ? d.model() : "(sin model)";
            	    sb.append(i).append(") ")
            	      .append(shownModel)
            	      .append("  [").append(d.state()).append("]  ")
            	      .append(d.serial())
            	      .append("\n");
            	}
            	
            	

            	view.show(sb.toString());
            	
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
            } catch (Exception e) { // excepcion generica del refresh TODO especificarla
                view.showError("Error al ejecutar el comando: " + e.getMessage());
                running = false;
            }
        }
    }
}