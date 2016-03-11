package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.ForwardAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;

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
		assertEquals(false, auto.shouldChange());
	}
	
	@Test
	public void testForwardMotion() {
		AutoState auto = new ForwardAutonomous(1000);
		MotorOutput motorOutput = auto.driveTrainSpeed();
		assertEquals(1, motorOutput.left, EPSILON);
		assertEquals(1, motorOutput.right, EPSILON);
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