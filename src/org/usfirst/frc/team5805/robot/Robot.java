
package org.usfirst.frc.team5805.robot;

import org.usfirst.frc.team5805.robot.autos.Autonomous;
import org.usfirst.frc.team5805.robot.autos.Autonomous.AutoChoices;
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
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
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
	
	//Variables
	public static double shootSpeedVal;
	public static int shooterVelocity;
	public static int distance1, distance2;
	
	// Subsystems
	public static DriveTrain driveTrain; 
	public static Transmission transmission;
	public static Lift lift;
	public static GearManipulator gearManip;
	public static Shooter shooter;
	
	public static boolean practiceRobot = true;
	
	public static CameraServer axiCam;
	
	// Gear Manipulator Plate
	public static GearTrigger gearTrigger;

	//Vision 
	public static Vision vision;

	//Auto
	private Command autoCommand;
	private SendableChooser autoChoice; 
	
	//arduino
	public DigitalOutput arduinoOutput;
	
	
	@Override
	public void robotInit() {
		
		airCompressor = new Compressor();
		
		//Subsystem
		driveTrain = new DriveTrain();
		gearManip = new GearManipulator();
		transmission = new Transmission();
		shooter = new Shooter();
		lift = new Lift();
		
		// Gear Manip Triggers
		//lets put this in a class once competition is over
		CommandGroup gearManipGroup = new CommandGroup();
		gearManipGroup.addSequential(new OpenGearManipulator());
		gearManipGroup.addSequential(new TimedCommand(1));
		gearManipGroup.addSequential(new CloseGearManipulator());	
		
		gearTrigger = new GearTrigger();
		gearTrigger.whenActive(gearManipGroup);
		
		axiCam = CameraServer.getInstance();
		axiCam.addAxisCamera("Axi-Cam", "10.58.5.15");

		//Vision  ...rip
		vision = new Vision();

		ahrs = new AHRS(SPI.Port.kMXP);
		
		//arduino
		arduinoOutput = new DigitalOutput(5);

		autoChoice = new SendableChooser();
		autoChoice.addDefault("No auto", new Autonomous(AutoChoices.NO_AUTO));
		autoChoice.addDefault("Cross Baseline", new Autonomous(AutoChoices.CROSS_BASELINE));
		autoChoice.addObject("Center Gear, turn right", new Autonomous(AutoChoices.CENTER_GEAR_rTURN));
		autoChoice.addObject("Center Gear, turn left", new Autonomous(AutoChoices.CENTER_GEAR_lTURN));
		autoChoice.addObject("Blue, loading", new Autonomous(AutoChoices.BLUE_LOADING));
		autoChoice.addObject("Blue, boiler", new Autonomous(AutoChoices.BLUE_BOILER));
		autoChoice.addObject("Red, loading", new Autonomous(AutoChoices.RED_LOADING));
		autoChoice.addObject("Red, boiler", new Autonomous(AutoChoices.RED_BOILER));
		
		SmartDashboard.putData("Auto Choice", autoChoice);
		
		NetworkTable.getTable("SmartDashboard");

		SmartDashboard.putNumber("Turn Angle: ", ahrs.getAngle());
		
		SmartDashboard.putData("AHRS", ahrs);
		
		//keep this at the bottom, NOTHING GOES BELOW THIS!!!!!
		oi = new OI();
	}

	@Override
	public void disabledInit() {
		airCompressor.setClosedLoopControl(false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void autonomousInit() {
		Robot.driveTrain.resetEncPos();

		airCompressor.setClosedLoopControl(true);
		airCompressor.start();
		ahrs.reset();

		SmartDashboard.putData("AHRS", ahrs);
	
		autoCommand = (Command)autoChoice.getSelected();
		autoCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData("AHRS", ahrs);
		SmartDashboard.putNumber("Shooter Velocity", Robot.shooter.shooterMotor1.getEncVelocity());
	}

	@Override
	public void teleopInit() {
		//We should make the driveTrain motors an ArrayList next time...
		/*Robot.driveTrain.frontLeft.setVoltageRampRate(96);
		Robot.driveTrain.frontRight.setVoltageRampRate(96);
		Robot.driveTrain.rearLeft.setVoltageRampRate(96);
		Robot.driveTrain.rearRight.setVoltageRampRate(96);
		*/
		
		airCompressor.setClosedLoopControl(true);
		airCompressor.start();
		
		Robot.driveTrain.resetEncPos();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Turn Angle: ", ahrs.getAngle());
		
//		SmartDashboard.putNumber("Shooter Speed", shootSpeedVal);
		SmartDashboard.putNumber("Shooter Velocity", Robot.shooter.shooterMotor1.getEncVelocity());
		SmartDashboard.putNumber("ShootToScore", Robot.shooter.shooterMotor1.getEncVelocity());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
