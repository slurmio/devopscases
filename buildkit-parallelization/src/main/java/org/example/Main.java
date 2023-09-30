package org.example;

import lombok.SneakyThrows;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.example.servlet.SignServlet;

public class Main {
  @SneakyThrows
  public static void main(String[] args) {
    final Server server = new Server(8080);

    final ServletHandler handler = new ServletHandler();
    // no initialization for servlet/service
    handler.addServletWithMapping(SignServlet.class, "/");
    server.setHandler(handler);

    server.start();
  }
}
