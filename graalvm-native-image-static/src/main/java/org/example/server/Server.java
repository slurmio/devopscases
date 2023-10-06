package org.example.server;

import lombok.extern.java.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class Server {
  public void serve(final int port) throws IOException {
    try (
        final DatagramSocket datagramSocket = new DatagramSocket(port);
    ) {
      log.log(Level.INFO, "server created, listen on: {0}", port);

      while (true) {
        final byte[] buffer = new byte[1024];
        final DatagramPacket in = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(in);
        log.log(Level.INFO, "received packet from {0}:{1}", new Object[]{in.getAddress(), in.getPort()});
        final String incomingMessage = new String(in.getData(), in.getOffset(), in.getLength(), StandardCharsets.UTF_8).trim();
        log.log(Level.FINE, "message: {0}", incomingMessage);

        switch (incomingMessage) {
          case "UUID": {
            final byte[] replyMessage = String.format("%s%n", UUID.randomUUID()).getBytes(StandardCharsets.UTF_8);
            final DatagramPacket out = new DatagramPacket(replyMessage, replyMessage.length, in.getAddress(), in.getPort());
            datagramSocket.send(out);
            break;
          }
          default: {
            final byte[] replyMessage = String.format("Invalid Command: %s%n", incomingMessage).getBytes(StandardCharsets.UTF_8);
            final DatagramPacket out = new DatagramPacket(replyMessage, replyMessage.length, in.getAddress(), in.getPort());
            datagramSocket.send(out);
            break;
          }
        }
      }
    }
  }
}
