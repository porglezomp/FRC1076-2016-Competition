package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.ForwardAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.test.mock.MockGyro;

public class ForwardAutonomousTest {
	private static final double EPSILON = 1e-12;

	@Test
	public void testNext() {
		AutoState auto = new ForwardAutonomous(1000);
		assertSame(null, auto.next());
		auto.addNext(auto);
		assertSame(auto, auto.next());
	}
	
	@Test
	public void testShouldNotChange() {
		AutoState auto = new ForwardAutonomous(1000);
		auto.init();
		assertEquals(false, auto.shouldChange());
	}
	
	@Test
	public void testNoGyroForwardMotion() {
		AutoState auto = new ForwardAutonomous(1000);
		auto.init();
		MotorOutput motorOutput = auto.driveTrainSpeed();
		assertEquals(1, motorOutput.left, EPSILON);
		assertEquals(1, motorOutput.right, EPSILON);
	}
	
	@Test
	public void testGyroClockwiseForwardMotion() {
		MockGyro gyro = new MockGyro();
		gyro.gyroRate = 2;
		AutoState auto = new ForwardAutonomous(1000, 1, gyro);
		auto.init();
		MotorOutput motorOutput = auto.driveTrainSpeed();
		assertEquals(motorOutput.left, 1 , EPSILON);
		assertEquals(motorOutput.right, 5.0 / 6.0, EPSILON);
	}

	@Test
	public void testGyroCounterclockwiseMotion() {
		MockGyro gyro = new MockGyro();
		gyro.gyroRate = -2;
		AutoState auto = new ForwardAutonomous(1000, 1, gyro);
		auto.init();
		MotorOutput motorOutput = auto.driveTrainSpeed();
		System.out.println(motorOutput.left);
		assertEquals(motorOutput.left, 5.0 / 6.0, EPSILON);
		assertEquals(motorOutput.right, 1, EPSILON);
	}
	
	@Test
	public void testNoArmMotion() {
		AutoState auto = new ForwardAutonomous(1000);
		assertEquals(0, auto.armSpeed(), EPSILON);
	}
	
	@Test
	public void testNoIntakeMotion() {
		AutoState auto = new ForwardAutonomous(1000);
		assertEquals(0, auto.intakeSpeed(), EPSILON);
	}
}