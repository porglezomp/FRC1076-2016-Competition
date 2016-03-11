package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.sensors.IAutoSelector;
import org.usfirst.frc.team1076.robot.statemachine.AngleAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.ForwardAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector implements IAutoSelector {
	private double speed = 0.8;
	public AutoSelector() {
		SmartDashboard.putNumber("Autonomous", 1);
		SmartDashboard.putBoolean("Autonomous Backwards", false);
	}

	public AutoState getState(IRobot robot) {
		int chooser = (int) SmartDashboard.getNumber("Autonomous", 1);
		boolean backwards = SmartDashboard.getBoolean("Autonomous Backwards", false);
		AutoState chain = new NothingAutonomous();
		switch(chooser) {
		// TODO: Refine and test the state chains.
		case 1:
			if (backwards) {
				chain.addNext(new ForwardAutonomous(1000, -speed))
					.addNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.addNext(new ForwardAutonomous(1000, speed));
			}
			// FieldPosition.Left
			chain.addNext(new AngleAutonomous(90.0, 0.0, robot.getSensorData().getGyro()))
				// TODO: Determine if positive means clockwise.
				.addNext(null); 
				// TODO: Replace with an Autonomous that drives forward until it reaches the goal.
		case 2:
			if (backwards) {
				chain.addNext(new ForwardAutonomous(1000, -speed))
					.addNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.addNext(new ForwardAutonomous(1000, speed));
			}
			// FieldPosition.Left
			chain.addNext(new AngleAutonomous(90.0, 0.0, robot.getSensorData().getGyro()))
				.addNext(null);
		case 3:
			if (backwards) {
				chain.addNext(new ForwardAutonomous(1000, -speed))
					.addNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.addNext(new ForwardAutonomous(1000, speed));
			}
			// FieldPosition.Left
			chain.addNext(new AngleAutonomous(90.0, 0.0, robot.getSensorData().getGyro()))
				.addNext(null);
		case 4:
			// This is right in front of the tower. What is our procedure for this?
			if (backwards) {
				chain.addNext(new ForwardAutonomous(1000, -speed))
					.addNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.addNext(new ForwardAutonomous(1000, speed));
			}
			// FieldPosition.Right
			chain.addNext(new AngleAutonomous(-90.0, 0.0, robot.getSensorData().getGyro()))
				.addNext(null);
		case 5:
			if (backwards) {
				chain.addNext(new ForwardAutonomous(1000, -speed))
					.addNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.addNext(new ForwardAutonomous(1000, speed));
			}
			// FieldPosition.Right
			chain.addNext(new AngleAutonomous(-90.0, 0.0, robot.getSensorData().getGyro()))
				.addNext(null);
		default:
			// Just nothing
		}
		return chain;
	}
}
