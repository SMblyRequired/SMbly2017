package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.commands.SetGearManipulator;
import org.usfirst.frc.team5805.robot.commands.SetTransmission;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Transmission extends Subsystem {
	
	DoubleSolenoid gearPneumatic;
	
	public Transmission() {
		gearPneumatic = new DoubleSolenoid(0,1); 
	}
	
	public void setSolenoid(DoubleSolenoid.Value value) {
		gearPneumatic.set(value);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SetTransmission());
		
	}
}
