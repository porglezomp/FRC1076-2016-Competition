package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.VisionAutonomous;
import org.usfirst.frc.team1076.test.mock.MockSensorData;

public class VisionAutonomousTest {

	MockSensorData sensorData = new MockSensorData();
	double r; // We don't actually care about the sensorData range.
	private static final double EPSILON = 1e-10;
	
	@Test
	public void testNegativeHeading() {
		sensorData.setVision(-100, r);
		double speed = 0.5;
		VisionAutonomous auto = new VisionAutonomous(10, speed, sensorData);
		MotorOutput motor = auto.driveTrainSpeed();
		assertEquals("Right motor is the wrong speed!", 
				speed * auto.getSpeedDifference(), motor.right, EPSILON);
		assertEquals("Left motor is the wrong speed!", 
				speed, motor.left, EPSILON);
	}
	
	@Test
	public void testPositiveHeading() {
		sensorData.setVision(100, r);
		double speed = 0.5;
		VisionAutonomous auto = new VisionAutonomous(10, speed, sensorData);
		MotorOutput motor = auto.driveTrainSpeed();
		assertEquals("Right motor is the wrong speed!", 
				speed, motor.right, EPSILON);
		assertEquals("Left motor is the wrong speed!", 
				speed * auto.getSpeedDifference(), motor.left, EPSILON);
	}
	
	@Test
	public void testHeadingTolerance() {
		sensorData.setVision(5, r);
		double speed = 0.5;
		VisionAutonomous auto = new VisionAutonomous(10, speed, sensorData);
		MotorOutput motor = auto.driveTrainSpeed();
		assertEquals("Right motor is the wrong speed!", 
				speed, motor.right, EPSILON);
		assertEquals("Left motor is the wrong speed!", 
				speed, motor.left, EPSILON);
	}
}
