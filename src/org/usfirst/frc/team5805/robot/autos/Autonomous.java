package org.usfirst.frc.team5805.robot.autos;

import org.usfirst.frc.team5805.robot.commands.DriveADistance;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {
	public Autonomous() {
		//this.addSequential(new GearTrigger());
		this.addSequential(new DriveADistance(4), 2);
	}
}
