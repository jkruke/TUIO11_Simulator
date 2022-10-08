/**
 * @author cramakrishnan
 *
 * Copyright (C) 2004, C. Ramakrishnan / Illposed Software
 * All rights reserved.
 * 
 * See license.txt (or license.rtf) for license information.
 * 
 * 
 * OSCPortOut is the class that sends OSC messages.
 *
 * To send OSC, in your code you should instantiate and hold onto an OSCPort
 * pointing at the address and port number for the receiver.
 *
 * When you want to send an OSC message, call send().
 *
 * An example based on com.illposed.osc.test.OSCPortTest::testMessageWithArgs() :
		OSCPort sender = new OSCPort();
		Object args[] = new Object[2];
		args[0] = new Integer(3);
		args[1] = "hello";
		OSCMessage msg = new OSCMessage("/sayhello", args);
		 try {
			sender.send(msg);
		 } catch (Exception e) {
			 showError("Couldn't send");
		 }
 */

package com.illposed.osc.websocket;

import com.illposed.osc.IOSCPortOut;
import com.illposed.osc.OSCPacket;

public class OSCPortWSOut implements IOSCPortOut {

	private final IWebSocketHandler socketHandler;

	public OSCPortWSOut(IWebSocketHandler socketHandler) {
		this.socketHandler = socketHandler;
	}

	@Override
	public void send(OSCPacket aPacket) {
		byte[] byteArray = aPacket.getByteArray();
		socketHandler.broadcast(byteArray);
	}
}
