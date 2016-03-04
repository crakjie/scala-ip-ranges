package ipranges

import ipranges.Incrementable._

/**
  * Created by Crakjie on 15/02/2016.
  */
class IPIterator[T: Incrementable](val start: T, val end : T, step : Int) extends Iterator[T] {


  var last : T = start

  @inline
  def next() : T = {
    last = last.incrementBy(step)
    last
  }

  def hasNext() : Boolean = {
    if (last >= end)
      false
    else
      true
  }
}


class IPRange[T: Incrementable](start: T, end : T, step : Int = 1) extends scala.collection.immutable.Iterable[T] {

  override def iterator: Iterator[T] = new IPIterator(start, end, step)

  /**
    * Generate a new IPRange from the current IPRange but the step is know step with a new value.
    * @param step Increment IPs by step.
    * @return A new IPRange.
    */
  def by(step: Int) = new IPRange(start, end, step)
}









