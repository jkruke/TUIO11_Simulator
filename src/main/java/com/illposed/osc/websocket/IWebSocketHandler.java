package com.illposed.osc.websocket;

public interface IWebSocketHandler {

    void broadcast(byte[] payload);
}
