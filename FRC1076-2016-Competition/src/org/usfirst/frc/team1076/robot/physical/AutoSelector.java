package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.sensors.IAutoSelector;
import org.usfirst.frc.team1076.robot.statemachine.AngleAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.ForwardAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.UDPAutonomous;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector implements IAutoSelector {
	//Button button;
	public AutoSelector() {
		//button = new Button();
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
			if (!backwards) {
				chain.setNext(new ForwardAutonomous(100, 0.2))
					.setNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.setNext(new ForwardAutonomous(100, 0.2));
			}
			chain.setNext(new UDPAutonomous(robot.getSensorData(), FieldPosition.Left))
				.setNext(new AngleAutonomous(90.0, 0.0, robot.getSensorData().getGyro()))
				// TODO: Determine if positive means clockwise.
				.setNext(null); 
				// TODO: Replace with an Autonomous that drives forward until it reaches the goal.
		case 2:
			if (!backwards) {
				chain.setNext(new ForwardAutonomous(100, 0.2))
					.setNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.setNext(new ForwardAutonomous(100, 0.2));
			}
			chain.setNext(new UDPAutonomous(robot.getSensorData(), FieldPosition.Left))
				.setNext(new AngleAutonomous(90.0, 0.0, robot.getSensorData().getGyro()))
				.setNext(null);
		case 3:
			if (!backwards) {
				chain.setNext(new ForwardAutonomous(100, 0.2))
					.setNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.setNext(new ForwardAutonomous(100, 0.2));
			}
			chain.setNext(new UDPAutonomous(robot.getSensorData(), FieldPosition.Left))
				.setNext(new AngleAutonomous(90.0, 0.0, robot.getSensorData().getGyro()))
				.setNext(null);
		case 4:
			// This is right in front of the tower. What is our procedure for this?
			if (!backwards) {
				chain.setNext(new ForwardAutonomous(100, 0.2))
					.setNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.setNext(new ForwardAutonomous(100, 0.2));
			}
			chain.setNext(new UDPAutonomous(robot.getSensorData(), FieldPosition.Right))
				.setNext(new AngleAutonomous(-90.0, 0.0, robot.getSensorData().getGyro()))
				.setNext(null);
		case 5:
			if (!backwards) {
				chain.setNext(new ForwardAutonomous(100, 0.2))
					.setNext(new AngleAutonomous(180.0, 0.0, robot.getSensorData().getGyro()));
			} else {
				chain.setNext(new ForwardAutonomous(100, 0.2));
			}
			chain.setNext(new UDPAutonomous(robot.getSensorData(), FieldPosition.Right))
				.setNext(new AngleAutonomous(-90.0, 0.0, robot.getSensorData().getGyro()))
				.setNext(null);
		default:
			// Just nothing
		}
		return chain;
	}
}
