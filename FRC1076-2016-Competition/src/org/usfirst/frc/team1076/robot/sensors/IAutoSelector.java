package org.usfirst.frc.team1076.robot.sensors;
import org.usfirst.frc.team1076.robot.statemachine.IAutoState;

public interface IAutoSelector {
	IAutoState getState();
}
