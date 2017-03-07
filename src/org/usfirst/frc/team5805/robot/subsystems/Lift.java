/**
 * Lift mechanism subsystem
 * @author Joshua Ferrara
 * @author Ashkan Jamshasb
 */

package org.usfirst.frc.team5805.robot.subsystems;
import org.usfirst.frc.team5805.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
	private CANTalon liftM1;
	private CANTalon liftM2;
	
	public enum Direction {
		UP,
		DOWN
	}
	
	public Lift() {
		liftM1 = new CANTalon(RobotMap.LIFT_M1);
		liftM2 = new CANTalon(RobotMap.LIFT_M2);
	}
	
	public void move(Direction dir) {
		double outVal = (dir == Direction.UP ? -1.0 : 1.0);
//		double outVal = (dir == Direction.UP ? -0.55 : 0.55); // TODO: Remove this, and move it to another class for the washing machine agitator
		liftM1.set(outVal);
		liftM2.set(outVal);
	}
	
	public void stop() {
		liftM1.set(0.0);
		liftM2.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() { }
}
