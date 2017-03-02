package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveADistance extends Command {
	public DriveADistance(double distance) {
		requires(Robot.driveTrain);
		//Play with sensors when we get a change to convert rotation into meters...
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.driveTrain.driveDriveTrain(-0.5, -0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		// distance <= Current distance, maybe incorporate pid loop to make results more accurate?? Same result if we take off coast??
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.driveDriveTrain(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}
}
