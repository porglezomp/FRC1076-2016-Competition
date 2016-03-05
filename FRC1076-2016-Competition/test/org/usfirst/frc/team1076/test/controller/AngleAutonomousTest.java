package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.test.mock.MockGyro;
import org.usfirst.frc.team1076.robot.statemachine.AngleAutonomous;


public class AngleAutonomousTest {
	// TODO: Write a MockGyro class which outputs fake gyro numbers.
	// Tip: Look at the other Mock___ classes.
	private static final double EPSILON = 1e-12;
	private static final double PI = Math.PI;
	MockGyro gyro = new MockGyro();

	@Test
	public void testNext() {
		gyro.reset();
		AutoState auto = new AngleAutonomous(0, 1, gyro);
		assertSame(null, auto.next());
		auto.setNext(auto);
		assertSame(auto, auto.next());
	}
	
	@Test
	public void testShouldNotChange() {
		gyro.reset();
		AutoState autoClock = new AngleAutonomous(2*PI, 0.1, gyro);
		assertEquals(false, autoClock.shouldChange());
		
		AutoState autoCounterclock = new AngleAutonomous(-2*PI, 0.1, gyro);
		assertEquals(false, autoCounterclock.shouldChange());
	}
	
	@Test
	public void testClockwiseRotation() {
		gyro.reset();
		AngleAutonomous auto = new AngleAutonomous(PI/4, 1, gyro);
		MotorOutput motorOutput = auto.driveTrainSpeed();

		gyro.currAngle = PI/4;
		
		motorOutput = auto.driveTrainSpeed();
		
		assertSame(true, auto.shouldChange());

	}
	
	@Test
	public void testCounterClockwiseRotation() {
		gyro.reset();
		AngleAutonomous auto = new AngleAutonomous(-PI/4, 1, gyro);
		MotorOutput motorOutput = auto.driveTrainSpeed();
		
		gyro.currAngle = -PI/4;
		
		motorOutput = auto.driveTrainSpeed();
		
		assertSame(true, auto.shouldChange());
	}
	
	@Test
	public void testNoArmMotion() {
		gyro.reset();
		AutoState auto = new AngleAutonomous(0, 1, gyro);
		assertEquals(0, auto.armSpeed(), EPSILON);
	}
	
	@Test
	public void testNoIntakeMotion() {
		gyro.reset();
		AutoState auto = new AngleAutonomous(0, 1, gyro);
		assertEquals(0, auto.intakeSpeed(), EPSILON);
	}

}
