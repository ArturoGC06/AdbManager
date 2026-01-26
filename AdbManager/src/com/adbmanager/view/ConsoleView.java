package com.adbmanager.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void showPrompt() {
        System.out.print("ADB Manager> ");
    }

    public String readLine() throws IOException {
        return in.readLine(); // null si EOF
    }

    public void show(String msg) {
        if (msg != null && !msg.isBlank()) System.out.println(msg);
    }

    public void showError(String msg) {
        System.err.println("[ERROR] " + msg);
    }
}
