package ipranges

/**
  * Created by crakjie on 03/03/2016.
  */

object Integral {
  /**
    *Convert Int to Integral
    */
  private[ipranges] implicit def toIntegral[T](self :Int)(implicit  Integral : Integral[T]) : T = Integral.fromInt(self)

  /**
    * Use to transforme Integral[T] functions into "methods"
    */
  final class IntegralOps[T](val self : T) extends AnyVal {
    def >=(other : T)(implicit  Integral : Integral[T]) : Boolean = Integral.compare(self,other) >= 0
    def >(other : T)(implicit  Integral : Integral[T]) : Boolean = Integral.compare(self,other) > 0
    def <(other : T)(implicit  Integral : Integral[T]) : Boolean = Integral.compare(self,other) < 0
    def <=(other : T)(implicit  Integral : Integral[T]) : Boolean = Integral.compare(self,other) <= 0
  }

  /**
    * Use because if IntegralOpt is implicit every things can be a IntegralOps but not every things can call this methods
    * cause of implicit.
    * Here to have IncrementOpt you need to bring your Incrementable[T] that prove you can call methods
    */
  implicit def toIntegralOps[T](self :T)(implicit  Integral : Integral[T]) : IntegralOps[T] = new IntegralOps( self )

}
