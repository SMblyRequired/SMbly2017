package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Provides a command for driving and turning a certain direction or number of degrees
 * @author Victor E
 */
public class DriveCurve extends Command {
	private double left;
	private double right;

	
	//travels about 3m/s at full speed
	
	public DriveCurve (double left, double right) {
		requires(Robot.driveTrain);
		this.left = -left;
		this.right = -right;
	}
	
	protected void initialize() {
		
		Robot.driveTrain.tankDrive(left, right);

		Robot.driveTrain.frontLeft.setVoltageRampRate(96);
		Robot.driveTrain.frontRight.setVoltageRampRate(96);
		Robot.driveTrain.rearLeft.setVoltageRampRate(96);
		Robot.driveTrain.rearRight.setVoltageRampRate(96);
	}
	
	protected void execute() {
		Robot.driveTrain.tankDrive(left, right);
	}

	protected void end() {
		Robot.driveTrain.stop();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}

