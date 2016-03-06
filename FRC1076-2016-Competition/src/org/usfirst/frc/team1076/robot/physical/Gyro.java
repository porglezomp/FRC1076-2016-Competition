package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IGyro;
import edu.wpi.first.wpilibj.AnalogGyro;

public class Gyro implements IGyro {
	private AnalogGyro gyro;
	
	public Gyro() {
		gyro = new AnalogGyro(0);
	}
	
	public void calibrate() {
		gyro.calibrate();
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	public double getRate() {
		return gyro.getRate();
	}
}
