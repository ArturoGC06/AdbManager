package com.adbmanager.logic;

import java.util.List;

import com.adbmanager.logic.model.Device;

public interface AdbModel {
    void refreshDevices() throws Exception;
    List<Device> getDevices();
}