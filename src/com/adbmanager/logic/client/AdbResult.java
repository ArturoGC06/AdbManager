package com.adbmanager.logic.client;

public record AdbResult(int exitCode, String output) {
    public boolean ok() { return exitCode == 0; }
}
