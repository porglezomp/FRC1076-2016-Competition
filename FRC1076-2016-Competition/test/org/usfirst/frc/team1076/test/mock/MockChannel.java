package org.usfirst.frc.team1076.test.mock;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;

import org.usfirst.frc.team1076.udp.IChannel;
import org.usfirst.frc.team1076.udp.UDPMessage;

public class MockChannel implements IChannel {
	int port;
	Queue<UDPMessage> messageQueue = new LinkedList<UDPMessage>();
	
	public MockChannel(int port) {
		this.port = port;
	}
	
	public void addMessage(UDPMessage message) {
		messageQueue.add(message);
	}
	
	public void addMessage(String message) {
		addMessage(new UDPMessage(message, null));
	}
	
	@Override
	public boolean hasMessage() {
		return !messageQueue.isEmpty();
	}

	@Override
	public UDPMessage popLatestMessage() {
		return messageQueue.poll();
	}

	@Override
	public void sendMessage(String message, InetAddress target) throws IOException {
		sendMessage(message, target, this.port);
	}

	@Override
	public void sendMessage(String message, InetAddress target, int port) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		byte[] bytes = message.getBytes();
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, target, port);
		socket.send(packet);
		socket.close();
	}

}
