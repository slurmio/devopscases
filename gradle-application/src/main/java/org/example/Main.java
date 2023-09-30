package org.example;

import com.google.gson.Gson;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.example.serlvet.HealthServlet;
import org.example.serlvet.MainServlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
  public static void main(String[] args) throws IOException, LifecycleException {
    final int port = 9999;
    final Tomcat tomcat = new Tomcat();
    tomcat.setPort(port);

    final Connector connector = new Connector("HTTP/1.1");
    connector.setPort(port);
    tomcat.setConnector(connector);


    final Path baseDir = Files.createTempDirectory("tomcat-");
    tomcat.setBaseDir(baseDir.toFile().getAbsolutePath());

    final Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

    final Gson gson = new Gson();
    final MainServlet mainServlet = new MainServlet(gson);

    Tomcat.addServlet(ctx, "main", mainServlet);
    ctx.addServletMappingDecoded("/*", "main");

    final HealthServlet healthServlet = new HealthServlet(gson);
    Tomcat.addServlet(ctx, "health", healthServlet);
    ctx.addServletMappingDecoded("/health", "health");

    tomcat.start();
  }
}
