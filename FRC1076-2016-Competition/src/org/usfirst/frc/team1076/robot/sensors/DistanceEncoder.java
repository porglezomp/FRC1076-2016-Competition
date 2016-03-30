package org.usfirst.frc.team1076.robot.sensors;

import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager.GearStates;

public class DistanceEncoder implements IDistanceEncoder {	
	IEncoder encoder;
	GearShiftStateManager gearShifter;
	double countAccumulator = 0; 
	double totalDistance = 0;
	
	public static final double COUNTS_PER_MOTOR_ROTATION = 4096; // Number of encoder counts per motor rotation.
	// Number of motor rotations per wheel rotation.
	public static final double HIGH_GEAR_RATIO = 34.0/40.0;
	public static final double LOW_GEAR_RATIO = 14.0/60.0;
	// This is just the circumference.
	public static final double INCHES_PER_WHEEL_ROTATION = 6 * Math.PI;
	
	public DistanceEncoder(IEncoder encoder, GearShiftStateManager gearShifter) {
		this.encoder = encoder;
		this.gearShifter = gearShifter;
	}

	public void updateDistance() {
		// This function should be called often and whenever the gearShifter changes gears.
		double deltaCount = getRaw() - countAccumulator;
		countAccumulator = getRaw();
		GearStates currentGear = gearShifter.getGearState();
		
		if (currentGear == null) {
			System.err.println("currentGear is null or gearShifter is null in DistanceEncoder");
			return;
		}
		switch (currentGear) {
		case High:
			totalDistance += highGearCountsToInches(deltaCount);
			break;
		case Low:
			totalDistance += lowGearCountsToInches(deltaCount);
			break;
		default:
			break;
		}
	}
	
	@Override
	public double getDistance() {
		updateDistance();
		return totalDistance;
	}
	
	@Override
	public void reset() {
		encoder.reset();
		countAccumulator = 0;
		totalDistance = 0;
	}

	@Override
	public double getRate() {
		return encoder.getRate();
	}

	@Override
	public double getRaw() {
		return encoder.getRaw();
	}
	
	public double highGearCountsToInches(double counts) {
		return counts / COUNTS_PER_MOTOR_ROTATION 
				/ HIGH_GEAR_RATIO * INCHES_PER_WHEEL_ROTATION;
	}
	
	public double lowGearCountsToInches(double counts) {
		// Counts -> Motor Rotations -> Wheel Rotations -> Inches
		return counts / COUNTS_PER_MOTOR_ROTATION 
				/ LOW_GEAR_RATIO * INCHES_PER_WHEEL_ROTATION;
	}
	
	public double highGearInchesToCounts(double inches) {
		// This should convert in the following order:
		// Inches -> Wheel Rotations -> Motor Rotations -> Counts
		return inches / INCHES_PER_WHEEL_ROTATION 
				* HIGH_GEAR_RATIO *  COUNTS_PER_MOTOR_ROTATION;
	}
	
	public double lowGearInchesToCounts(double inches) {
		// This should convert in the following order:
		// Inches -> Wheel Rotations -> Motor Rotations -> Counts
		return inches / INCHES_PER_WHEEL_ROTATION
			* LOW_GEAR_RATIO * COUNTS_PER_MOTOR_ROTATION;
	}
}
