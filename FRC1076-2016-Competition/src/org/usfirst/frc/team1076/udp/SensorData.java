package org.usfirst.frc.team1076.udp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SensorData {
	enum FieldPosition { Right, Left; }
	private int port;
	private Channel receiver;
	private double heading;
	private double distance;
	private FieldPosition position;
	private JSONParser parser = new JSONParser();
	
	public SensorData(int port) {
		this.port = port;
		receiver = new Channel(this.port);
	}
	
	public void interpretData() {
		UDPMessage latest = receiver.popLatestMessage();
		while(latest != null) {
			latest = receiver.popLatestMessage();
			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(latest.getMessage());
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			}
			
			switch ((String) obj.get("sender")) {
			case "lidar":
				handleLidarMessage(obj);
				break;
			case "vision":
				handleVisionMessage(obj);
				break;
			case "sonar":
				handleSonarMessage(obj);
				break;
			default:
			}
		}
	}
	
	private void handleSonarMessage(JSONObject msg) {

	}
	
	private void handleVisionMessage(JSONObject msg) {
		String status = (String) msg.get("status");
		double heading = ((Number) msg.get("heading")).doubleValue();
		double range = ((Number) msg.get("range")).doubleValue();
		switch (status) {
		case "left":
			if (position == FieldPosition.Right) set(heading, range);
			break;
		case "right":
			if (position == FieldPosition.Left) set(heading, range);
			break;
		case "ok":
			set(heading, range);
			break;
		default:
		}
	}
	
	private void handleLidarMessage(JSONObject msg) {
		String message = (String) msg.get("message");
		switch (message) {
		case "range and heading":
			double heading = ((Number) msg.get("heading")).doubleValue();
			double range = ((Number) msg.get("range")).doubleValue();
			this.heading = heading;
			this.distance = range;
			break;
		default:
		}
	}
	
	public void set(double h, double d) {
		this.heading = h;
		this.distance = d;
	}
	
	public double getHeading() {
		return heading;
	}
	
	public double getDistance() {
		return heading;
	}
	
	public Channel getChannel() {
		return receiver;
	}

	public double currentHeading() {
		return 0;
	}
}
