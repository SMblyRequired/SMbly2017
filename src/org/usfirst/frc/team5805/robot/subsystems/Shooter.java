package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	CANTalon shooterMotor1, shooterMotor2;
	
	public Shooter() {
		shooterMotor1 = new CANTalon(7);
		shooterMotor2 = new CANTalon(8);
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
