package com.adbmanager;

import java.time.Duration;

import com.adbmanager.control.Controller;
import com.adbmanager.logic.AdbModel;
import com.adbmanager.logic.AdbService;
import com.adbmanager.logic.client.AdbClient;
import com.adbmanager.view.ConsoleView;

public class App {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

        AdbModel model = new AdbService(new AdbClient("adb", Duration.ofSeconds(60))) {
            // modelo vacío de momento
        };

        new Controller(model, view).run();
    }
}