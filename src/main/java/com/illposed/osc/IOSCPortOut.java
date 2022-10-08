package com.illposed.osc;

import java.io.IOException;

public interface IOSCPortOut {

	/**
	 * @param aPacket OSCPacket
	 */
	void send(OSCPacket aPacket) throws IOException;
}
