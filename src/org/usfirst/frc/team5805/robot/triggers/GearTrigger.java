package org.usfirst.frc.team5805.robot.triggers;
import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class GearTrigger extends Trigger {
	DigitalInput input1, input2, input3, input4;
	
	public GearTrigger() {
		input1 = new DigitalInput(0);
		input2 = new DigitalInput(1);
		input3 = new DigitalInput(2);
		input4 = new DigitalInput(3);
	}
	
	@Override
	public boolean get() {
		return (!input1.get() || !input2.get() || !input3.get() || !input4.get());
	}
}
