package org.usfirst.frc.team5805.robot.autos;

import java.io.IOException;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.commands.CloseGearManipulator;
import org.usfirst.frc.team5805.robot.commands.CycleShooterLoader;
import org.usfirst.frc.team5805.robot.commands.DriveCurve;
import org.usfirst.frc.team5805.robot.commands.DriveDistBackward;
import org.usfirst.frc.team5805.robot.commands.DriveDistForward;
import org.usfirst.frc.team5805.robot.commands.DriveForwardAndTurn;
import org.usfirst.frc.team5805.robot.commands.HighGear;
import org.usfirst.frc.team5805.robot.commands.OpenGearManipulator;
import org.usfirst.frc.team5805.robot.commands.PIDDriveADistance;
import org.usfirst.frc.team5805.robot.commands.PIDSetShooter;
import org.usfirst.frc.team5805.robot.commands.PIDTurn;
import org.usfirst.frc.team5805.robot.commands.SetShooter;
import org.usfirst.frc.team5805.robot.commands.SetTrans;
import org.usfirst.frc.team5805.robot.commands.Turn;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;



public class Autonomous extends CommandGroup {
	public enum AutoChoices {
		CROSS_BASELINE, 
		CENTER_GEAR_rTURN, 
		CENTER_GEAR_lTURN, 
		BLUE_LOADING,
		BLUE_BOILER,
		RED_LOADING,
		RED_BOILER,
		NO_AUTO
	}
	public Autonomous(AutoChoices choice) {
		 
		// 0 = drive straight, no gear
		// 1 = drive forward and put gear on, turn left
		// 2 = drive forward and put gear on, turn right
		// 3 = Blue, loading station side
		// 4 = Blue, boiler side
		// 5 = Red, loading station side
		// 6 = Red, boiler side
	
		switch(choice) {
			case CROSS_BASELINE: //drive straight, no gear
				this.addSequential(new DriveDistForward(150));
				break;
			case CENTER_GEAR_rTURN: //drive forward and put gear on, turn left
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
				this.addSequential(new DriveCurve(0.8, 0.8), 2);
				this.addParallel(new HighGear());
				this.addSequential(new Turn(0), 2);
				this.addSequential(new DriveCurve(1.0, 1.0), 10);
				break;
			case CENTER_GEAR_lTURN: //drive forward and put gear on, turn right
				this.addSequential(new DriveDistForward(86), 4);
				this.addSequential(new OpenGearManipulator());
				this.addSequential(new TimedCommand(.5));
				this.addParallel(new HighGear());
				this.addSequential(new DriveDistBackward(40.0));
				this.addParallel(new HighGear());
				this.addSequential(new Turn(-90), 2);
				this.addParallel(new CloseGearManipulator());
				this.addParallel(new HighGear());
				this.addSequential(new DriveCurve(0.8, 0.8), 2);
				this.addParallel(new HighGear()); 
				this.addSequential(new Turn(0), 2);
				this.addSequential(new DriveCurve(1.0, 1.0), 10);
				break;
			case BLUE_LOADING:	//Fazio, can you make this mirror the Red auto?
				this.addParallel(new HighGear());
				this.addSequential(new DriveDistForward(77));
				this.addSequential(new TimedCommand(1));
				this.addSequential(new Turn(-60), 3.5);
				this.addParallel(new HighGear());
				this.addSequential(new DriveDistForward(71));
				this.addSequential(new OpenGearManipulator(), 1);
				this.addSequential(new TimedCommand(1));
				this.addParallel(new HighGear());
				this.addSequential(new DriveDistBackward(-40.0));
				this.addParallel(new CloseGearManipulator());
				this.addParallel(new HighGear());
				this.addSequential(new Turn(0), 1);
				this.addSequential(new DriveDistForward(100));
				break;
			case BLUE_BOILER:	//Fazio, can you make this mirror the Red auto?
				this.addSequential(new DriveDistForward(79));
				this.addSequential(new TimedCommand(1));
				this.addSequential(new Turn(60), 3.5);
				this.addParallel(new HighGear());
				this.addSequential(new DriveDistForward(73));
				this.addSequential(new OpenGearManipulator(), 1);
				this.addSequential(new TimedCommand(1));
				this.addParallel(new HighGear());
				this.addSequential(new DriveDistBackward(-40.0));
				this.addParallel(new CloseGearManipulator());
				this.addParallel(new HighGear());
				this.addSequential(new Turn(0), 1);
				this.addSequential(new DriveDistForward(100));
				break;
			case RED_LOADING:	//Drops off gear and gets ready for gear cycle
				this.addSequential(new DriveDistForward(76));
				this.addSequential(new TimedCommand(.2));
				this.addSequential(new Turn(60), 2.5);
				this.addSequential(new DriveDistForward(76), 1.5);
				this.addSequential(new OpenGearManipulator(), 1);
				this.addSequential(new TimedCommand(1));
				this.addParallel(new HighGear());
				this.addSequential(new DriveDistBackward(-50.0));	//maybe drive back a little bit more?
				this.addParallel(new CloseGearManipulator());
				this.addParallel(new HighGear());
				this.addSequential(new Turn(0), 1.5);
				this.addSequential(new DriveDistForward(100));		//change this to make it farther
				break;
			case RED_BOILER:	//Drops off gear and shoots
				this.addSequential(new DriveDistForward(83.5));
				this.addSequential(new TimedCommand(0.2));
				this.addSequential(new Turn(-60), 3.5);
				this.addSequential(new DriveDistForward(65), 1.5);
				this.addSequential(new OpenGearManipulator(), 1);
				this.addSequential(new TimedCommand(1));
				this.addSequential(new DriveDistBackward(-40.0));
				this.addSequential(new CloseGearManipulator());
				this.addSequential(new TimedCommand(1));
				this.addParallel(new SetTrans(1));
				this.addSequential(new TimedCommand(0.5));
				this.addSequential(new Turn(-26), 5); //-29.5
			//	this.addSequential(new PIDSetShooter(0.000065, 0.000001, 0.000005, -6700));
				this.addParallel(new PIDSetShooter(0.00004, 0.0000011, 0.0000073, -66700));
				this.addSequential(new TimedCommand(2));
				this.addSequential(new CycleShooterLoader());
				break;
			default: //nothing
				//TODO Try these, will be easier later down the road if this works
				this.addSequential(new PIDDriveADistance(0.5, 0.0000001, 0.0, 83.5));
				this.addSequential(new TimedCommand(0.2));
				this.addSequential(new PIDTurn(0.091, 0.0124, 0.0, -60), 3.5);
				this.addSequential(new PIDDriveADistance(0.5, 0.0000001, 0.0, 65), 1.5);
				this.addSequential(new OpenGearManipulator(), 1);
				this.addSequential(new TimedCommand(0.3));				
				this.addSequential(new PIDDriveADistance(0.5, 0.0000001, 0.0, -40));
				this.addSequential(new CloseGearManipulator());
				this.addParallel(new SetTrans(1));
				this.addSequential(new TimedCommand(0.5));
				this.addParallel(new PIDTurn(0.091, 0.0124, 0.0, -26), 3.5);
				this.addParallel(new PIDSetShooter(0.00004, 0.0000011, 0.0000073, -66700));
				this.addSequential(new TimedCommand(2));
				this.addSequential(new CycleShooterLoader());
				
	//			this.addSequential(new PIDSetShooter(0.00004, 0.0000011, 0.0000073, -65500));
				break;
		}
	}
}
