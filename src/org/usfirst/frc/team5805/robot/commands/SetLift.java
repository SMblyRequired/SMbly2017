package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class SetLift extends Command {
	private Lift.Direction dir;
	
	public SetLift(Lift.Direction _dir) {
		requires(Robot.lift);
		dir = _dir;
	}
	
	protected void initialize() {

	}
	
	protected void execute() {
		Robot.lift.move(dir);
	}
	
	protected void end() {
		Robot.lift.stop();
	}  
	
	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO: Stop when a sensor returns high
		return false;
	}

}
