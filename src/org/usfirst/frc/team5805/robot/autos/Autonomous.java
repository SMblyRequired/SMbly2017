package org.usfirst.frc.team5805.robot.autos;

import org.usfirst.frc.team5805.robot.commands.CloseGearManipulator;
import org.usfirst.frc.team5805.robot.commands.DriveADistance;
import org.usfirst.frc.team5805.robot.commands.DriveDistBackward;
import org.usfirst.frc.team5805.robot.commands.DriveDistForward;
import org.usfirst.frc.team5805.robot.commands.HighGear;
import org.usfirst.frc.team5805.robot.commands.OpenGearManipulator;
import org.usfirst.frc.team5805.robot.commands.Turn;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class Autonomous extends CommandGroup {
	public Autonomous(int command) {
		
		// 0 = drive forward
		// 1 = drive forward and put gear on, turn right
		// 2 = drive forward and put gear on, turn left
		// 3 = Blue, loading station side
		// 4 = Blue, boiler side
		// 5 = Red, loading station side
		// 6 = Red, boiler side
//I'd like to make this a lot cleaner before next competition									
		
		switch(command) {
		case 0: //drive forward
			this.addSequential(new DriveDistForward(80));
			break;
		case 1: //drive forward and put gear on, turn right
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(70));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(40.0));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(90), 2);
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(80));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 2);
			this.addSequential(new DriveDistForward(80));
			break;
		case 2: //drive forward and put gear on
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(70));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(40.0));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(-90), 2);
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(80));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 2);
			this.addSequential(new DriveDistForward(80));
			break;
		case 3: //Blue, loading station side
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(79)); //We don't know the distances yet, basic layout
			this.addParallel(new HighGear());
			this.addSequential(new Turn(60), 1.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(60));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);
			this.addSequential(new DriveDistForward(100));
			break;
		case 4: //Blue, boiler side
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(79));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(-60), 1.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(60));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1));
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
			this.addSequential(new Turn(-60), 1.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(60));
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
			this.addSequential(new DriveDistForward(79));
			this.addParallel(new HighGear());
			this.addSequential(new Turn(60), 1.5);
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistForward(60));
			this.addSequential(new OpenGearManipulator(), 1);
			this.addSequential(new TimedCommand(1));
			this.addParallel(new HighGear());
			this.addSequential(new DriveDistBackward(-40.0));
			this.addParallel(new CloseGearManipulator());
			this.addParallel(new HighGear());
			this.addSequential(new Turn(0), 1);
			this.addSequential(new DriveDistForward(100));
			break;
		default:
				//nothing
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
