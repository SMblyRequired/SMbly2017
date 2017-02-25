package org.usfirst.frc.team5805.robot;

public class MathSM {
	public static double clamp(double inVal, double min, double max) {
		return Math.max(min, Math.min(max, inVal));
	}

	public static double map(double rawAxis, double in_min, double in_max, double out_min, double out_max) {
		return (rawAxis - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}