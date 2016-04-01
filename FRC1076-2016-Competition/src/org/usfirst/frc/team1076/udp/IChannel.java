package org.usfirst.frc.team1076.udp;

import java.io.IOException;
import java.net.InetAddress;

public interface IChannel {
	boolean hasMessage();
	UDPMessage popLatestMessage();
	void sendMessage(String message, InetAddress target) throws IOException;
	void sendMessage(String message, InetAddress target, int port) throws IOException;
}
