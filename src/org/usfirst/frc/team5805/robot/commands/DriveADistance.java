package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveADistance extends Command implements PIDSource, PIDOutput {
	private double curPidVal;
	private double distance;
	private int direction; //1 is forward
	// 27 inch drive base width
	// 6 inch diameter wheels
	
	// 7301 = 1ft
	
	private static double ROBOT_LENGTH_IN = 12 * 3; // 3 Ft. = 36 inches
	private static double WHEEL_CIRC = 10 * Math.PI;
	private static double DB_CIRC = 27 * Math.PI;
	
	private PIDController pController;

	/**
	 * Drives the robot straight for a given distance
	 * @param distance - distance in inches
	 */
	public DriveADistance(int direction, double distance) {
		requires(Robot.driveTrain);
		
		this.direction = direction;
		this.distance = distance;
		
		Robot.driveTrain.resetEncPos();
		
		pController = new PIDController(0.7, 0.0, 0.0, this, this);
		pController.setSetpoint(distance / WHEEL_CIRC);
		pController.setOutputRange(0.4, 0.8);
		pController.setAbsoluteTolerance(0.1);
		pController.enable();
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.resetEncPos();
	}

	@Override
	protected void execute() {
		Robot.driveTrain.arcadeDrive(((direction == 1)?-curPidVal:curPidVal), 0.0);
	}

	@Override
	protected boolean isFinished() {
		return pController.onTarget();
	}

	@Override
	protected void end() {
		Robot.driveTrain.arcadeDrive(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		curPidVal = output;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return -1 * Robot.driveTrain.getRightController().getPosition();
	}
}
