package ipranges.v4

import java.net.InetAddress

import ipranges.IPRange
import ipranges.Incrementable._

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

  def /(subnestMask : Int) : IPRange[IP] = {
    val bitMask = -1 << (32-subnestMask)
    new IP(this.value & bitMask) to new IP(this.value | ~bitMask)
  }

}

object IP {
  def apply(
             a : Int,
             b : Int,
             c : Int,
             d : Int) :IP = {
    IP(a << 24 | b << 16 | c << 8 | d)
  }

  implicit val IncrementableIP : Incrementable[IP] = new Incrementable[IP] {
    @inline
    final override def >=(self: IP)(other: IP): Boolean = (self.value & 0xffffffffL) >= (other.value & 0xffffffffL)

    @inline
    final override def incrementBy(self: IP)(n: Int): IP = self.incrementBy(n)
  }


}
