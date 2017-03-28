package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Accurately turn to a given degree.	
 * P - Proportional.	
 * I - Integral.	
 * D - Derivative.	
 * Angle - desired angle.	
 * @author SMBly Required
 * @version 1.0
 * 
 */
public class PIDTurn extends PIDCommand {

	public double PIDTurnSolution;
	
	public PIDTurn(double p, double i, double d, double angle) {
		super(p, i, d);
		requires(Robot.driveTrain);
		Robot.ahrs.reset();
		this.getPIDController().setAbsoluteTolerance(0.5f);
		this.setSetpoint(angle);
		this.getPIDController().setOutputRange(-0.7, 0.7); //TODO ideally we get rid of this, FAZ if you can play with this code and make this 100 and fine tune the pid values I'll love you
	}
	
	protected void execute() {
		Robot.driveTrain.arcadeDrive(0, PIDTurnSolution);
	}
	
	@Override
	protected double returnPIDInput() {
		return Robot.ahrs.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		//TODO I think we can just put our "execute" stuff in here, no need for execute method
		PIDTurnSolution = output;
	}
	
	protected void interrupted() {
		end();
	}
		
	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
}
