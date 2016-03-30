package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
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
		auto.init();
		MotorOutput motor = auto.driveTrainSpeed();
		assertEquals("Right motor should drive slower to turn left.", 
				speed * auto.getSpeedDifference(), motor.right, EPSILON);
		assertEquals("Left motor should drive at normal speed.", 
				speed, motor.left, EPSILON);
	}
	
	@Test
	public void testPositiveHeading() {
		sensorData.setVision(100, r);
		double speed = 0.5;
		VisionAutonomous auto = new VisionAutonomous(10, speed, sensorData);
		auto.init();
		MotorOutput motor = auto.driveTrainSpeed();
		assertEquals("Right motor should drive at normal speed.", 
				speed, motor.right, EPSILON);
		assertEquals("Left motor should drive slower to turn right.", 
				speed * auto.getSpeedDifference(), motor.left, EPSILON);
	}
	
	@Test
	public void testHeadingTolerance() {
		sensorData.setVision(5, r);
		double speed = 0.5;
		VisionAutonomous auto = new VisionAutonomous(10, speed, sensorData);
		auto.init();
		MotorOutput motor = auto.driveTrainSpeed();
		assertEquals("Right motor should not slow down.", 
				speed, motor.right, EPSILON);
		assertEquals("Left motor should not slow down.", 
				speed, motor.left, EPSILON);
	}
	
	@Test
	public void testShouldChange() {
		sensorData.setVision(100, r);
		double speed = 0.5;
		VisionAutonomous auto = new VisionAutonomous(10, speed, sensorData);
		auto.init();
		assertFalse("Should not change, no motor created.", auto.shouldChange());
		MotorOutput motor = auto.driveTrainSpeed();
		assertFalse("Should not change even after motor was created.", auto.shouldChange());
		
		try {
		    Thread.sleep(11);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		motor = auto.driveTrainSpeed();
		
		assertTrue("Should change (Did you call init?)", auto.shouldChange());
		assertEquals("Left motor should be zero when shouldChange is true.",
				0, motor.left, EPSILON);
		assertEquals("Right motor should be zero when shouldChange is true.",
				0, motor.right, EPSILON);
	}
	
}
