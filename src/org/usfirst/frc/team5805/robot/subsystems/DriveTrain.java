package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.RobotMap;
import org.usfirst.frc.team5805.robot.commands.MoveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	CANTalon frontLeft, frontRight, rearLeft, rearRight, intakeRight;
	RobotDrive myDrive;
	
	public DriveTrain () {
		super("Drive Train");

		frontLeft = new CANTalon(RobotMap.FRONTLEFT);
		frontRight = new CANTalon(RobotMap.DB_FRONTRIGHT);
		rearLeft = new CANTalon(RobotMap.REARLEFT); rearLeft.reverseSensor(true);
		rearRight = new CANTalon(RobotMap.REARRIGHT);

		myDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	
	public void driveDriveTrain(double lStickY, double rStickY){
		//myDrive.tankDrive(-rStickY, -lStickY); // Regular drive
			myDrive.arcadeDrive(-rStickY, lStickY);
	} 
	 
	public void setTurn(double turn) {
		myDrive.arcadeDrive(turn, 0.0);
	}
	
	public void stop() {
	//	myDrive.tankDrive(0, 0);
		myDrive.arcadeDrive(0,0);
	}
	
	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new MoveWithJoystick());
	}

}
