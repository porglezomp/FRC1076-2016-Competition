package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IEncoder;
import edu.wpi.first.wpilibj.Encoder;

public class RobotEncoder implements IEncoder {
	//TODO: Find a way to rename this class to Encoder without conflicting
	//      with wpilib's Encoder class.
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
