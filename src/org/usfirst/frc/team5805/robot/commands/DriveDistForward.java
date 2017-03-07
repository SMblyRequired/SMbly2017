package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.MathSM;
import org.usfirst.frc.team5805.robot.Robot;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveDistForward extends Command implements PIDOutput {
	private double curPidVal;
	private double distance;
	
	//sudo kill me now
	// 27 inch drive base width
	// 6 inch diameter wheels
	
	// 7301 = 1ft
	
	private static double ROBOT_LENGTH_IN = 12 * 3; // 3 Ft. = 36 inches
	private static double WHEEL_CIRC = 10 * Math.PI;
	private static double DB_CIRC = 27 * Math.PI;
	
	private boolean reverse = true; // If we're driving backwards...

	private PIDController pController;
	
	/**
	 * Drives the robot straight for a given distance
	 * @param distance - distance in inches
	 */
	public DriveDistForward(double _distance) {
		requires(Robot.driveTrain);
		
		distance = _distance;
		
		System.out.println("Driving " + distance + " inches.");
		
		Robot.driveTrain.resetEncPos();
		
		pController = new PIDController(0.7, 0.0, 0.0, Robot.driveTrain.getRightController(), this);
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
		System.out.println("DT Encoder: " + Robot.driveTrain.getRightController().pidGet() + " - Set point: " + (distance / WHEEL_CIRC) + " - Derr " + pController.getError());
		Robot.driveTrain.arcadeDrive(-curPidVal, 0.0);
	}

	@Override
	protected boolean isFinished() {
		return pController.onTarget();
	}

	@Override
	protected void end() {
		Robot.driveTrain.resetEncPos();
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
}