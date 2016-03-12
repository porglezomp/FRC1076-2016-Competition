package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.sensors.IGyro;

import java.util.concurrent.TimeUnit;

public class ForwardAutonomous extends AutoState {
	private static final double TOLERANCE = 0.05;
	private static final double CORRECTION_FACTOR_CONST = 10.0;
	long timeStart;
	long timeLimit;
	boolean started = false;
	double speed = 1;
	private IGyro gyro;
	
	public ForwardAutonomous(int millis, double speed, IGyro gyro) {
		this.timeLimit = millis;
		this.speed = speed;
		this.gyro = gyro;
	}
	
	public ForwardAutonomous(int millis, double speed) {
		this(millis, speed, null);
	}
	
	public ForwardAutonomous(int millis) {
		this(millis, 1);
	}
	
	@Override
	public void init() {
		started = true;
		timeStart = System.nanoTime();
	}
	
	@Override
	public boolean shouldChange() {
		return started && TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) > timeLimit;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		double rate;
		if (gyro != null) {
			rate = gyro.getRate();
		} else {
			rate = 0;
		}
		if (shouldChange()) {
			return new MotorOutput(0, 0);
		} else if(rate > TOLERANCE) {
			return new MotorOutput(speed * getCorrectionFactor(rate), speed);
		} else if(rate < -TOLERANCE) {
			return new MotorOutput(speed, speed * getCorrectionFactor(rate));
		} else {
			return new MotorOutput(speed, speed);
		}
	}
	
	// TODO: Find a reasonable function for this.
	public double getCorrectionFactor(double rate) {
		return CORRECTION_FACTOR_CONST / (CORRECTION_FACTOR_CONST + Math.abs(rate));
	}
}
