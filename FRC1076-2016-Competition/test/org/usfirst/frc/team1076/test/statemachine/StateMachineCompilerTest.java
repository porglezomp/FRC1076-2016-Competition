package org.usfirst.frc.team1076.test.statemachine;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.ForwardAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.IntakeAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.IntakeElevationAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.RotateAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.StateMachineCompiler;
import org.usfirst.frc.team1076.test.mock.MockSensorData;

public class StateMachineCompilerTest {
	@Test
	public void testCompiling() {
		AutoState state = StateMachineCompiler.compile("elevate up ; forward 4 ; rotate left 1 ;"
				+ "forward 1 ; elevate down ; intake 1 in", new MockSensorData());
		assertEquals(NothingAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(IntakeElevationAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(ForwardAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(RotateAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(ForwardAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(IntakeElevationAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(IntakeAutonomous.class, state.getClass());
	}
}
