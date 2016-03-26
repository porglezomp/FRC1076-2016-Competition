package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.IRobot.SolenoidValue;
import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager;
import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager.GearStates;

public class MockGearShifter extends GearShiftStateManager {
	// TODO: This is doesn't actually test anything.
	// (See GearShiftTest)
	public GearStates currentState;

	public MockGearShifter(double upShift, double downShift) {
		super(upShift, downShift);
	}
	
	public void shiftGear(GearStates newState, IRobot robot) {
		currentState = newState;
		if(newState == GearStates.High) {
			robot.setGear(SolenoidValue.Forward);
		} else if(newState == GearStates.Low) {
			robot.setGear(SolenoidValue.Reverse);
		} 
	}
	
	public GearStates getGearState(){
		return currentState;
	}

	public void shiftGearTo(GearStates newState, MockRobot robot) {
		shiftGear(newState, robot);
	}
}
