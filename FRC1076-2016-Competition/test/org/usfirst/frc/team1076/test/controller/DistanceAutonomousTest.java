package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.DistanceAutonomous;
import org.usfirst.frc.team1076.test.mock.MockEncoder;

public class DistanceAutonomousTest {

	private static final double EPSILON = 1e-12;
	MockEncoder encoder = new MockEncoder();
	
	
	@Test
	public void testNext() {
		encoder.reset();
		AutoState auto = new DistanceAutonomous(1000, 10, encoder);
		assertSame(null, auto.next());
		auto.setNext(auto);
		assertSame(auto, auto.next());
	}
	
	@Test
	public void testShouldNotChange() {
		encoder.reset();
		AutoState auto = new DistanceAutonomous(1000, 10, encoder);
		assertEquals(false, auto.shouldChange());
	}
	
	@Test
	public void testFixedDistanceTraveled() {
		encoder.reset();
		DistanceAutonomous auto = new DistanceAutonomous(1.5, 1, encoder);
		MotorOutput motorOutput = auto.driveTrainSpeed();
		
		// We haven't driven far enough yet, so the robot should still be moving.
		assertEquals(false, auto.shouldChange());
		assertEquals(1, motorOutput.left, EPSILON);
		assertEquals(1, motorOutput.right, EPSILON);
		
		encoder.distance = 1.5;

		motorOutput = auto.driveTrainSpeed();
		
		// The robot should stop by now.
		assertEquals(true, auto.shouldChange());
		assertEquals(0, motorOutput.left, EPSILON);
		assertEquals(0, motorOutput.right, EPSILON);
		assertEquals(1.5, auto.getDistanceTraveled(), 0.1);
	}
	
	@Test
	public void testVaribleSpeed() {
		encoder.reset();
		for (double speed = 0.0; speed < 1.0; speed += 0.3) {
			DistanceAutonomous auto = new DistanceAutonomous(1, speed, encoder);
			auto.driveTrainSpeed();
			
			encoder.distance = speed;

			auto.driveTrainSpeed();

			assertEquals(speed, auto.getDistanceTraveled(), 0.1);
		}
	}
	
	@Test
	public void testNoArmMotion() {
		encoder.reset();
		AutoState auto = new DistanceAutonomous(100, 10, encoder);
		assertEquals(0, auto.armSpeed(), EPSILON);
	}
	
	@Test
	public void testNoIntakeMotion() {
		encoder.reset();
		AutoState auto = new DistanceAutonomous(100, 10, encoder);
		assertEquals(0, auto.intakeSpeed(), EPSILON);
	}

}
