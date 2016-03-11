package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.sensors.IGyro;

/**
 * AngleAutonomous takes an input angle and returns MotorOutputs
 * which rotate the robot on the spot. The angle must be in radians.
 * 
 * In the future, AngleAutonomous will use a PID-based system to turn accurately.
 * @author a2aaron
 *
 */
public class AngleAutonomous extends AutoState {
	IGyro gyro;
	double speed;
	double currAngle;
	double endAngle;
	private double deltaAngle;

	private static final double SLOW_FACTOR = 1.5;	
	
	public AngleAutonomous(double angle, double speed, IGyro gyro) {
		this.gyro = gyro;
		this.speed = speed;
		this.deltaAngle = angle;
	}

	@Override
	public void init() {
		this.currAngle = gyro.getAngle();
		this.endAngle = currAngle + deltaAngle;
	}
	
	@Override
	public boolean shouldChange() {
		if (deltaAngle > 0) { // clockwise
			return currAngle >= endAngle;
		} else { // counter-clockwise
			return currAngle <= endAngle;
		}
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		currAngle = gyro.getAngle();
		
		if (shouldChange()) {
			return new MotorOutput(0, 0);
		}
		
		// Reduce turning speed to avoid overshooting.
		if (Math.abs(currAngle - endAngle) < Math.PI/16) {
			speed /= SLOW_FACTOR;
		}
		
		if (deltaAngle > 0) { // clockwise 					
			return new MotorOutput(speed, -speed);
		} else if (deltaAngle < 0) { // counter-clockwise 					
			return new MotorOutput(-speed, speed);
		} else {
			return new MotorOutput(0, 0);
		}
	}

	public double getAngleTurned() {
		return currAngle - endAngle;
	}
}
