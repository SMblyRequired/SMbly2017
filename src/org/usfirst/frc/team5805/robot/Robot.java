
package org.usfirst.frc.team5805.robot;

import org.usfirst.frc.team5805.robot.autos.Autonomous;
import org.usfirst.frc.team5805.robot.commands.CloseGearManipulator;
import org.usfirst.frc.team5805.robot.commands.OpenGearManipulator;
import org.usfirst.frc.team5805.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5805.robot.subsystems.GearManipulator;
import org.usfirst.frc.team5805.robot.subsystems.Transmission;
import org.usfirst.frc.team5805.robot.subsystems.Vision;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;
import org.usfirst.frc.team5805.robot.subsystems.Lift;
import org.usfirst.frc.team5805.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.TimedCommand;
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
	
	//Camera
	public static CameraServer cam1;
	
	// Gear Manipulator Plate
	public static GearTrigger gearTrigger;

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
		
		// Gear Manip Triggers
		CommandGroup gearManipGroup = new CommandGroup();
		gearManipGroup.addSequential(new OpenGearManipulator());
		gearManipGroup.addSequential(new TimedCommand(1));
		gearManipGroup.addSequential(new CloseGearManipulator());	
		
		gearTrigger = new GearTrigger();
		gearTrigger.whenActive(gearManipGroup);
		//gearTrigger.whenInactive(new CloseGearManipulator());
					
		//Camera		
		cam1 = CameraServer.getInstance();
		cam1.startAutomaticCapture();
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
		Autonomous auto1 = new Autonomous();
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
		// System.out.println("Input 1 " + input1.get());
		// System.out.println("Trigger " + gearTrigger.get());
		
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
