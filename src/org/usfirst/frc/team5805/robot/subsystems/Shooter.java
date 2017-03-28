package org.usfirst.frc.team5805.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public CANTalon shooterMotor1, shooterMotor2;
	public DoubleSolenoid loader;
	
	public Shooter() {
		shooterMotor1 = new CANTalon(7);
		shooterMotor2 = new CANTalon(5);
		
		shooterMotor1.reverseSensor(true);
		shooterMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
 /*		shooterMotor1.configEncoderCodesPerRev(360);
		shooterMotor1.setForwardSoftLimit(15);
		shooterMotor1.setReverseSoftLimit(15);
*/	
		//shooterMotor1.SetVelocityMeasurementPeriod(CANTalon.VelocityMeasurementPeriod.Period_100Ms);
		
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
