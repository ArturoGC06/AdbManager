package com.adbmanager.logic.client;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AdbClient {
	
	private final String adbPath;
	private final Duration timeout;

	public AdbClient(String adbPath, Duration timeout) {
        this.adbPath = Objects.requireNonNull(adbPath);
        this.timeout = Objects.requireNonNull(timeout);
    }

    public AdbResult run(List<String> args) throws Exception {
        List<String> cmd = new ArrayList<>();
        cmd.add(adbPath);
        cmd.addAll(args);

        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);

        Process p = pb.start();

        String output;
        try (InputStream is = p.getInputStream()) {
            output = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }

        boolean finished = p.waitFor(timeout.toMillis(), TimeUnit.MILLISECONDS);
        if (!finished) {
            p.destroyForcibly();
            return new AdbResult(-1, output + "\n[TIMEOUT]");
        }
        return new AdbResult(p.exitValue(), output);
    }
}
