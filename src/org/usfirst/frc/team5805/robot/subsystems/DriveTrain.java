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
	
	public void driveDrive(double lStickY, double rStickY){
		myDrive.arcadeDrive(-rStickY, lStickY); // Regular drive
	}
	
	public void setTurn(double turn) {
		myDrive.arcadeDrive(turn, 0.0);
	}
	
	public void stop() {
		myDrive.arcadeDrive(0, 0);
	}
	
	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new MoveWithJoystick());
	}

}
