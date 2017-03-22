package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.MathSM;
import org.usfirst.frc.team5805.robot.OI;
import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.RobotMap;
import org.usfirst.frc.team5805.robot.ToggleButton;

import edu.wpi.first.wpilibj.command.Command;

public class MoveWithJoystick extends Command {
	
	private ToggleButton directionToggle;
	
	
	public MoveWithJoystick() {
		requires(Robot.driveTrain);
		directionToggle = new ToggleButton(OI.dStick, RobotMap.X_BUTTON);
	}		
	// Called just before this Command runs the first time
	protected void initialize() {

	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//(r)right(Axis
		 
		// left nub on y axis on drive joystick (lNubY)
		// right num on y axis on drive joystick (rNubY)
		
		double driveSpeedMod = /* 1;*/MathSM.map(OI.dStick.getRawAxis(RobotMap.RIGHT_TRIGGER), 0.0, 1.0, 0.75, 1.0);	//toggle speed with right trigger
		
		double lNubY = OI.dStick.getRawAxis(RobotMap.RIGHT_X_AXIS) * driveSpeedMod;
		double rNubY = OI.dStick.getRawAxis(RobotMap.LEFT_Y_AXIS) * driveSpeedMod;

		if (directionToggle.getStatus()) {
			rNubY = -rNubY;
		}
		
		Robot.driveTrain.driveDriveTrain(lNubY, rNubY);
	}
	
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
}
