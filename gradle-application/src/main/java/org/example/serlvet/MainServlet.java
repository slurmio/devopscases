package org.example.serlvet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.MainResponseDto;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

public class MainServlet extends HttpServlet {
  private final transient Gson gson;

  public MainServlet(final Gson gson) {
    this.gson = gson;
  }

  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
    final MainResponseDto dto = new MainResponseDto(UUID.randomUUID());
    resp.setHeader("Content-Type", "application/json");
    this.gson.toJson(dto, resp.getWriter());
  }
}
