package org.usfirst.frc.team1076.robot.statemachine;

public class IntakeAutonomous extends AutoState {
	private long duration, startTime;
	private double intakeSpeed;

	public IntakeAutonomous(int durationMilliseconds, double intakeSpeed) {
		this.duration = durationMilliseconds * 1000 * 1000;
		this.intakeSpeed = intakeSpeed;
	}

	@Override
	public void init() {
		this.startTime = System.nanoTime();
	}

	@Override
	public boolean shouldChange() {
		return (startTime - System.nanoTime()) > duration;
	}

	@Override
	public double intakeSpeed() {
		if (shouldChange()) {
			return 0;
		}
		return intakeSpeed;
	}
}
