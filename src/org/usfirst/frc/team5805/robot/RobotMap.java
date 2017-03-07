package org.usfirst.frc.team5805.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		//Joysticks
		public final static int 
				DRIVER = 0,
				OPERATOR = 1;
		
		//Can ID
		public final static int 
				FRONTRIGHT = 4, 	// Encoder connected to this talon (PBot Encoder)
				FRONTLEFT = 2, 		// PBot Encoder
				REARLEFT = 1, 		// Encoder connected to this talon
				REARRIGHT = 3,
				LIFT_M1 = 6,
				LIFT_M2 = 8,
				SHOOTER_M1 = 5,
				SHOOTER_M2 = 7;
		
		//Joystick Buttons
		public final static int 
				A_BUTTON = 1, 
				B_BUTTON = 2, 
				X_BUTTON = 3, 
				Y_BUTTON = 4, 
				L_BUMPER = 5, 
				R_BUMPER = 6, 
				BACK_BUTTON = 7, 
				START_BUTTON = 8, 
				L_NUB_BUTTON = 9, 
				R_NUB_BUTTON = 10;
		
		
		//Joystick Axis and Triggers
		public final static int 
				LEFT_X_AXIS = 0, 
				LEFT_Y_AXIS = 1, 
				LEFT_TRIGGER = 2, 
				RIGHT_TRIGGER = 3, 
				RIGHT_X_AXIS = 4, 
				RIGHT_Y_AXIS = 5;
}
