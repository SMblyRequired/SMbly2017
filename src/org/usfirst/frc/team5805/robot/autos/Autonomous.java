package org.usfirst.frc.team5805.robot.autos;

import org.usfirst.frc.team5805.robot.commands.CloseGearManipulator;
import org.usfirst.frc.team5805.robot.commands.DriveDistBackward;
import org.usfirst.frc.team5805.robot.commands.DriveDistForward;
import org.usfirst.frc.team5805.robot.commands.OpenGearManipulator;
import org.usfirst.frc.team5805.robot.commands.Turn;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class Autonomous extends CommandGroup {
	public Autonomous() {
		
		//this.addSequential(new GearTrigger());
		/*
		this.addSequential(new OpenGearManipulator(), 1);
		this.addSequential(new TimedCommand(2));
		this.addSequential(new DriveDistBackward(40.0));
		*/
		this.addSequential(new DriveDistForward(79));

		this.addSequential(new Turn(-60), 10);
		this.addSequential(new DriveDistForward(60));
		
		this.addSequential(new OpenGearManipulator(), 1);
		this.addSequential(new TimedCommand(2));
		this.addSequential(new DriveDistBackward(40.0));
		this.addSequential(new CloseGearManipulator(), 1);
		//this.addSequential(new TimedCommand(2));
		//this.addSequential(new DriveDistBackward(40.0));
	}
}
