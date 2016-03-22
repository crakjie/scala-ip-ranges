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

  def /(subnetMask : Int) : IPRange[IP] = {
    val bitMask : BigInt = BigInt(-1) << (128-subnetMask)
    new IP(this.value & bitMask) to new IP(this.value | ~bitMask)
  }

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


case class PartialIP1(a : Int) {
  def :: (b : Int) : PartialIP2 = PartialIP2(a,b)
  def ::/ (subnetMask : Int) : IPRange[IP] = IP(a,0,0,0,0,0,0,0) / subnetMask
}
case class PartialIP2(a: Int, b : Int) {
  def :: (c : Int) : PartialIP3 =PartialIP3(a,b,c)
  def ::/ (subnetMask : Int) : IPRange[IP] = IP(a,b,0,0,0,0,0,0) / subnetMask
}
case class PartialIP3(a: Int, b : Int, c :Int) {
  def :: (d : Int) : PartialIP4 = PartialIP4(a,b,c,d)
  def ::/ (subnetMask : Int) : IPRange[IP] = IP(a,b,c,0,0,0,0,0) / subnetMask
}
case class PartialIP4(a: Int, b : Int, c :Int, d : Int) {
  def :: (e : Int) : PartialIP5 = PartialIP5(a,b,c,d,e)
  def ::/ (subnetMask : Int) : IPRange[IP] = IP(a,b,c,d,0,0,0,0) / subnetMask
}
case class PartialIP5(a: Int, b : Int, c :Int, d : Int, e :Int) {
  def :: (f : Int) : PartialIP6 = PartialIP6(a,b,c,d,e,f)
  def ::/ (subnetMask : Int) : IPRange[IP] = IP(a,b,c,d,e,0,0,0) / subnetMask
}
case class PartialIP6(a: Int, b : Int, c :Int, d : Int, e :Int, f :Int) {
  def :: (g : Int) : PartialIP7 = PartialIP7(a,b,c,d,e,f,g)
  def ::/ (subnetMask : Int) : IPRange[IP] = IP(a,b,c,d,e,f,0,0) / subnetMask
}
case class PartialIP7(a: Int, b : Int, c :Int, d : Int, e :Int, f :Int, g : Int) {
  def :: (h : Int) : IP = IP(a,b,c,d,e,f,g,h)
  def ::/ (subnetMask : Int) : IPRange[IP] = IP(a,b,c,d,e,f,g,0) / subnetMask
}

