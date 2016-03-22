package ipranges

/**
  * Created by admin on 22/03/2016.
  */
package object v6 {
  //Create PartialIP1 from Int
  implicit def toPartialIP1[T](self :Int) : PartialIP1 = new PartialIP1( self )
}
