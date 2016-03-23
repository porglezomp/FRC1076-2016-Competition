package org.usfirst.frc.team1076.robot.sensors;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;

public interface IAutoSelector {
	AutoState getState(IRobot robot);
}
