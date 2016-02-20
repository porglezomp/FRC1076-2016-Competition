package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.sensors.IAutoSelector;
import org.usfirst.frc.team1076.robot.statemachine.IAutoState;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.UDPAutonomous;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.buttons.Button;
//import edu.wpi.first.wpilibj.command.Command;

public class AutoSelector implements IAutoSelector {
	//Button button;
	public AutoSelector() {
		//button = new Button();
		SmartDashboard.putNumber("Autonomous", 0);
	}
	public IAutoState getState() {
		int chooser = (int) SmartDashboard.getNumber("Autonomous", 0);
		switch(chooser) {
		case 1:
			return new UDPAutonomous(5880, FieldPosition.Left);
		case 2:
			return new UDPAutonomous(5880, FieldPosition.Left);
		case 3:
			return new UDPAutonomous(5880, FieldPosition.Left);
		case 4:
			return new UDPAutonomous(5880, FieldPosition.Right);
		case 5:
			return new UDPAutonomous(5880, FieldPosition.Right);
		default:
			return new NothingAutonomous();
		}
	}
}
