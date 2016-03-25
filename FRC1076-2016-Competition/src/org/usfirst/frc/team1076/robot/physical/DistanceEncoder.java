package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager;
import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager.GearStates;

public class DistanceEncoder implements IEncoder {	
	Encoder encoder;
	GearShiftStateManager gearShifter;
	GearStates lastGear;
	double countAccumulator = 0; 
	
	public static final double MOTOR_PERIOD = 4096; // Number of encoder counts per motor rotation.
	// Number of motor rotations per wheel rotation.
	public static final double HIGH_GEAR_CONSTANT = 34.0/40.0;
	public static final double LOW_GEAR_CONSTANT = 14.0/60.0;
	public static final double FALLBACK_GEAR_CONSTANT = 1;
	public static final double WHEEL_CIRCUMFRENCE = 6 * Math.PI; // Value is in inches
	
	public DistanceEncoder(Encoder encoder, GearShiftStateManager gearShifter) {
		this.encoder = encoder;
		this.gearShifter = gearShifter;
		this.lastGear = gearShifter.getGearState();
	}
	
	@Override
	public double getDistance() {
		double deltaCount = countAccumulator - getRaw();
		double deltaDistance = (deltaCount) * MOTOR_PERIOD * WHEEL_CIRCUMFRENCE;
		countAccumulator = getRaw();
		
		if (gearShifter.getGearState() == GearStates.High) {
			return deltaDistance * HIGH_GEAR_CONSTANT;
		} else if(gearShifter.getGearState() == GearStates.Low) {
			return deltaDistance * LOW_GEAR_CONSTANT;
		} else { // Fallback if the above gears don't apply for some reason.
			return deltaDistance * FALLBACK_GEAR_CONSTANT;
		}
	}

	@Override
	public void reset() {
		encoder.reset();
		countAccumulator = 0;
	}

	@Override
	public double getRate() {
		return encoder.getRate();
	}

	@Override
	public double getRaw() {
		return encoder.getRaw();
	}
}
