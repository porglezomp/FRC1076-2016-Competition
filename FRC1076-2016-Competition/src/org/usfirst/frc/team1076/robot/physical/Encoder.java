package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IEncoder;

public class Encoder implements IEncoder {
	edu.wpi.first.wpilibj.Encoder encoder;
	
	public Encoder(edu.wpi.first.wpilibj.Encoder encoder) {
		this.encoder = encoder; 
	}
	
	@Override
	public double getDistance() {
		return encoder.getDistance();
	}

	@Override
	public double getRate() {
		return encoder.getRate();
	}

	@Override
	public void reset() {
		encoder.reset();
	}
}
