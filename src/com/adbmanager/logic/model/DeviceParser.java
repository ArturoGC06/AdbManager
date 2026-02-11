package com.adbmanager.logic.model;

import java.util.ArrayList;
import java.util.List;

import com.adbmanager.view.Messages;

public class DeviceParser {
	
	public List<Device> parseDevices(String output){ // TODO implemtentar exepción de parse
		List<Device> deviceList = new ArrayList<>();
		List<String> lines = output.lines().toList();
		if(lines.get(0).equals(Messages.ADB_DEVICES_LIST)) {
			if(lines.size()>1){
				int i = 1;
				while(i<lines.size() && lines.get(i).contains("*")) i++;
			while(i<lines.size() && !lines.get(i).isEmpty()) {
				    String currentLine = lines.get(i).trim();
				    String[] deviceInfo = currentLine.split("\\s+");
				    
				    String serial = deviceInfo[0];
				    String state = deviceInfo[1];
				    
//				    boolean deviceAdded = deviceList.stream().anyMatch(d -> d.serial().equals(serial))
//				    && deviceList.stream().anyMatch(d -> d.serial().equals(serial));
					    /* es lo mismo que hacer:
					    boolean deviceAdded = false;
					    for (Device d : deviceList) {
					        if (d.serial().equals(serial)) {
					            existe = true;
					            break; // Cortocircuito manual
					        }
					    }*/

//				    if (!deviceAdded) { comprobacion inútil de momento pues se hace una lista nueva cada vez
				    	if(state.equals(Messages.STATUS_CONNECTED)) {
				    		String product = deviceInfo[2].split(":")[1];
				    		String model = deviceInfo[3].split(":")[1];
				    		String device = deviceInfo[4].split(":")[1];
				    		String transportId = deviceInfo[5].split(":")[1];
					        deviceList.add(new Device(serial, state, product, model, device, transportId));
				    	} else deviceList.add(new Device(serial, state, null, null, null, null));
//				    }
				    i++;
				}
			}
		}
		return deviceList;
	}
}
