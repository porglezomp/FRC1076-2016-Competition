package org.usfirst.frc.team1076.udp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChannelTest {
	Channel channel;
	
	@Before
	public void setupChannel() {
		channel = new Channel(5885);
	}
	
	@After
	public void cleanupChannel() {
		channel.close();
	}
	
	@Test
	public void testChannel() throws IOException, InterruptedException {
		String data = "hello";
		InetAddress address = InetAddress.getByName("127.0.0.1");
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(), address, 5885);
		DatagramSocket senderSocket = new DatagramSocket();
		senderSocket.send(packet);
		senderSocket.close();
		
		// Wait at most 1 second, 0.1 seconds at a time
		boolean recievedMessage = false;
		for (int i = 0; i < 10; i++) {
			UDPMessage message = channel.popLatestMessage();
			if (message != null) {
				recievedMessage = true;
				assertEquals("Get the message that we sent",
						message.getMessage(), "hello");
				break;
			}
			Thread.sleep(100);
		}
		
		if (!recievedMessage) {
			fail("We didn't recieve the message after 1 seconds");
		}
	}
}
