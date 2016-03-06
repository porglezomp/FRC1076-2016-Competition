package org.usfirst.frc.team1076.robot.gamepad;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad.GamepadAxis;

public class TankInput implements IDriverInput {
	IGamepad gamepad;

	public TankInput(IGamepad gamepad) {
		this.gamepad = gamepad;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		double left = gamepad.getAxis(GamepadAxis.LeftY);
		double right = gamepad.getAxis(GamepadAxis.RightY);
		return new MotorOutput(left, right);
	}

	@Override
	public boolean brakesApplied() {
		return gamepad.getAxis(GamepadAxis.LeftTrigger) > 0.25;
	}
}
