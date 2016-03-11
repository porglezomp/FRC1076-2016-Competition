package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IAutoSelector;
import org.usfirst.frc.team1076.robot.statemachine.ForwardAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;
import org.usfirst.frc.team1076.udp.Channel;
import org.usfirst.frc.team1076.udp.IChannel;
import org.usfirst.frc.team1076.udp.SensorData;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector implements IAutoSelector {
	public AutoSelector() {
		SmartDashboard.putNumber("Autonomous", 1);
	}

	public AutoState getState() {
		int chooser = (int) SmartDashboard.getNumber("Autonomous", 1);
		IChannel channel = new Channel(5880);
		SensorData sensorData;
		switch(chooser) {
		// TODO: make the actual state chains
		case 1:
			sensorData = new SensorData(channel, FieldPosition.Left);
			return new ForwardAutonomous(100, 0.2);
		case 2:
			return new NothingAutonomous();
		case 3:
			return new NothingAutonomous();
		case 4:
			return new NothingAutonomous();
		case 5:
			return new NothingAutonomous();
		default:
			return new NothingAutonomous();
		}
	}
}
