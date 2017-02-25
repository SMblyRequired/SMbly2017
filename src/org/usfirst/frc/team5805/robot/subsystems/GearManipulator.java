package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.commands.SetGearManipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator extends Subsystem {
		DoubleSolenoid sol;
		
		public GearManipulator() {
			sol = new DoubleSolenoid(2,3); 
		}
	 
		public void setSolenoid(DoubleSolenoid.Value value) {
			sol.set(value);
		}
		@Override
		protected void initDefaultCommand() {
			setDefaultCommand(new SetGearManipulator());
		}

}
