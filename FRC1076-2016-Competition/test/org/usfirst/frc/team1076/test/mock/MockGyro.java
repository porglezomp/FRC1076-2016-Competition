package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.sensors.IGyro;

public class MockGyro implements IGyro {
	public double initAngle;
	public double currAngle;
	public double gyroRate;
	
	public MockGyro() {
		reset();
	}

	@Override
	public double getAngle() {
		return currAngle;
	}

	@Override
	public double getRate() {
		// TODO Auto-generated method stub
		return gyroRate;
	}

	@Override
	public void reset() {
		initAngle = currAngle = 0;
		
	}

}
