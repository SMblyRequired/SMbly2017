package org.usfirst.frc.team5805.robot;

import org.usfirst.frc.team5805.robot.commands.CloseGearManipulator;
import org.usfirst.frc.team5805.robot.commands.CycleShooterLoader;
import org.usfirst.frc.team5805.robot.commands.HighGear;
import org.usfirst.frc.team5805.robot.commands.OpenGearManipulator;
import org.usfirst.frc.team5805.robot.commands.SetLift;
import org.usfirst.frc.team5805.robot.commands.SetShooter;
import org.usfirst.frc.team5805.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI { 
	public static Joystick dStick, oStick;
	
	public OI() {
		// Joysticks
		dStick = new Joystick(RobotMap.DRIVER);
		oStick = new Joystick(RobotMap.OPERATOR);
		 
		// Extend/Retract Gear Manipulator
		
		Button extendGearArmBtn = new JoystickButton(oStick, RobotMap.R_BUMPER);         	//Extend Gear Manipulator
		extendGearArmBtn.whenPressed(new OpenGearManipulator());		
		extendGearArmBtn.whenReleased(new CloseGearManipulator());
		
		//Extend Gear Driver command

		Button extendGearArmBtnDriver = new JoystickButton(dStick, RobotMap.Y_BUTTON); 
	//Extend Gear Manipulator
		extendGearArmBtnDriver.whenPressed(new OpenGearManipulator());		
		extendGearArmBtnDriver.whenReleased(new CloseGearManipulator());
		
		// Toggle Transmission 
		Button highGearBtn = new JoystickButton(dStick, RobotMap.L_BUMPER); 				//Drive With Speed
		highGearBtn.whileHeld(new HighGear());
		
		// Robot lift
		Button liftUp = new JoystickButton(oStick, RobotMap.L_BUMPER);
		liftUp.whileHeld(new SetLift(Lift.Direction.UP));
		
		// Robot lift for driver
		Button liftUpDriver = new JoystickButton(dStick, RobotMap.START_BUTTON);
		liftUpDriver.whileHeld(new SetLift(Lift.Direction.UP));
		
		// Shooter, testing using one class instead of 4 different ones
		Button startShooter = new JoystickButton(oStick, RobotMap.X_BUTTON);
		startShooter.whenPressed(new SetShooter(SetShooter.START));

		Button stopShooter = new JoystickButton(oStick, RobotMap.B_BUTTON);
		stopShooter.whenPressed(new SetShooter(SetShooter.STOP));
		 
		Button speedUpShooter = new JoystickButton(oStick, RobotMap.Y_BUTTON);
		speedUpShooter.whileHeld(new SetShooter(SetShooter.SPEED_UP));
		
		Button speedDownShooter = new JoystickButton(oStick, RobotMap.A_BUTTON);
		speedDownShooter.whileHeld(new SetShooter(SetShooter.SPEED_DOWN));
		
		Button cycleLoader = new JoystickButton(oStick, RobotMap.START_BUTTON);
		cycleLoader.whileHeld(new CycleShooterLoader());
		
		
	}
}
