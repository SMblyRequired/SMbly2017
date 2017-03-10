package org.usfirst.frc.team5805.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	CameraServer axisCam;
	//CameraServer usbCamera;
	
	//forget this for FRC Competition, will work on it later
	
	
	public Camera(/*int in*/) {
/*		switch(in) {
		case 1:
*/			axisCam = CameraServer.getInstance();
			axisCam.addAxisCamera("Axi-Cam", "10.58.5.15");
/*			break;
		case 2:
//			usbCamera = CameraServer.getInstance();
//			usbCamera.addServer("Front", 0);
			break;
		}
*/	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
