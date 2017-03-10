
package org.usfirst.frc.team5805.robot;

import org.usfirst.frc.team5805.robot.autos.Autonomous;
import org.usfirst.frc.team5805.robot.commands.CloseGearManipulator;
import org.usfirst.frc.team5805.robot.commands.DriveDistBackward;
import org.usfirst.frc.team5805.robot.commands.DriveDistForward;
import org.usfirst.frc.team5805.robot.commands.OpenGearManipulator;
import org.usfirst.frc.team5805.robot.commands.SetShooter;
import org.usfirst.frc.team5805.robot.commands.Turn;
import org.usfirst.frc.team5805.robot.subsystems.Agitator;
import org.usfirst.frc.team5805.robot.subsystems.Camera;
import org.usfirst.frc.team5805.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5805.robot.subsystems.GearManipulator;
import org.usfirst.frc.team5805.robot.subsystems.Transmission;
import org.usfirst.frc.team5805.robot.subsystems.Vision;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5805.robot.subsystems.Lift;
import org.usfirst.frc.team5805.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	

	public static AHRS ahrs;
 
	private static Compressor airCompressor; 
	public static double shootSpeedVal;
	public static int shooterVelocity;
	public static int distance1, distance2;
	
	
	// Subsystems
	public static DriveTrain driveTrain; 
	public static Transmission transmission;
	public static Lift lift;
	public static GearManipulator gearManip;
	public static Shooter shooter;
	//public static Agitator agitator;
	
	//Camera
//	public static CameraServer cam1;

	
//	public static Camera axisCam;
	//public static Camera usbCamera;
	
	public static CameraServer axiCam;
	
	// Gear Manipulator Plate
	public static GearTrigger gearTrigger;

	//Vision 
	public static Vision vision;

	//Auto
	private Command autoCommand;
	private SendableChooser autoChoice; 
	
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
//		cam1 = CameraServer.getInstance();
//		cam1.startAutomaticCapture();
//		axisCam = new Camera();			
		axiCam = CameraServer.getInstance();
		axiCam.addAxisCamera("Axi-Cam", "10.58.5.15");
		//usbCamera = new Camera(2);

		//Vision
		vision = new Vision();

		ahrs = new AHRS(SPI.Port.kMXP);
		
		// Current auto is for blue alliance start on side with single driver station
		
//		autoChoice = new SendableChooser<CommandGroup>();
	
		autoChoice = new SendableChooser();
		autoChoice.addDefault("Default", new Autonomous(7));
		autoChoice.addDefault("Straight line", new Autonomous(0));
		autoChoice.addObject("Straight, turn right", new Autonomous(1));
		autoChoice.addObject("Straight, turn left", new Autonomous(2));
		autoChoice.addObject("Blue, loading", new Autonomous(3));
		autoChoice.addObject("Blue, boiler", new Autonomous(4));
		autoChoice.addObject("Red, loading", new Autonomous(5));
		autoChoice.addObject("Red, boiler", new Autonomous(6));
		SmartDashboard.putData("Auto Choice", autoChoice);
		
//		autoChoice.addDefault("Center Gear", new CenterGearAuto);
//		autoChoice.addObject("Red Alliance, Left Gear", new RLeftAuto);
//		autoChoice.addObject("Red Alliance, Right Gear", new RRightAuto);
		// Insert the rest here...
		
		
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
		Robot.driveTrain.resetEncPos();
		Robot.driveTrain.frontLeft.setVoltageRampRate(24);
		Robot.driveTrain.frontRight.setVoltageRampRate(24);
		Robot.driveTrain.rearLeft.setVoltageRampRate(24);
		Robot.driveTrain.rearRight.setVoltageRampRate(24);
		//autoCommand = (CommandGroup)SmartDashboard.getData("Auto Choice");
		autoCommand = (Command)autoChoice.getSelected();
		autoCommand.start();
		
		//CommandGroup test = new CommandGroup();
		/*
		test.addSequential(new DriveDistForward(70));
		test.addSequential(new OpenGearManipulator(), 1);
		test.addSequential(new TimedCommand(2));
		test.addSequential(new DriveDistBackward(40.0));
		test.start();
		*/

		/*
		this.addSequential(new OpenGearManipulator(), 1);
		this.addSequential(new TimedCommand(2));
		this.addSequential(new DriveDistBackward(40.0));
		*/
		//Command auto1 = new Autonomous();
		//auto1.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
//		SmartDashboard.putNumber("Encoder pidGet()", Robot.driveTrain.getLeftController().pidGet());
	}

	@Override
	public void teleopInit() {

		Robot.driveTrain.frontLeft.setVoltageRampRate(96);
		Robot.driveTrain.frontRight.setVoltageRampRate(96);
		Robot.driveTrain.rearLeft.setVoltageRampRate(96);
		Robot.driveTrain.rearRight.setVoltageRampRate(96);
		
		airCompressor.setClosedLoopControl(true);
		airCompressor.start();
		
		Robot.driveTrain.resetEncPos();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		// System.out.println("Input 1 " + input1.get());
		// System.out.println("Trigger " + gearTrigger.get());
		
		System.out.println("left enc: " + driveTrain.getLeftController().pidGet());
		
		SmartDashboard.putNumber("Shooter Speed", shootSpeedVal);
		SmartDashboard.putNumber("Shooter Velocity", Robot.shooter.shooterMotor2.getEncVelocity());
		SmartDashboard.putNumber("Max Velocity", SetShooter.getMaxEncoderVel(shooterVelocity));
		
		SmartDashboard.putNumber("Left Pos: ", Robot.driveTrain.getLeftPos());
		SmartDashboard.putNumber("Right Pos: ", Robot.driveTrain.getRightPos());		

		SmartDashboard.putNumber("Left Vel: ", Robot.driveTrain.getLeftVel());
		SmartDashboard.putNumber("Right Vel: ", Robot.driveTrain.getRightVel());
		
		//SmartDashboard.putData("USB Camera", usbCamera);
		
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
