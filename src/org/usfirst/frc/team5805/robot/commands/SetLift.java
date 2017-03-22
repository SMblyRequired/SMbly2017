package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

public class SetLift extends Command {
	private Lift.Direction dir;
//	private double scalerAdj;
	
	public SetLift(Lift.Direction dir/*, double scalerAdj*/) {
		requires(Robot.lift);
		this.dir = dir;
//		this.scalerAdj = scalerAdj;
	}
	
	protected void initialize() {

	}
	
	protected void execute() {
		Robot.lift.move(dir/*, scalerAdj*/);
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
