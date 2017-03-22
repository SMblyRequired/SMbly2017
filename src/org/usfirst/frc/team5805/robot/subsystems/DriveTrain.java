package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.RobotMap;
import org.usfirst.frc.team5805.robot.commands.MoveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	public CANTalon frontLeft, frontRight, rearLeft, rearRight, intakeRight;
	RobotDrive myDrive;
	
	public DriveTrain () {
		super("Drive Train");

		frontLeft = new CANTalon(RobotMap.FRONTLEFT); // PBot Encoder
		frontRight = new CANTalon(RobotMap.FRONTRIGHT); // Encoder
		rearLeft = new CANTalon(RobotMap.REARLEFT); // Encoder
		rearRight = new CANTalon(RobotMap.REARRIGHT);


		
		
		// Encoder setup
		frontRight.reverseSensor(true);
		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRight.configEncoderCodesPerRev(1440 * 3);
		frontRight.setPosition(0.0);
		
		rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearLeft.configEncoderCodesPerRev(1440 * 3);
		rearLeft.setPosition(0.0);
		
		myDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	
	public void resetEncPos() {
		frontRight.setPosition(0.0);
		rearLeft.setPosition(0.0);
	}
	
	public double getLeftPos() {
		return rearLeft.getEncPosition();
	}
	
	public double getRightPos() {
		return frontRight.getEncPosition();
	}
	
	public void arcadeDrive(double forward, double turn) {
		myDrive.arcadeDrive(forward, -turn);
	}
	
	public void driveDriveTrain(double lStickY, double rStickY){
		//	myDrive.tankDrive(-rStickY, -lStickY); // Regular drive
			myDrive.arcadeDrive(-rStickY, lStickY);
	}  
	 
	public void setTurn(double turn) {
		myDrive.arcadeDrive(0, turn);
	}
	
	public void setCurve(double curPIDval, double turn) {
		myDrive.arcadeDrive(curPIDval, turn);
	}
	public void stop() {
	//	myDrive.tankDrive(0, 0);
		myDrive.arcadeDrive(0,0);
	}
	
	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new MoveWithJoystick());
	}

	public double getLeftVel() {
		return frontLeft.getEncVelocity();
	}
	
	public double getRightVel() {
		return frontRight.getEncVelocity();
	}
	

	public CANTalon getLeftController() {
		return rearLeft;
	}
	
	public CANTalon getRightController() {
		return frontRight;
	}

	public void tankDrive(double left, double right) {
		// TODO Auto-generated method stub
		myDrive.tankDrive(left, right);
	}


}
