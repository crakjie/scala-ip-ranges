package ipranges

//import ipranges.Incrementable._
import ipranges.Integral._
import scala.math.Integral.Implicits._

/**
  * Created by Crakjie on 15/02/2016.
  */
class IPIterator[T: Integral](val start: T, val end : T, step : Int) extends Iterator[T] {

  val last = end - ( end - start ) % step

  var current : T = start

  val nain = implicitly[Integral[T]]

  @inline
  def next() : T = {
    current = current + (step : T)
    current
  }

  @inline
  def hasNext() : Boolean = current < last

}


class IPRange[T: Integral](start: T, end : T, step : Int = 1) extends scala.collection.immutable.Iterable[T] {

  override def iterator: Iterator[T] = new IPIterator(start, end, step)

  /**
    * Generate a new IPRange from the current IPRange but the step is know step with a new value.
    * @param step Increment IPs by step.
    * @return A new IPRange.
    */
  def by(step: Int) = new IPRange(start, end, step)


  override def last = end - ( end - start ) % step

}









