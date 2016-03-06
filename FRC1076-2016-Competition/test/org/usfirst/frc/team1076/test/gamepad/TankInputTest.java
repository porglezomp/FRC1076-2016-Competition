package org.usfirst.frc.team1076.test.gamepad;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.gamepad.TankInput;
import org.usfirst.frc.team1076.test.mock.MockGamepad;

public class TankInputTest {
	private static final double EPSILON = 1e-10;
	MockGamepad gamepad = new MockGamepad();
	TankInput input = new TankInput(gamepad);
	
	@Test
	public void testDriveSticks() {
		gamepad.reset();
		MotorOutput output;		
		for (int i = -100; i <= 100; i++) {
			for (int j = -100; j <= 100; j++) {
				double l = i / 100.0;
				double r = j / 100.0;
				gamepad.ly = l;
				gamepad.ry = r;
				output = input.driveTrainSpeed();
				assertEquals("The left motor should move corresponding to the left stick",
						l, output.left, EPSILON);
				assertEquals("The right motor should move corresponding to the right stick",
						r, output.right, EPSILON);
			}
		}
	}
	
	@Test
	public void testBrakes() {
		gamepad.reset();
		gamepad.lt = 0;
		assertEquals(false, input.brakesApplied());
		gamepad.lt = 0.5;
		assertEquals(true, input.brakesApplied());
		gamepad.lt = 1;
		assertEquals(true, input.brakesApplied());
	}
}
