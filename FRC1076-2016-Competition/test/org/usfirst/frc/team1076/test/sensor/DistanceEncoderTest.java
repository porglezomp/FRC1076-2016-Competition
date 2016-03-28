package org.usfirst.frc.team1076.test.sensor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1076.robot.sensors.DistanceEncoder;
import org.usfirst.frc.team1076.test.mock.MockEncoder;
import org.usfirst.frc.team1076.test.mock.MockGearShifter;
import org.usfirst.frc.team1076.test.mock.MockRobot;

public class DistanceEncoderTest {
	
	private static final double EPSILON = 1e-5;
	MockGearShifter gear = new MockGearShifter(0, 1);
	MockRobot robot = new MockRobot();
	MockEncoder mockEncoder = new MockEncoder();
	DistanceEncoder encoder = new DistanceEncoder(mockEncoder, gear);
	
	private final double HIGH_GEAR_COUNTS_PER_INCH = encoder.highGearCountsToInches(1);
	private final double LOW_GEAR_COUNTS_PER_INCH = encoder.lowGearCountsToInches(1);
	
	@Before
	public void testSetup() {
		mockEncoder.reset();
		encoder.reset();
	}
	
	@Test
	public void testNoDistance() {
		gear.shiftLow(robot);
		assertEquals(0, encoder.getDistance(), EPSILON);
	}
	
	@Test
	public void testHighGear() {
		mockEncoder.rawCount += 12;
		gear.shiftHigh(robot);
		encoder.updateDistance();
		assertEquals(12 * HIGH_GEAR_COUNTS_PER_INCH, encoder.getDistance(), EPSILON);
	}
	
	@Test
	public void testLowGear() {
		mockEncoder.rawCount += 12;
		gear.shiftLow(robot);
		encoder.updateDistance();
		assertEquals(12 * LOW_GEAR_COUNTS_PER_INCH, encoder.getDistance(), EPSILON);		
	}

	@Test
	public void testResetDistance() {
		mockEncoder.rawCount += 12;
		gear.shiftLow(robot);
		encoder.updateDistance();
		assertEquals(12 * LOW_GEAR_COUNTS_PER_INCH, encoder.getDistance(), EPSILON);
		
		encoder.reset();
		assertEquals(0, encoder.getDistance(), EPSILON);
		
		mockEncoder.rawCount += 5;
		encoder.updateDistance();
		assertEquals(5 * LOW_GEAR_COUNTS_PER_INCH, encoder.getDistance(), EPSILON);
	}

	@Test
	public void testLowToHighGear() {
		gear.shiftLow(robot);
		mockEncoder.rawCount += 60;
		double correctLow = 60 * LOW_GEAR_COUNTS_PER_INCH; 
		encoder.updateDistance();
		
		gear.shiftHigh(robot);
		mockEncoder.rawCount += 40;
		double correctHigh = 40 * HIGH_GEAR_COUNTS_PER_INCH;
		encoder.updateDistance();
		
		assertEquals(correctLow + correctHigh, encoder.getDistance(), EPSILON);		
	}
	
	@Test
	public void testHighToLowGear() {
		mockEncoder.rawCount += 40;
		gear.shiftHigh(robot);
		double correctHigh = 40 * HIGH_GEAR_COUNTS_PER_INCH;
		encoder.updateDistance();
		
		mockEncoder.rawCount += 60;
		gear.shiftLow(robot);
		double correctLow = 60 * LOW_GEAR_COUNTS_PER_INCH;
		encoder.updateDistance();
		assertEquals(correctLow + correctHigh, encoder.getDistance(), EPSILON);		
	}
	
	@Test
	public void testGearRatios() {
		assertEquals(6 * Math.PI / (4096 * 34.0/40.0),
				encoder.highGearCountsToInches(1), EPSILON);
		assertEquals(6 * Math.PI / (4096 * 14.0/60.0),
				encoder.lowGearCountsToInches(1), EPSILON);
	}
}
