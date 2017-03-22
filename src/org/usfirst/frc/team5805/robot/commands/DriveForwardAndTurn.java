package org.usfirst.frc.team5805.robot.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team5805.robot.MathSM;
import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.commands.Turn.Direction;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardAndTurn extends Command implements PIDOutput {
	private double curPidVal;
	private double distance;
	
	//sudo kill me now
	
	//dont use this
	
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
	public DriveForwardAndTurn(double distance, double angle) {
		requires(Robot.driveTrain);
		
		this.distance = distance;
		
		
		Robot.driveTrain.resetEncPos();
		
		pController = new PIDController(0.7, 0.0, 0.0, Robot.driveTrain.getLeftController(), this);
		pController.setSetpoint(distance / WHEEL_CIRC);
		pController.setOutputRange(0.4, 0.8);
		pController.setAbsoluteTolerance(0.1);
		pController.enable();

		requires(Robot.driveTrain);
		setInterruptible(false);
		
		this.angle = angle;
		
		Robot.ahrs.reset();						// Reset gyro yaw, or we can just add to it which might be a better option if we want to get the gyro value at another point in the code.
		
		kp = 0.08; //0.03 .0925
		ki = 0.00; //0.00 
		kd = 0.11; //0.12  .11
		
		pid = new PIDController(kp, ki, kd, 0.0, Robot.ahrs, this); // 0.03, 0.00, 0.12 || 0.04, 0.00, 0.1
		pid.setInputRange(-180.0f, 180.0f);
		pid.setOutputRange(-1.0, 1.0); // -0.7, 0.7
		pid.setAbsoluteTolerance(toleranceDeg);
		pid.setContinuous(true);
		pid.setSetpoint(angle);
		pid.enable();

	}

	@Override
	protected void initialize() {
		Robot.driveTrain.resetEncPos();
		Robot.driveTrain.frontLeft.setVoltageRampRate(48);
		Robot.driveTrain.frontRight.setVoltageRampRate(48);
		Robot.driveTrain.rearLeft.setVoltageRampRate(48);
		Robot.driveTrain.rearRight.setVoltageRampRate(48);
	}

	@Override
	protected void execute() {
		Robot.driveTrain.frontLeft.setVoltageRampRate(48);
		Robot.driveTrain.frontRight.setVoltageRampRate(48);
		Robot.driveTrain.rearLeft.setVoltageRampRate(48);
		Robot.driveTrain.rearRight.setVoltageRampRate(48);
		
		
		if(angle < 0) {
			Robot.driveTrain.setCurve(curPidVal, -curSolution);
		}
		else {
			Robot.driveTrain.setCurve(curPidVal, curSolution);
		}
	}

	@Override
	protected boolean isFinished() {
		return pid.onTarget();
	}

	@Override
	protected void end() {
		Robot.driveTrain.resetEncPos();
		Robot.driveTrain.arcadeDrive(0.0, 0.0);
		pid.disable();
		Robot.driveTrain.stop();
		Robot.driveTrain.frontLeft.setVoltageRampRate(96);
		Robot.driveTrain.frontRight.setVoltageRampRate(96);
		Robot.driveTrain.rearLeft.setVoltageRampRate(96);
		Robot.driveTrain.rearRight.setVoltageRampRate(96);
	}
	

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		curPidVal = output;
		curSolution = output;
	}
	
	public enum Direction {
		LEFT,		// -090 Degrees
		RIGHT,		// +090 Degrees
		FLIP		// +180 Degrees
	} 
	
	private static final Map<Direction, Double> dirToAng;
	static {
		HashMap<Direction, Double> tmpMap = new HashMap<Direction, Double>();
		tmpMap.put(Direction.LEFT, -120.0);
		tmpMap.put(Direction.RIGHT, 90.0);
		tmpMap.put(Direction.FLIP, +180.0);
        dirToAng = Collections.unmodifiableMap(tmpMap);
	}
	
	static final double toleranceDeg = 2.0f; 	// How many degrees we allow the controller to be off
	private double curSolution; 				// Current solution provided by the PID controller
	private double angle;						// Angle we are moving towards
	private PIDController pid;					// PID Controller
	private static double kp, ki, kd;

}
