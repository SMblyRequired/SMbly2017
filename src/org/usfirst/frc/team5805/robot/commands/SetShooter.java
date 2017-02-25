package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooter extends Command{

	public final static int START = 0, STOP = 1, SPEED_UP = 2, SPEED_DOWN = 3;	
	public int operation;
	public final double MAX_VAL = 1.0, MIN_VAL = -1.0;
	
	public double shooterSpeed;
	public SetShooter (int operation) {
		requires(Robot.shooter);
		this.operation = operation;
		
	}
	
	protected void execute() {
		switch(operation) {
		case START:
			Robot.shooter.setShooterSpeed(Robot.shootSpeedVal);
			break;
		case STOP:
			Robot.shooter.setShooterSpeed(0.0);
			break;
		case SPEED_UP:
			Robot.shootSpeedVal = (Robot.shootSpeedVal<=MAX_VAL)?Robot.shootSpeedVal+0.05:Robot.shootSpeedVal;
			//Robot.shooter.setShooterSpeed(Robot.shootSpeedVal);
			break;
		case SPEED_DOWN:
			Robot.shootSpeedVal = (Robot.shootSpeedVal>=MIN_VAL)?Robot.shootSpeedVal-0.05:Robot.shootSpeedVal;
			//Robot.shooter.setShooterSpeed(Robot.shootSpeedVal);
			break;
		default:
			Robot.shooter.setShooterSpeed(0);
			break;	
		} 
	}
	
	protected void end() {
		Robot.shooter.setShooterSpeed(0);
	}
	
	protected void interrupted() {
		//end(); no
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
}
