package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad;

public class MockGamepad implements IGamepad {

	public double lt, lx, ly, rt, rx, ry;
	public boolean a, b, back, lb, lstick, rb, rstick, start, x, y;
	
	public MockGamepad() {
		reset();
	}
	
	public void reset() {
		lt = lx = ly = rt = rx = ry = 0;
		a = b = back = lb = lstick = rb = rstick = start = x = y = false;
	}
	
	@Override
	public double getAxis(GamepadAxis axis) {
		switch (axis) {
		case LeftTrigger: return lt;
		case LeftX: return lx;
		case LeftY: return ly;
		case RightTrigger: return rt;
		case RightX: return rx;
		case RightY: return ry;
		default: return 0;
		}
	}
	
	@Override
	public boolean getButton(GamepadButton button) {
		switch (button) {
		case A: return a;
		case B: return b;
		case Back: return back;
		case LB: return lb;
		case LStick: return lstick;
		case RB: return rb;
		case RStick: return rstick;
		case Start: return start;
		case X: return x;
		case Y: return y;
		default: return false;
		}
	}

}
