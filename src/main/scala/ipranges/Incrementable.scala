package ipranges

/**
  * Created by admin on 03/03/2016.
  */



object Incrementable {
  trait Incrementable[T] extends Any {
    @inline def >=(self : T)(other : T) : Boolean

    @inline def incrementBy(self : T)(n :Int) : T
  }

  /**
    * Use to transforme Incrementable[T] functions into "methods"
    */
  final class IncrementableOps[T](val self : T) extends AnyVal {
    def >=(other : T)(implicit  Incrementable : Incrementable[T]) : Boolean = Incrementable.>=(self)(other)
    def incrementBy(n : Int)(implicit  Incrementable : Incrementable[T]) : T = Incrementable.incrementBy(self)(n)
  }
  /**
    * Use because if IncrementOpt is implicit every things can be a IncrementOps but not every things can call this methods
    * cause of implicit.
    * Here to have IncrementOpt you need to bring your Incrementable[T]
    */
  implicit def toIncrementOps[T](self :T)(implicit  Incrementable : Incrementable[T]) : IncrementableOps[T] = new IncrementableOps( self )
}
