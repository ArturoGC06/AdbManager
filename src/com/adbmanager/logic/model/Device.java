package com.adbmanager.logic.model;

public record Device(
	    String serial,
	    String state,
	    String product,
	    String model,
	    String device,
	    String transportId
	) {}