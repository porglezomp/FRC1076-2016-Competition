package org.usfirst.frc.team1076.test.sensor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1076.robot.sensors.DistanceEncoder;
import org.usfirst.frc.team1076.robot.sensors.GearShiftStateManager.GearStates;
import org.usfirst.frc.team1076.test.mock.MockEncoder;
import org.usfirst.frc.team1076.test.mock.MockGearShifter;
import org.usfirst.frc.team1076.test.mock.MockRobot;

public class DistanceEncoderTest {
	
	private static final double EPSILON = 1e-5;
	// Constant to make the assertions look nicer.
	private static final double SCALING = 4096 * 6 * Math.PI;
	MockGearShifter gear = new MockGearShifter(0, 1);
	MockRobot robot = new MockRobot();
	MockEncoder mockEncoder = new MockEncoder();
	DistanceEncoder encoder = new DistanceEncoder(mockEncoder, gear);
	
	@Before
	public void testSetup() {
		mockEncoder.reset();
		encoder.reset();
	}
	
	@Test
	public void testNoDistance() {
		assertEquals(0, encoder.getDistance(), EPSILON);
	}
	
	@Test
	public void testHighGear() {
		mockEncoder.rawCount += 12;
		gear.shiftGearTo(GearStates.High, robot);
		encoder.updateDistance();
		assertEquals(12 * 34.0/40.0 * SCALING, encoder.getDistance(), EPSILON);
	}
	
	@Test
	public void testLowGear() {
		mockEncoder.rawCount += 12;
		gear.shiftGearTo(GearStates.Low, robot);
		encoder.updateDistance();
		assertEquals(12 * 14.0/60.0 * SCALING, encoder.getDistance(), EPSILON);		
	}

	@Test
	public void testResetDistance() {
		mockEncoder.rawCount += 12;
		gear.shiftGearTo(GearStates.Low, robot);
		encoder.updateDistance();
		assertEquals(12 * 14.0/60.0 * SCALING, encoder.getDistance(), EPSILON);
		
		encoder.reset();
		assertEquals(0, encoder.getDistance(), EPSILON);
		
		mockEncoder.rawCount += 5;
		encoder.updateDistance();
		assertEquals(5 * 14.0/60.0 * SCALING, encoder.getDistance(), EPSILON);
	}

	@Test
	public void testLowToHighGear() {
		gear.shiftGearTo(GearStates.Low, robot);
		mockEncoder.rawCount += 60;
		double correctLow = 60 * 14.0/60.0; 
		encoder.updateDistance();
		
		gear.shiftGearTo(GearStates.High, robot);
		mockEncoder.rawCount += 40;
		double correctHigh = 40 * 34.0/40.0;
		encoder.updateDistance();
		
		assertEquals((correctLow + correctHigh) * SCALING, encoder.getDistance(), EPSILON);		
	}
	
	@Test
	public void testHighToLowGear() {
		mockEncoder.rawCount += 40;
		gear.shiftGearTo(GearStates.High, robot);
		double correctHigh = 40 * 34.0/40.0;
		encoder.updateDistance();
		
		mockEncoder.rawCount += 60;
		gear.shiftGearTo(GearStates.Low, robot);
		double correctLow = 60 * 14.0/60.0;
		encoder.updateDistance();
		assertEquals((correctLow + correctHigh) * SCALING, encoder.getDistance(), EPSILON);		
	}
}
