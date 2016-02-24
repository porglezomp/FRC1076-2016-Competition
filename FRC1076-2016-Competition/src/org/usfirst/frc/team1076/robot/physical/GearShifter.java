package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class GearShifter extends GearShiftStateManager {
	public void shiftGear(GearStates newState, IRobot robot) {
		if(newState == GearStates.High) {
			robot.setGear(Value.kForward);
		} else if(newState == GearStates.Low) {
			robot.setGear(Value.kForward);
		} 
	}
}
