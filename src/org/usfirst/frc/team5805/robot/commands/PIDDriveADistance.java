package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class PIDDriveADistance extends PIDCommand {

	private double PIDDriveSolution;
	private final double CIRCUMFERENCE = 10 * Math.PI;
	
	public PIDDriveADistance(double p, double i, double d, double distance) {
		super(p, i, d);
		requires(Robot.driveTrain);
		this.setSetpoint(distance/CIRCUMFERENCE);
		this.getPIDController().setAbsoluteTolerance(0.1);
	}

	protected void execute() {
		Robot.driveTrain.arcadeDrive(PIDDriveSolution, 0);
	}
	
	@Override
	protected double returnPIDInput() {
		return Robot.driveTrain.getRightController().getPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		PIDDriveSolution = output;
	}

	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return this.getPIDController().onTarget();
	}
	
	protected void end() {
		Robot.driveTrain.arcadeDrive(0, 0);
	}
}
