package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;


public class PIDSetShooter extends PIDCommand{

	public double velocity;
	public double PIDVelocitySolution;
	
//	public PIDController pController;
	
	
	public PIDSetShooter(double p, double i, double d, int velocity) {
		super(p, i, d);
		requires(Robot.shooter);
		this.velocity = velocity;
//		this.setInputRange(-80000, 0);
		this.setSetpoint(velocity);
	}
	
	protected void execute() {
		Robot.shooter.setShooterSpeed(PIDVelocitySolution);
	}
	
	@Override
	protected double returnPIDInput() {
		return Robot.shooter.shooterMotor1.getEncVelocity();
	}

	@Override
	protected void usePIDOutput(double output) {
		PIDVelocitySolution = output;
	}
	
	protected void interrupted() {

	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}


}
