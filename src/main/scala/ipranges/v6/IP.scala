package ipranges.v6

import java.net.InetAddress

import ipranges.IPRange
/**
  * Created by Crakjie on 15/02/2016.
  *
  * The value is the numeric representation of the 16 octects of the IP.
  */
final case class IP (val value : BigInt) extends AnyVal {

  /**
    *  @return First octet in decimal form
    *         like 192.x.x.x = 192
    */
  def a  = {
    (value >> 112) & 0xffff
  }
  /**
    *  @return First octet in decimal form
    *         like 192.x.x.x = 192
    */
  def b  = {
    (value >> 96) & 0xffff
  }
  /**
    *  @return First octet in decimal form
    *         like 192.x.x.x = 192
    */
  def c  = {
    (value >> 80) & 0xffff
  }
  /**
    *  @return First octet in decimal form
    *         like 192.x.x.x = 192
    */
  def d  = {
    (value >> 64) & 0xffff
  }

  /**
    *  @return First octet in decimal form
    *         like 192.x.x.x = 192
    */
  def e  = {
    (value >> 48) & 0xffff
  }

  /**
    * @return Second octet in decimal form
    *         like x.28.x.x = 28
    */
  def f = {
    (value >> 32) & 0xffff
  }

  /**
    * @return Third octet in decimal form
    *         like x.x.223.x = 223
    */
  def g = {
    (value >> 16) & 0xffff
  }

  /**
    * @return Fourth octet in decimal form
  *           like x.x.x.1 = 1
    */
  def h = {
    value & 0xffff
  }

  /**
    * @return return the String form of the IP.
    */
  override def toString = f"$a%x:$b%x:$c%x:$d%x:$e%x:$f%x:$g%x:$h%x"

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
  def to(end : IP) : IPRange[IP] = new IPRange(this, end)

  def inetAdress: InetAddress = InetAddress.getByName(toString)

}

object IP{
  def apply(
            a : BigInt,
            b : BigInt,
            c : BigInt,
            d : BigInt,
            e : BigInt,
            f : BigInt,
            g : BigInt,
            h : BigInt) :IP = {

    IP( a << 112 | b << 96 | c << 80 | d << 64 |e << 48 | f << 32 | g << 16 | h)
  }

  //val ItegralIp : Integral[IP] =
  implicit val ItegralIp : Integral[IP] = new Integral[IP] {
    @inline def quot(x: IP, y: IP): IP = new IP(x.value / y.value)

    @inline def rem(x: IP, y: IP): IP = new IP(x.value % y.value)

    @inline def toDouble(x: IP): Double = x.value.toDouble

    @inline def plus(x: IP, y: IP): IP = new IP(x.value + y.value)

    @inline def toFloat(x: IP): Float = x.value.toFloat

    @inline def toInt(x: IP): Int = x.value.toInt

    @inline def negate(x: IP): IP = new IP(x.value)

    @inline def fromInt(x: Int): IP = new IP(x)

    @inline def toLong(x: IP): Long = x.value.toLong

    @inline def times(x: IP, y: IP): IP = new IP(x.value * y.value)

    @inline def minus(x: IP, y: IP): IP = new IP(x.value - y.value)

    @inline def compare(x: IP, y: IP): Int = x.value.compare(y.value)
  }


}


