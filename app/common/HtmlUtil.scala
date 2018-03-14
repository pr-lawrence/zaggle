package common

import java.math.BigInteger
import java.net.URLEncoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64
import org.apache.commons.codec.binary.Hex

/**
  *
  * @author Lawrence
  * @since 2017. 11. 17.
  * @note
  * @version
  */
object HtmlUtil {
  private val DEFAULT_ENCODING = "UTF-8"
  private val HMAC_SHA512 = "HmacSHA512"

  def base64Encode(bytes: Array[Byte]): String = {
    val bytesEncoded: String = Base64.encode(bytes)
    bytesEncoded
  }

  def hashToString(data: String, key: Array[Byte]): String = {
    val sha512_HMAC = Mac.getInstance("HmacSHA512")
    System.out.println("key : " + new String(key))
    val secretkey = new SecretKeySpec(key, "HmacSHA512")
    sha512_HMAC.init(secretkey)
    val mac_data: Array[Byte] = sha512_HMAC.doFinal(data.getBytes)
    Base64.encode(mac_data)
  }

  def hmacSha512(value: String, key: String): Array[Byte] = {
    val keySpec = new SecretKeySpec(key.getBytes(DEFAULT_ENCODING), HMAC_SHA512)
    val mac: Mac = Mac.getInstance(HMAC_SHA512)
    mac.init(keySpec)
    val macData: Array[Byte] = mac.doFinal(value.getBytes(DEFAULT_ENCODING))
    new Hex().encode(macData)
  }

  def asHex(bytes: Array[Byte]) = new String(Base64.encode(bytes))

  def bin2hex(data: Array[Byte]): String = String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data))

  def mapToQueryString(params: Map[String, String]): String = {
    if (params.size > 0) {
      s"${params.map(s => s._1 + "=" + s._2).mkString("&")}"
    }
    else {
      ""
    }
  }

  def encodeURIComponent(s: String): String = {
    URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%26", "&").replaceAll("\\%3D", "=").replaceAll("\\%7E", "~")
  }
}