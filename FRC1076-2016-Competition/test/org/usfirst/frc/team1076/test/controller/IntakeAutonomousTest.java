package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.IntakeAutonomous;

public class IntakeAutonomousTest {
	private static double EPSILON = 1e-12;

	@Test
	public void testIntakeRuns() {
		AutoState auto = new IntakeAutonomous(1000, 1);
		auto.init();
		assertFalse(auto.shouldChange());
		assertEquals(1, auto.intakeSpeed(), EPSILON);
	}

	@Test
	public void testIntakeRunsDuration() {
		AutoState auto = new IntakeAutonomous(10, 1);
		auto.init();
		assertFalse(auto.shouldChange());
		assertEquals(1, auto.intakeSpeed(), EPSILON);
		assertEquals("Asking twice shouldn't make it stop",
				1, auto.intakeSpeed(), EPSILON);
		try {
			Thread.sleep(11);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(auto.shouldChange());
		assertEquals(0, auto.intakeSpeed(), EPSILON);
	}
}
