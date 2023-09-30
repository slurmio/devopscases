package org.example.serlvet;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.HealthResponseDto;

import java.io.IOException;

public class HealthServlet extends HttpServlet {
  private final transient Gson gson;

  public HealthServlet(final Gson gson) {
    this.gson = gson;
  }

  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
    final HealthResponseDto dto = new HealthResponseDto(HealthResponseDto.Status.UP);
    resp.setHeader("Content-Type", "application/json");
    this.gson.toJson(dto, resp.getWriter());
  }
}
