package org.usfirst.frc.team5805.robot.autos;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.commands.CloseGearManipulator;
import org.usfirst.frc.team5805.robot.commands.DriveADistance;
import org.usfirst.frc.team5805.robot.commands.DriveCurve;
import org.usfirst.frc.team5805.robot.commands.DriveDistBackward;
import org.usfirst.frc.team5805.robot.commands.DriveDistForward;
import org.usfirst.frc.team5805.robot.commands.DriveForwardAndTurn;
import org.usfirst.frc.team5805.robot.commands.HighGear;
import org.usfirst.frc.team5805.robot.commands.OpenGearManipulator;
import org.usfirst.frc.team5805.robot.commands.Turn;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class Autonomous extends CommandGroup {
	public Autonomous(int command) {
		 
		// 0 = drive straight, no gear
		// 1 = drive forward and put gear on, turn right
		// 2 = drive forward and put gear on, turn left
		// 3 = Blue, loading station side
		// 4 = Blue, boiler side
		// 5 = Red, loading station side
		// 6 = Red, boiler side

		//we can clean this up a bunch, lets just do this after, we can make command that are for driving forwards and releasing gears
		//also, I didn't want to play with any of the ports so I put highGear in every other line
		switch(command) {
		case 0: //drive straight, no gear
			this.addSequential(new DriveDistForward(150));
			break;
		case 1: //drive forward and put gear on, turn left
			this.addParallel(new HighGear()); //slower speed
			this.addSequential(new DriveDistForward(69), 4);
			this.addSequential(new OpenGearManipulator());
			this.addSequential(new TimedCommand(.5));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(40.0));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(-90), 2);
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			//this.addSequential(new DriveDistForward(80));
			this.addSequential(new DriveCurve(0.8, 0.8), 2);
			this.addParallel(new HighGear()); 
			this.addSequential(new Turn(0), 2);
			this.addSequential(new DriveCurve(1.0, 1.0), 10);
		//	this.addSequential(new DriveDistForward(200)); 
			break;
		case 2: //drive forward and put gear on, turn right 
			this.addParallel(new HighGear()); 
			this.addSequential(new DriveDistForward(69), 4);
			this.addSequential(new OpenGearManipulator());
			this.addSequential(new TimedCommand(.5));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(40.0));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(90), 2);
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			//this.addSequential(new DriveDistForward(80));
			this.addSequential(new DriveCurve(0.8, 0.8), 2);
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 2);
			this.addSequential(new DriveCurve(1.0, 1.0), 10);
			//this.addSequential(new DriveDistForward(200));
			break;
		case 3: //Blue, loading station side
			/*this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(79)); //We don't know the distances yet, basic layout
			this.addParallel(new HighGear());
			this.addSequential(new Turn(-60), 3.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(65));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);
			this.addSequential(new DriveDistForward(100));
			*/
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(75)); //10
			this.addParallel(new HighGear());
			this.addSequential(new Turn(-60), 3.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(67));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1)); // end of revision
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);
			this.addSequential(new DriveDistForward(100));
			break;
		case 4: //Blue, boiler side

			/*this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(83));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(60), 3.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(65));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);
			*/
			//this.addSequential(new DriveCurve(0.8,0.9), 10);
			//this.addSequential(new DriveForwardAndTurn(200, 60), 10);
/*			this.addSequential(new DriveDistForward(80));
			this.addSequential(new Turn(60), 1.5);
			this.addSequential(new DriveDistForward(150));
*/			
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(75)); //10
			this.addParallel(new HighGear());
			this.addSequential(new Turn(60), 3.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(67));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1)); // end of revision
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);
			this.addSequential(new DriveDistForward(100));
			
			break;
		case 5: //Red, loading station side
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(79)); //We don't know the distances yet, basic layout
			this.addParallel(new HighGear());
			this.addSequential(new Turn(60), 3.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(65));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);
			this.addSequential(new DriveDistForward(100));
			break;
		case 6: //Red, boiler side
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(83));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(-60), 3.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(65));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);

			this.addSequential(new DriveCurve(0.9,0.85), 10);
//			this.addSequential(new DriveForwardAndTurn(10, -60), 10);
/*			this.addSequential(new DriveDistForward(80));
			this.addSequential(new Turn(-60), 1.5);
			this.addSequential(new DriveDistForward(150));
*/			break;
		default:
			this.addSequential(new Turn(60), 3.5);
			break;
		}
/*
		this.addSequential(new DriveDistForward(79));

		this.addSequential(new Turn(60), 1.5);
		//this.addSequential(new Turn(90), 10);
		this.addSequential(new DriveDistForward(60));
		
		this.addSequential(new OpenGearManipulator(), 1);
		this.addSequential(new TimedCommand(1));
		this.addSequential(new DriveDistBackward(-40.0));
		this.addParallel(new CloseGearManipulator());
		this.addSequential(new Turn(0), 1);
		this.addSequential(new DriveDistForward(100));
		*/
	}
}
