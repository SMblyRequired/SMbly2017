package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class SetGearManipulator extends Command {

	public static final int EXTEND = 0, RETRACT = 1;
	
	private int command;
	
	public SetGearManipulator() {
		requires(Robot.gearManip);
	}

	protected void execute() {
		if (Robot.oi.dStick.getRawButton(4)) {
			Robot.gearManip.setSolenoid(DoubleSolenoid.Value.kForward);
		} else {
			Robot.gearManip.setSolenoid(DoubleSolenoid.Value.kReverse);
		}
	}
	
	protected void end() {
		Robot.gearManip.setSolenoid(DoubleSolenoid.Value.kOff);
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
