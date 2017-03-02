package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.GearManipulator;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import edu.wpi.first.wpilibj.command.Command;

public class OpenGearManipulator extends Command {
	public OpenGearManipulator() {
		requires(Robot.gearManip);
		setInterruptible(false);
	}

	protected void execute() {
		
	}
	
	protected void end() {

	}
	
	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void initialize() {
		System.out.println("Extending");
		Robot.gearManip.setState(GearManipulator.State.EXTEND);
	}
}
