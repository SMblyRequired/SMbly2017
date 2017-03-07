package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooter extends Command{

	public final static int START = 0, STOP = 1, SPEED_UP = 2, SPEED_DOWN = 3;	
	public int operation;
	public final double MAX_VAL = 1.0, MIN_VAL = -1.0;
	public final int COUNTS_PER_REV = 25000;
	public double shooterSpeed;
	public SetShooter (int operation) {
		requires(Robot.shooter);
		//requires(Robot.agitator);
		this.operation = operation;
		
	}
	
	static int dvel = 0;
	public static int getMaxEncoderVel(int vel) {
		if (vel > dvel) {
			dvel = vel;
		} return dvel;
	}
	
	protected void execute() {
		switch(operation) {
		case START:
			Robot.shooter.setShooterSpeed(Robot.shootSpeedVal);
			//Robot.agitator.agitate();
			break;
		case STOP:
			Robot.shooter.setShooterSpeed(0.0);
			//Robot.agitator.relax(); 
			break;
		case SPEED_UP:
			Robot.shootSpeedVal = (Robot.shootSpeedVal<=MAX_VAL)?Robot.shootSpeedVal+0.05:Robot.shootSpeedVal;
			break;
		case SPEED_DOWN:
			Robot.shootSpeedVal = (Robot.shootSpeedVal>=MIN_VAL)?Robot.shootSpeedVal-0.05:Robot.shootSpeedVal;
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
		//Robot.shooter.shooterMotor2.configEncoderCodesPerRev(10);
	}
}
