package org.usfirst.frc.team1076.robot.statemachine;

import java.util.concurrent.TimeUnit;

public class IntakeAutonomous extends AutoState {
	private long duration, startTime;
	private double intakeSpeed;

	public IntakeAutonomous(int durationMilliseconds, double intakeSpeed) {
		this.duration = TimeUnit.MILLISECONDS.toNanos(durationMilliseconds);
		this.intakeSpeed = intakeSpeed;
	}

	@Override
	public void init() {
		this.startTime = System.nanoTime();
	}

	@Override
	public boolean shouldChange() {
		return (System.nanoTime() - startTime) > duration;
	}

	@Override
	public double intakeSpeed() {
		if (shouldChange()) {
			return 0;
		}
		return intakeSpeed;
	}
}
