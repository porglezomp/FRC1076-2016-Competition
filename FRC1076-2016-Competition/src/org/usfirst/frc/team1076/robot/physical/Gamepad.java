package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad;

import edu.wpi.first.wpilibj.DriverStation;

public class Gamepad implements IGamepad {
	private int port;
	private DriverStation driverStation;
	
	Gamepad(int port) {
		this.port = port;
		driverStation = DriverStation.getInstance();
	}
	
	@Override
	public double getAxis(GamepadAxis axis) {
		return driverStation.getStickAxis(port, axis.value());
	}

	@Override
	public boolean getButton(GamepadButton button) {
		return driverStation.getStickButton(port,  button.value());
	}
}
