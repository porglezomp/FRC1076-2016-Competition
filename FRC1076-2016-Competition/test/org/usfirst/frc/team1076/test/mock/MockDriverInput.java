package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput;

public class MockDriverInput implements IDriverInput {

	public double left, right;
	public boolean brakes;
	
	public void reset() {
		left = right = 0;
		brakes = false;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		return new MotorOutput(left, right);
	}

	@Override
	public boolean brakesApplied() {
		return brakes;
	}
}
