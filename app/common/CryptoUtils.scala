package common

/**
  *
  * @author Lawrence
  * @since 2018. 2. 22.
  * @note
  * @version 0.1.1
  */
object CryptoUtils {
  def sha256Hash(text: String): String = {
    String.format("%064x", new java.math.BigInteger(1, java.security.MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8"))))
  }
}
