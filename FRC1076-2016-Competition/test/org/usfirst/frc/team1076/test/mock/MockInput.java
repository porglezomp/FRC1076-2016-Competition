package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.gamepad.IInput;

public class MockInput implements IInput {

	private double left, right, arm, intake;
	
	@Override
	public MotorOutput driveTrainSpeed() {
		return new MotorOutput(getLeft(), right);
	}

	@Override
	public double armSpeed() {
		return arm;
	}

	@Override
	public double intakeSpeed() {
		return intake;
	}

	public double getLeft() {
		return left;
	}
}
