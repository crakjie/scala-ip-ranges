package ipranges.v4

import java.net.InetAddress

import ipranges.IPRange

/**
  * Created by Crakjie on 15/02/2016.
  *
  * The value is the numeric representation of the 4 octects of the IP.
  */
case class IP (value : Int) extends AnyVal {

  /**
    *  @return First octet in decimal form
    *         like 192.x.x.x = 192
    */
  def a : Int = {
    (value >> 24) & 255
  }

  /**
    * @return Second octet in decimal form
    *         like x.28.x.x = 28
    */
  def b : Int = {
    (value >> 16) & 255
  }

  /**
    * @return Third octet in decimal form
    *         like x.x.223.x = 223
    */
  def c : Int = {
    (value >> 8) & 255
  }

  /**
    * @return Fourth octet in decimal form
  *           like x.x.x.1 = 1
    */
  def d : Int = {
    value & 255
  }

  /**
    * @return return the String form of the IP.
    */
  override def toString = s"$a.$b.$c.$d"

  /**
    * Increment by n
    * @return A new IP with the incremented value inside.
    */
  @inline
  def incrementBy(n :Int) = IP(value + n)

  /**
    * Increment the IP by one, alias to incrementBy(1).
    * @return A new IP with the next adress.
    */
  @inline
  def increment = incrementBy(1)

  /**
    * Increment the IP by one, alias to incrementBy(1).
    * @return A new IP with the next adress.
    */
  @inline
  def ++ = incrementBy(1)


  /**
    * Build a IPRange that go from current IP to the given IP.
    * @param end
    * @return
    */
  def to(end : IP) : IPRange[IP] = new IPRange[IP](this, end)

  def inetAdress: InetAddress = InetAddress.getByName(toString)

  def /(subnetMask : Int) : IPRange[IP] = {
    val bitMask = -1 << (32-subnetMask)
    new IP(this.value & bitMask) to new IP(this.value | ~bitMask)
  }

}

object IP {

  val ipRegex = """([0-9]*).([0-9]*).([0-9]*).([0-9]*)""".r

  def apply(ipStr: String): IP = {
    ipStr match {
      case ipRegex(a,b,c,d) => IP(a.toInt, b.toInt, c.toInt, d.toInt)
    }
  }

  def apply(
             a : Int,
             b : Int,
             c : Int,
             d : Int) :IP = {
    IP(a << 24 | b << 16 | c << 8 | d)
  }

  implicit val ItegralIp : Integral[IP] = new Integral[IP] {

    @inline def quot(x: IP, y: IP): IP = new IP(((x.value & 0xffffffffL) / (y.value & 0xffffffffL)).toInt)

    @inline def rem(x: IP, y: IP): IP = new IP(((x.value & 0xffffffffL) % (y.value & 0xffffffffL)).toInt)

    @inline def toDouble(x: IP): Double = x.value.toDouble

    @inline def plus(x: IP, y: IP): IP = new IP(x.value + y.value)

    @inline def toFloat(x: IP): Float = x.value.toFloat

    @inline def toInt(x: IP): Int = x.value

    @inline def negate(x: IP): IP = new IP(x.value)

    @inline def fromInt(x: Int): IP = new IP(x)

    @inline def toLong(x: IP): Long = x.value.toLong

    @inline def times(x: IP, y: IP): IP = new IP(x.value * y.value)

    @inline def minus(x: IP, y: IP): IP = new IP(x.value - y.value)

    @inline def compare(x: IP, y: IP): Int = (x.value & 0xffffffffL).compare(y.value & 0xffffffffL)
  }

}
