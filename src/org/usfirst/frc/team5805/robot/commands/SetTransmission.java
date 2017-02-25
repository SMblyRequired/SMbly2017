package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class SetTransmission extends Command {
	public static final int HIGH = 0, LOW = 1;

	public int command;
	
	public SetTransmission() {
		requires(Robot.transmission);
	}

	protected void execute() {		
		if (Robot.oi.dStick.getRawButton(6)) {
			Robot.transmission.setSolenoid(DoubleSolenoid.Value.kForward);
		} else {
			Robot.transmission.setSolenoid(DoubleSolenoid.Value.kReverse);
		}
	}
	
	protected void end() {
		Robot.transmission.setSolenoid(DoubleSolenoid.Value.kOff);
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
