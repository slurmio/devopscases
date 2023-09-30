package org.example.service;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

class SignServiceTest {
  @SneakyThrows
  @Test
  void shouldSign() {
    final String algo = "HmacSHA256";
    final String key = "073b9ce8277c4d70dba2edf33e80f6a2bde0dbd7e5c9d149ccc20ba8b125129b";
    final byte[] data = "secret".getBytes(StandardCharsets.UTF_8);
    final String expected = "b4a5151c4d6939f8df7febe32ac94c3ce0f61112cbe1d8df0e51d7f2c4d9618b";

    final SignService service = new SignService(algo, Hex.decodeHex(key));

    final String actual = service.sign(data);

    Assertions.assertEquals(expected, actual);
  }
}