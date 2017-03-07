package org.usfirst.frc.team5805.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public CANTalon shooterMotor1, shooterMotor2;
	public DoubleSolenoid loader;
	
	public Shooter() {
		shooterMotor1 = new CANTalon(7);
		shooterMotor2 = new CANTalon(5);
		
		shooterMotor2.reverseSensor(true);
		shooterMotor2.configEncoderCodesPerRev(3 * 1024);
		
		loader = new DoubleSolenoid(4, 5);
	}
	  
	public void setShooterSpeed(double speed) {
		shooterMotor1.set(speed);
		shooterMotor2.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
		
	}
}
