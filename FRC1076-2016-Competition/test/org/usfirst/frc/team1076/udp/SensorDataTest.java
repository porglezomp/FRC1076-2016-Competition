package org.usfirst.frc.team1076.udp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1076.test.mock.MockChannel;
import org.usfirst.frc.team1076.test.mock.MockGyro;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public class SensorDataTest {
	private static final double EPSILON = 1e-12;
	MockChannel channel;
	ISensorData data;
	
	@Before
	public void setupChannelAndData() {
		channel = new MockChannel(5880);
		data = new SensorData(channel, FieldPosition.Left, new MockGyro());
	}
	
	@Test
	public void testSonarMessage() {
		String message = "{\"sender\": \"sonar\","
				+ "\"message\": \"ranges\","
				+ "\"left side back\": 1,"
				+ "\"right side back\": 2,"
				+ "\"left side front\": 3,"
				+ "\"right side front\": 4,"
				+ "\"left front\": 5,"
				+ "\"right front\": 6}";
		channel.addMessage(message);
		data.interpretData();
		assertEquals(1, data.getLeftSideBack(), EPSILON);
		assertEquals(2, data.getRightSideBack(), EPSILON);
		assertEquals(3, data.getLeftSideFront(), EPSILON);
		assertEquals(4, data.getRightSideFront(), EPSILON);
		assertEquals(5, data.getLeftFront(), EPSILON);
		assertEquals(6, data.getRightFront(), EPSILON);
	}
	
	@Test
	public void testVisionMessageOk() {
		String message = "{\"sender\": \"vision\","
				+ "\"message\": \"range and heading\","
				+ "\"status\": \"ok\","
				+ "\"heading\": 42,"
				+ "\"range\": 12}";
		channel.addMessage(message);
		data.interpretData();
		
		assertEquals(42, data.getVisionHeading(), EPSILON);
		assertEquals(12, data.getVisionRange(), EPSILON);
	}	
	
	@Test
	public void testVisionMessageSides() {
		data.setFieldPosition(FieldPosition.Left);
		String leftMessage = "{\"sender\": \"vision\","
				+ "\"message\": \"range and heading\","
				+ "\"status\": \"left\","
				+ "\"heading\": 1,"
				+ "\"range\": 2}";
		String rightMessage = "{\"sender\": \"vision\","
				+ "\"message\": \"range and heading\","
				+ "\"status\": \"right\","
				+ "\"heading\": 3,"
				+ "\"range\": 4}";
		channel.addMessage(leftMessage);
		channel.addMessage(rightMessage);
		data.interpretData();
		
		assertEquals(1, data.getVisionHeading(), EPSILON);
		assertEquals(2, data.getVisionRange(), EPSILON);
	
		data.setFieldPosition(FieldPosition.Right);
		channel.addMessage(leftMessage);
		channel.addMessage(rightMessage);
		data.interpretData();
		
		assertEquals(3, data.getVisionHeading(), EPSILON);
		assertEquals(4, data.getVisionRange(), EPSILON);
	}
	
	@Test
	public void testVisionSendColor() throws IOException, ParseException {
		DatagramSocket socket = new DatagramSocket(5888);
		socket.setSoTimeout(1000);
		
		data.sendAttackColor("127.0.0.1", "red");
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024	);
		socket.receive(packet);
		String message = new String(packet.getData());
		message = message.substring(0, packet.getLength());
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(message);
		assertEquals("red", object.get("color"));
		assertEquals("robot", object.get("sender"));
		assertEquals("target", object.get("message"));
		
		data.sendAttackColor("127.0.0.1", "blue");
		packet = new DatagramPacket(new byte[1024], 1024);
		socket.receive(packet);
		message = new String(packet.getData());
		message = message.substring(0, packet.getLength());
		object = (JSONObject) parser.parse(message);
		assertEquals("blue", object.get("color"));
		assertEquals("robot", object.get("sender"));
		assertEquals("target", object.get("message"));
		
		socket.close();
	}
}
