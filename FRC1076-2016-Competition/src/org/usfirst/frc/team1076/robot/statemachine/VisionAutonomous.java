package org.usfirst.frc.team1076.robot.statemachine;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.udp.ISensorData;

/**
 * VisionAutonomous attempts to drive towards the goal.
 * It drives forwards while turning.
 * This is based off of heading data from a SensorData object.
 * It does not use range data. 
 *  */
public class VisionAutonomous extends AutoState {
	private final static double TOLERANCE = 2; // In degrees (?).
	private final static double SPEED_DIFFERENCE = 0.85;
	ISensorData sensorData;
	double currentHeading;
	double speed;
	long timeLimit;
	long timeStart;
	// Protect against shouldChange() returning true before actually driving.
	// Also protect against forgetting to call init().
	boolean started = false;
	
	// TODO: Make LIDAR work so we can drive based off of LIDAR range instead of constant time.
	public VisionAutonomous(int millis, double speed, ISensorData sensorData) {
		this.sensorData = sensorData;
		this.speed = speed;
		this.timeLimit = millis;
	}
	
	@Override
	public void init() {
		started = true;
		currentHeading = sensorData.getVisionHeading();
		timeStart = System.nanoTime();
	}

	@Override
	public MotorOutput driveTrainSpeed() {
		currentHeading = sensorData.getVisionHeading();
		
		if (shouldChange()) {
			return new MotorOutput(0, 0);
		}
		
		if (currentHeading > TOLERANCE) {
			// Turn right
			return new MotorOutput(speed, speed * SPEED_DIFFERENCE);
		} else if (currentHeading < -TOLERANCE) {
			// Turn left
			return new MotorOutput(speed * SPEED_DIFFERENCE, speed);
		} else {
			return new MotorOutput(speed, speed);
		}
	}

	@Override
	public boolean shouldChange() {
		return started && TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) >= timeLimit;
	}
	
	public double getTolerance() {
		return TOLERANCE;
	}
	
	public double getSpeedDifference() {
		return SPEED_DIFFERENCE;
	}

}
