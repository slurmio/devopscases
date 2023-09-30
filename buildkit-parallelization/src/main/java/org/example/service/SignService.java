package org.example.service;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.example.exception.ConfigAppException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Optional;

public class SignService {
  public static final String APP_SIGN_ALGO = "APP_SIGN_ALGO";
  public static final String APP_SIGN_KEY = "APP_SIGN_KEY";
  private final String algo;
  private final byte[] key;

  public SignService() {
    algo = Optional.ofNullable(System.getenv(APP_SIGN_ALGO))
        .orElseThrow(() -> new ConfigAppException("No algorithm provided"));

    key = Optional.ofNullable(System.getenv(APP_SIGN_KEY))
        .map(this::hexDecode)
        .orElseThrow(() -> new ConfigAppException("No key provided"));
  }

  public SignService(final String algo, final byte[] key) {
    this.algo = algo;
    this.key = Arrays.copyOf(key, key.length);
  }

  @SneakyThrows
  public String sign(final byte[] data) {
    final SecretKeySpec secretKeySpec = new SecretKeySpec(key, algo);
    final Mac mac = Mac.getInstance(algo);
    mac.init(secretKeySpec);
    mac.update(data);
    final byte[] signature = mac.doFinal();
    return Hex.encodeHexString(signature);
  }

  @SneakyThrows
  private byte[] hexDecode(final String string) {
    return Hex.decodeHex(string);
  }
}
