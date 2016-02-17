package org.usfirst.frc.team1076.udp;

public class SensorData {
	enum FieldPosition { Right, Left; }
	private int port;
	private Channel receiver;
	private double heading;
	private double distance;
	private FieldPosition position;
	
	public SensorData(int port) throws Exception {
		this.port = port;
		receiver = new Channel(this.port);
	}
	
	public void interpretData() {
		UDPMessage latest = receiver.popLatestMessage();
		while(latest != null) {
			latest = receiver.popLatestMessage();
			String vstatus = findByVariableName(latest.getMessage(), "VSTATUS");
			double head = Double.parseDouble(findByVariableName(latest.getMessage(), "TH"));
			double dist = Double.parseDouble(findByVariableName(latest.getMessage(), "TD"));
			String lstatus = findByVariableName(latest.getMessage(), "LSTATUS");
			if(vstatus != null) {
				if(vstatus.toLowerCase().contains("right") && position == FieldPosition.Right) {
					this.set(head, dist);
				} else if(vstatus.toLowerCase().contains("left") && 
						position == FieldPosition.Left) {
					this.set(head, dist);
				} else if(vstatus.toLowerCase().equals("ok")) {
					this.set(head, dist);
				}
			} else if(lstatus != null && lstatus.contains("ok")) {
				this.set(head, dist);
			}
		}
	}
	
	public static String findByVariableName(String str, String name) {
		String[] temp = str.replace(" ", "").toLowerCase().split(",");
		for(String i: temp) {
			String[] pair = i.split("\\=");
			if(pair[0].contains(name.toLowerCase())) {
				return pair[1];
			}
		}
		return null;
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
}
