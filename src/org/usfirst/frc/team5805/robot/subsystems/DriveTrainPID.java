package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.RobotMap;
import org.usfirst.frc.team5805.robot.commands.MoveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DriveTrainPID extends PIDSubsystem {

	public DriveTrainPID(double p, double i, double d) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
	}
/*
	CANTalon frontLeft, frontRight, rearLeft, rearRight, intakeRight;
	RobotDrive myDrive;
	
	public DriveTrainPID () {
		super("Drive Train", 0.7, 0.0, 0.0);

		frontLeft = new CANTalon(RobotMap.FRONTLEFT); // PBot Encoder
		frontRight = new CANTalon(RobotMap.FRONTRIGHT); // Encoder
		rearLeft = new CANTalon(RobotMap.REARLEFT); // Encoder
		rearRight = new CANTalon(RobotMap.REARRIGHT);

		// Encoder setup
		frontRight.reverseSensor(true);
		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRight.configEncoderCodesPerRev(1440 * 3);
		frontRight.setPosition(0.0);
		
		frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontLeft.configEncoderCodesPerRev(1440 * 3);
		frontLeft.setPosition(0.0);
		
		myDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		
		setAbsoluteTolerance(0.2);
		getPIDController().setContinuous(false);
	}
	
	public void resetEncPos() {
		frontRight.setPosition(0.0);
		frontLeft.setPosition(0.0);
	}
	
	public void arcadeDrive(double forward, double turn) {
		myDrive.arcadeDrive(forward, -turn);
	}
	
	public void driveDriveTrain(double lStickY, double rStickY){
			myDrive.arcadeDrive(-rStickY, lStickY);
	}  
	 
	public void setTurn(double turn) {
		myDrive.arcadeDrive(0, turn);
	}
	
	public void stop() {
		myDrive.arcadeDrive(0,0);
	}
	
	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new MoveWithJoystick());
	}

	@Override
	protected double returnPIDInput() {
		return frontRight.getEncPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		myDrive.arcadeDrive(output, 0);
	}
*/

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
