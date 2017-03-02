package org.usfirst.frc.team5805.robot;

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
		Button extendGearArmBtn = new JoystickButton(dStick, RobotMap.Y_BUTTON);         	//Extend Gear Manipulator
		OpenGearManipulator openManip = new OpenGearManipulator();
		extendGearArmBtn.whenPressed(openManip);		
		
		// Toggle Transmission 
		Button highGearBtn = new JoystickButton(dStick, RobotMap.L_BUMPER); 				//Drive With Speed
		highGearBtn.whenPressed(new HighGear());
		
		// Robot lift
		Button liftUp = new JoystickButton(dStick, RobotMap.BACK_BUTTON);
		liftUp.whileHeld(new SetLift(Lift.Direction.UP));
		
		// Shooter, testing using one class instead of 4 different ones
		Button startShooter = new JoystickButton(dStick, RobotMap.X_BUTTON);
		startShooter.whenPressed(new SetShooter(SetShooter.START));

		Button stopShooter = new JoystickButton(dStick, RobotMap.B_BUTTON);
		stopShooter.whenPressed(new SetShooter(SetShooter.STOP));
		 
		Button speedUpShooter = new JoystickButton(dStick, RobotMap.Y_BUTTON);
		speedUpShooter.whileHeld(new SetShooter(SetShooter.SPEED_UP));
		 
		Button speedDownShooter = new JoystickButton(dStick, RobotMap.A_BUTTON);
		speedDownShooter.whileHeld(new SetShooter(SetShooter.SPEED_DOWN));
	}
}
