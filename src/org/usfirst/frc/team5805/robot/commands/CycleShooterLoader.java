package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;


public class CycleShooterLoader extends Command {

	int position;
	
	public CycleShooterLoader() {
		requires(Robot.shooter);
	}

	public void initialize() {
		Robot.shooter.loader.set(Value.kForward);
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		Robot.shooter.loader.set(Value.kReverse);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
/*	
	class Unload extends Command {
		

		public Unload() {
			requires(Robot.shooter);
		}
		
		public void initialize() {
			Robot.shooter.loader.set(Value.kReverse);
		}
		
		@Override
		protected boolean isFinished() {
			return true;
		}
	}
	
	class Load extends Command {
		public Load() {
			requires(Robot.shooter);
		}
		
		
		@Override
		protected boolean isFinished() {
			return false;
		}
		
		protected void interrupted() {
			end();
		}
		
		protected void end() {
			Robot.shooter.loader.set(Value.kForward);
		}
	}
	
	public CycleShooterLoader() {
		addSequential(new Load(), 0.5);
		//addSequential(new TimedCommand(0.75));
		//addSequential(new Unload());
	}
	*/
}
