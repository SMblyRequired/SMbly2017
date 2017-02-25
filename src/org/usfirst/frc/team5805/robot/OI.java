package org.usfirst.frc.team5805.robot;

import org.usfirst.frc.team5805.robot.commands.SetTransmission;
import org.usfirst.frc.team5805.robot.commands.SetGearManipulator;
import org.usfirst.frc.team5805.robot.commands.SetLift;
import org.usfirst.frc.team5805.robot.commands.SetShooter;
import org.usfirst.frc.team5805.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI { 
	public static Joystick dStick, oStick;
	
	public OI() {
		//Joysticks
		dStick = new Joystick(RobotMap.DRIVER);
		oStick = new Joystick(RobotMap.OPERATOR);
		
		//Gear Manipulator
		//Button extendGearArm = new JoystickButton(dStick, RobotMap.Y_BUTTON);         	//Extend Gear Manipulator
		//extendGearArm.whenPressed(new SetGearManipulator(SetGearManipulator.EXTEND));	
		
		//Button retractGearArm = new JoystickButton(dStick, RobotMap.A_BUTTON);         	//Retract Gear Manipulator
		//retractGearArm.whenPressed(new SetGearManipulator(SetGearManipulator.RETRACT));		
		
		//Transmission 
		//Button highGear = new JoystickButton(dStick, RobotMap.L_BUMPER); 				//Drive With Speed
		//highGear.whenPressed(new SetTransmission(SetTransmission.HIGH));

		//Button lowGear = new JoystickButton(dStick, RobotMap.R_BUMPER);					//Drive With Torque
		//lowGear.whenPressed(new SetTransmission(SetTransmission.LOW));
		
		Button liftDown = new JoystickButton(dStick, RobotMap.BACK_BUTTON);
		liftDown.whileHeld(new SetLift(Lift.Direction.UP));
		
		
		//Shooter, testing using one class instead of 4 different ones
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
