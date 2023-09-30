package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.SignService;

import java.io.IOException;

public class SignServlet extends HttpServlet {
  private static final long serialVersionUID = -7589549632947183818L;
  private final transient SignService service = new SignService();

  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    final byte[] bytes = req.getInputStream().readAllBytes();
    final String sign = service.sign(bytes);
    resp.getWriter().write(sign);
  }
}
