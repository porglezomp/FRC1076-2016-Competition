package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.BrakeAutonomous;
import org.usfirst.frc.team1076.test.mock.MockRobot;

public class BrakeAutonomousTest {
	MockRobot robot = new MockRobot();
	
	@Test
	public void testSetBrakes() {
		AutoState auto = new BrakeAutonomous(true);
		robot.setBrakes(auto.setBrakes());
		assertTrue(robot.brakes);
	}
	
	@Test
	public void testSetAndUnsetBrakes() {
		AutoState auto = new BrakeAutonomous(true);
		robot.setBrakes(auto.setBrakes());
		assertTrue(robot.brakes);
		
		auto = new BrakeAutonomous(false);
		robot.setBrakes(auto.setBrakes());
		assertFalse(robot.brakes);
	}
	
	@Test
	public void testShouldChange() {
		AutoState auto = new BrakeAutonomous(true);
		assertFalse(auto.shouldChange());
		
		robot.setBrakes(auto.setBrakes());
		assertTrue(auto.shouldChange());
	}
	
	@Test
	public void testSameBrakeSet() {
		AutoState auto = new BrakeAutonomous(true);
		robot.setBrakes(auto.setBrakes());
		auto = new BrakeAutonomous(true);
		robot.setBrakes(auto.setBrakes());
		assertTrue(robot.brakes);
	}
}
