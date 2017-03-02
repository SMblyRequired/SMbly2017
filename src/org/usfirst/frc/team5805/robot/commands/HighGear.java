package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Transmission;

import edu.wpi.first.wpilibj.command.Command;

public class HighGear extends Command {	
	public HighGear() {
		requires(Robot.transmission);
	}

	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void initialize() {
		Robot.transmission.setState(Transmission.Gearing.HIGH);
	}	
	
	protected void execute() {
		
	}
	
	protected void end() {
		Robot.transmission.setState(Transmission.Gearing.LOW);
	}
	
	protected void interrupted() {
		end();
	}
}