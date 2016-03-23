package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IEncoder;
import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager;
import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager.GearStates;

import edu.wpi.first.wpilibj.Encoder;

public class RobotEncoder implements IEncoder {
	Encoder encoder;
	GearShiftStateManager gearShifter;
	double MOTOR_PERIOD = 4096; // Number of encoder counts per motor rotation.
	// Number of motor rotations per wheel rotation.
	public static final double HIGH_GEAR_CONSTANT = 34.0/40.0;
	public static final double LOW_GEAR_CONSTANT = 14.0/60.0;
	public static final double FALLBACK_GEAR_CONSTANT = 1;
	public static final double WHEEL_CIRCUMFRENCE = 6 * Math.PI; // Value is in inches
	
	public RobotEncoder(Encoder encoder, GearShiftStateManager gearShifter) {
		this.encoder = encoder;
		this.gearShifter = gearShifter;
	}
	
	@Override
	public double getDistance() {
		double distance = encoder.getRaw() * MOTOR_PERIOD * WHEEL_CIRCUMFRENCE;
		
		if (gearShifter.getGearState() == GearStates.High) {
			return distance * HIGH_GEAR_CONSTANT;
		} else if(gearShifter.getGearState() == GearStates.Low) {
			return distance * LOW_GEAR_CONSTANT;
		} else { // Fallback if the above gears don't apply for some reason.
			return distance * FALLBACK_GEAR_CONSTANT;
		}
	}

	@Override
	public double getRate() {
		return encoder.getRate();
	}

	@Override
	public void reset() {
		encoder.reset();
	}
}
