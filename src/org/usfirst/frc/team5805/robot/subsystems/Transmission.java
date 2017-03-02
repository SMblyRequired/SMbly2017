package org.usfirst.frc.team5805.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Transmission extends Subsystem {
	public enum Gearing {
		HIGH,
		LOW
	}
	
	DoubleSolenoid gearPneumatic;
	
	public Transmission() {
		gearPneumatic = new DoubleSolenoid(0,1); 
	}
	
	public DoubleSolenoid getSolenoid() {
		return gearPneumatic;
	}
	
	public Gearing getState() {
		return (gearPneumatic.get() == Value.kForward  ? Gearing.HIGH : Gearing.LOW);
	}
	
	public void setState(Gearing gear) {
		gearPneumatic.set((gear == Gearing.HIGH ? Value.kForward : Value.kReverse));
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
