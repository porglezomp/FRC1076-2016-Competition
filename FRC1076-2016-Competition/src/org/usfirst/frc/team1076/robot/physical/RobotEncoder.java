package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IEncoder;
import edu.wpi.first.wpilibj.Encoder;

public class RobotEncoder implements IEncoder {
	Encoder encoder;
	
	public RobotEncoder(Encoder encoder) {
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
