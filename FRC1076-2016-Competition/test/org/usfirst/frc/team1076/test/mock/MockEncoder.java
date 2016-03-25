package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.sensors.IEncoder;

public class MockEncoder implements IEncoder {
	public double distance;
	public double rawCount;
	public double encoderRate;
	
	public MockEncoder() {
		reset();
	}

	@Override
	public double getDistance(){
		return distance;
	}

	@Override
	public double getRate() {
		return encoderRate;
	}

	@Override
	public void reset() {
		distance = 0;
	}

	@Override
	public double getRaw() {
		return rawCount;
	}

}
