package org.usfirst.frc.team1076.robot.sensors;

import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager.GearStates;

public class DistanceEncoder implements IEncoder {	
	IEncoder encoder;
	GearShiftStateManager gearShifter;
	GearStates currentGear;
	double countAccumulator = 0; 
	double totalDistance = 0;
	
	public static final double MOTOR_PERIOD = 4096; // Number of encoder counts per motor rotation.
	// Number of motor rotations per wheel rotation.
	public static final double HIGH_GEAR_CONSTANT = 34.0/40.0;
	public static final double LOW_GEAR_CONSTANT = 14.0/60.0;
	public static final double FALLBACK_GEAR_CONSTANT = 99999; //TODO: change back to zero
	public static final double WHEEL_CIRCUMFRENCE = 6 * Math.PI; // Value is in inches
	
	public DistanceEncoder(IEncoder encoder, GearShiftStateManager gearShifter) {
		this.encoder = encoder;
		this.gearShifter = gearShifter;
		this.currentGear = gearShifter.getGearState();
	}
	
	public void updateDistance() {
		// This function should be called often.
		double deltaCount = getRaw() - countAccumulator;
		double deltaDistance = deltaCount * MOTOR_PERIOD * WHEEL_CIRCUMFRENCE;
		currentGear = gearShifter.getGearState();
		countAccumulator = getRaw();
		// First time doing updateDistance
		if (currentGear == null) {
			currentGear = gearShifter.getGearState();
		}
		
		// Add the distance traveled since last time we checked.
		switch (currentGear) {
		case High:
			totalDistance += deltaDistance * HIGH_GEAR_CONSTANT;
			break;
		case Low:
			totalDistance += deltaDistance * LOW_GEAR_CONSTANT;
			break;
		default:
			totalDistance += deltaDistance * FALLBACK_GEAR_CONSTANT;
			break;
		}
	}

	@Override
	public double getDistance() {
		return totalDistance;
	}
	
	@Override
	public void reset() {
		encoder.reset();
		countAccumulator = 0;
		totalDistance = 0;
		currentGear = null;
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
