package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetVisionLights extends Command {

	public SetVisionLights() {
		requires(Robot.vision);
	}

	protected void execute() {		
		Robot.vision.setSolenoid(true);
	}
	
	protected void end() {
		Robot.vision.setSolenoid(false);
	}
	
	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}
