package org.usfirst.frc.team1076.test.gamepad;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.OperatorInput;
import org.usfirst.frc.team1076.test.mock.MockGamepad;

public class OperatorInputTest {
	private static final double EPSILON = 1e-10;
	MockGamepad gamepad = new MockGamepad();
	OperatorInput input = new OperatorInput(gamepad);
	
	@Test
	public void testIntakeZero() {
		gamepad.reset();
		assertEquals("No triggers should mean no intake motion",
				0, input.intakeSpeed(), EPSILON);
	}
	
	@Test
	public void testIntakeForward() {
		gamepad.reset();
		gamepad.lt = 1;
		assertEquals("The left trigger should run forwards",
				1, input.intakeSpeed(), EPSILON);
	}
	
	@Test
	public void testIntakeBackward() {
		gamepad.reset();
		gamepad.rt = 1;
		assertEquals("The right trigger should run backwards",
				-1, input.intakeSpeed(), EPSILON);
	}
	
	@Test
	public void testIntakeNeutral() {
		gamepad.reset();
		gamepad.lt = 1;
		gamepad.rt = 1;
		assertEquals("Both triggers pressed should produce no motion",
				0, input.intakeSpeed(), EPSILON);
	}
}
