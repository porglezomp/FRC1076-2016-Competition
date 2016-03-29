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
	ISensorData sensorData;
	double currentHeading;
	double headingTolerance = 10; // In degrees (?).
	double speed;
	double speedDifference;
	long timeLimit;
	long timeStart;
	boolean started = false;
	
	
	public VisionAutonomous(int millis, double speed, double speedDifference, ISensorData sensorData) {
		this.sensorData = sensorData;
		this.speed = speed;
		this.speed = speedDifference;
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
		if (currentHeading > headingTolerance) {
			// Drive left towards heading.
			return new MotorOutput(speed - speedDifference, speed);
		} else if (currentHeading < -headingTolerance) {
			return new MotorOutput(speed, speed - speedDifference);
		} else {
			return new MotorOutput(speed, speed);
		}
	}

	@Override
	public boolean shouldChange() {
		return started && TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) > timeLimit;
	}

}
