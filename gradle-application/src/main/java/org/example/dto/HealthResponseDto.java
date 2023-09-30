package org.example.dto;

public record HealthResponseDto(Status status) {
  public enum Status {
    UP, DOWN
  }
}
