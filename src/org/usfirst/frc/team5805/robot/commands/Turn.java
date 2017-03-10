package org.usfirst.frc.team5805.robot.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Provides a command for turning a certain direction or number of degrees
 * @author Josh Ferrara
 */
public class Turn extends Command implements PIDOutput {
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
	
	public Turn(double _angle) {
		requires(Robot.driveTrain);
		setInterruptible(false);
		
		angle = _angle;
		
		Robot.ahrs.reset();						// Reset gyro yaw, or we can just add to it which might be a better option if we want to get the gyro value at another point in the code.
		
		kp = 0.093; //0.03 .0925
		ki = 0.00; //0.00 
		kd = 0.11; //0.12
		
		pid = new PIDController(kp, ki, kd, 0.0, Robot.ahrs, this); // 0.03, 0.00, 0.12 || 0.04, 0.00, 0.1
		pid.setInputRange(-180.0f, 180.0f);
		pid.setOutputRange(-1.0, 1.0); // -0.7, 0.7
		pid.setAbsoluteTolerance(toleranceDeg);
		pid.setContinuous(true);
		pid.setSetpoint(angle);
		pid.enable();
		
		LiveWindow.addActuator("DriveSystem", "TurnCommand", pid);
	}
	
	public Turn(Direction dir) {
		this(dirToAng.get(dir));
	}
	
	public Turn() {
		this(dirToAng.get(Direction.FLIP));
	}
	
	protected void execute() {
		SmartDashboard.putNumber("Turn error: ", pid.getError());
		
		Robot.driveTrain.setTurn(curSolution);
	}
	
	protected void end() {
		pid.disable();
		Robot.driveTrain.stop();
	}
	
	protected void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		return false;//pid.onTarget();
	}
	
	@Override
	public void pidWrite(double output) {
		//System.out.println("Current solution: " + output);
		curSolution = output;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
}
