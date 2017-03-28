package org.usfirst.frc.team5805.robot.commands;

import java.io.IOException;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Transmission;

import edu.wpi.first.wpilibj.command.Command;



public class SetTrans extends Command {
	public enum transState {HIGH, LOW}
	int gear;
	
	public SetTrans(int gear) {
		requires(Robot.transmission);
		this.gear = gear;
	}
	
	protected void execute() {
		//0 high gear, 1 low gear
		Robot.transmission.setState((gear==1)?Transmission.Gearing.LOW:Transmission.Gearing.HIGH);
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
