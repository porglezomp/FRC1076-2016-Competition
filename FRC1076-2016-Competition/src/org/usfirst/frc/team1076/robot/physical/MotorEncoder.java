package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.CANTalon;

public class MotorEncoder implements IEncoder {
	CANTalon motor;
	
	public MotorEncoder(CANTalon motor) {
		this.motor = motor;
	}

	@Override
	public double getRate() {
		return motor.getEncVelocity();
	}

	@Override
	public double getRaw() {
		return motor.getEncPosition();
	}

	@Override
	public void reset() {
		// TODO: ???
	}

}
