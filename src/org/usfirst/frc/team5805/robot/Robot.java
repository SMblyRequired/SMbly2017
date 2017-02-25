
package org.usfirst.frc.team5805.robot;

import org.usfirst.frc.team5805.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5805.robot.subsystems.GearManipulator;
import org.usfirst.frc.team5805.robot.subsystems.Transmission;
import org.usfirst.frc.team5805.robot.subsystems.Vision;
import org.usfirst.frc.team5805.robot.subsystems.Lift;
import org.usfirst.frc.team5805.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. 
 */
public class Robot extends IterativeRobot {
	public static OI oi;  
 
	private static Compressor airCompressor; 
	public static double shootSpeedVal;
	
	// Subsystems
	public static DriveTrain driveTrain; 
	public static Transmission transmission;
	public static Lift lift;
	public static GearManipulator gearManip;
	public static Shooter shooter;
	

	//Vision 
	public static Vision vision;
	
	@Override
	public void robotInit() {
		lift = new Lift();
		
		airCompressor = new Compressor();
		
		//Subsystem
		driveTrain = new DriveTrain();
		gearManip = new GearManipulator();
		transmission = new Transmission();
		shooter = new Shooter();
		
		//Vision
		vision = new Vision();
		
		oi = new OI();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		airCompressor.setClosedLoopControl(true);
		airCompressor.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

		SmartDashboard.putNumber("Shooter Speed", shootSpeedVal);
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
