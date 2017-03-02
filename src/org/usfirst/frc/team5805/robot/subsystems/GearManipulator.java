package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.triggers.GearTrigger;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearManipulator extends Subsystem {
		public enum State {
			EXTEND,
			RETRACT
		};
	
		private DoubleSolenoid sol;
		
		public GearManipulator() {
			sol = new DoubleSolenoid(2,3); 
		}
		
		public void setState(State newState) {
			sol.set((newState == State.EXTEND ? Value.kForward : Value.kReverse));
		}
		
		public State getState() {
			return (sol.get() == Value.kForward ? State.EXTEND : State.RETRACT);
		}
		
		public boolean getTrigger() {
			return Robot.gearTrigger.get();
		}
		
		@Override
		protected void initDefaultCommand() { 
			sol.set(Value.kReverse);
		}
}
