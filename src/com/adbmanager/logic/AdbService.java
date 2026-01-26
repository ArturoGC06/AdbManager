package com.adbmanager.logic;

import java.util.List;

import com.adbmanager.logic.client.AdbClient;
import com.adbmanager.logic.client.AdbResult;
import com.adbmanager.logic.model.Device;
import com.adbmanager.logic.model.DeviceParser;

public class AdbService implements AdbModel {

    private final AdbClient client;
    private final DeviceParser parser = new DeviceParser();
    private List<Device> devices = List.of();

    public AdbService(AdbClient client) {
        this.client = client;
    }

    @Override
    public void refreshDevices() throws Exception { // 
        AdbResult res = client.run(List.of("devices", "-l"));
        if (!res.ok()) throw new Exception("adb devices -l failed:\n" + res.output());
        devices = parser.parseDevices(res.output());
    }

    @Override
    public List<Device> getDevices() {
        return devices;
    }
}