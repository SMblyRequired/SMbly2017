package org.usfirst.frc.team5805.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Vision extends Subsystem {
	
	Solenoid gearLED, boilerLED;
	
	public Vision() {
		gearLED = new Solenoid(6);
		boilerLED = new Solenoid(7);
	}
 
	public void setSolenoid(boolean value) {
		gearLED.set(value);
		boilerLED.set(value);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
