package org.example;

import org.example.server.Server;

import java.io.IOException;
import java.util.Optional;

public class Main {
  public static void main(String[] args) throws IOException {
    final Server server = new Server();
    final String port = Optional.ofNullable(System.getProperty("port"))
      .orElse(System.getenv("PORT"))
    ;
    server.serve(Integer.parseInt(port));
  }
}
