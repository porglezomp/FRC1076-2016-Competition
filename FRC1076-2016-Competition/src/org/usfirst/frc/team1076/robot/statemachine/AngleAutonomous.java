package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

/**
 * AngleAutonomous takes an input angle and returns MotorOutputs
 * which rotate the robot on the spot. The angle must be in radians.
 * 
 * In the future, AngleAutonomous will use a PID-based system to turn accurately.
 * @author a2aaron
 *
 */
public class AngleAutonomous extends AutoState {
	double MOTOR_FACTOR = 1; // TODO: Find a reasonable value for this.

	double angle;
	double angleTurned;
	double speed;
	
	
	public AngleAutonomous(double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
	}


	public void init() {  }

	@Override
	public boolean shouldChange() {
		if (angle > 0) { // clockwise
			return angle > angleTurned;
		} else { // counter-clockwise
			return angle < angleTurned;
		}
	}

	
	private long lastFrameTime = 0;
	
	@Override
	public MotorOutput driveTrainSpeed() { 
		if (lastFrameTime == 0) {
			lastFrameTime = System.nanoTime(); 
		}
		// Time since last time a MotorOutput was given.
		double deltaTime = (System.nanoTime() - lastFrameTime) / 1e9;
		lastFrameTime = System.nanoTime();

		if (shouldChange()) {
			return new MotorOutput(0, 0);
		}
		
		
		if (angle > 0) { // clockwise
			angleTurned += MOTOR_FACTOR * speed * deltaTime; 					
			return new MotorOutput(speed, -speed);
		} else if (angle < 0) { // counter-clockwise
			angleTurned -= MOTOR_FACTOR * speed * deltaTime; 					
			return new MotorOutput(-speed, speed);
		} else {
			return new MotorOutput(0, 0);
		}
	}

	@Override
	public double armSpeed() {
		return 0;
	}

	@Override
	public double intakeSpeed() {
		return 0;
	}

}
