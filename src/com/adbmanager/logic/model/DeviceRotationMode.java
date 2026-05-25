package com.adbmanager.logic.model;

public enum DeviceRotationMode {
    AUTO("control.rotation.mode.auto", -1),
    PORTRAIT("control.rotation.mode.portrait", 0),
    LANDSCAPE_RIGHT("control.rotation.mode.landscapeRight", 1),
    PORTRAIT_INVERTED("control.rotation.mode.portraitInverted", 2),
    LANDSCAPE_LEFT("control.rotation.mode.landscapeLeft", 3);

    private final String messageKey;
    private final int adbValue;

    DeviceRotationMode(String messageKey, int adbValue) {
        this.messageKey = messageKey;
        this.adbValue = adbValue;
    }

    public String messageKey() {
        return messageKey;
    }

    public int adbValue() {
        return adbValue;
    }

    public boolean automatic() {
        return this == AUTO;
    }

    public static DeviceRotationMode fromAdbValues(int accelerometerRotation, int userRotation) {
        if (accelerometerRotation == 1) {
            return AUTO;
        }
        return switch (userRotation) {
            case 1 -> LANDSCAPE_RIGHT;
            case 2 -> PORTRAIT_INVERTED;
            case 3 -> LANDSCAPE_LEFT;
            default -> PORTRAIT;
        };
    }
}
