package org.usfirst.frc.team5805.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ToggleButton {
	private Joystick controller;
	private int button;
	private boolean status;
	private long lastPress;
	
	public ToggleButton(Joystick controller, int button) {
		this.controller = controller;
		this.button = button;
		
		status = false;
	}
	
	public boolean getStatus() {
		if (controller.getRawButton(button) && lastPress + 250 < System.currentTimeMillis()){
			status = !status;
			lastPress = System.currentTimeMillis();
		}
		return status;
	}
}
