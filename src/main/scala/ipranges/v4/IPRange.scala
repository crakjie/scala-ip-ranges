package ipranges.v4

/**
  * Created by Crakjie on 15/02/2016.
  */
class IPIterator(start: IP, end : IP, step : Int) extends Iterator[IP] {

  var last : IP = start

  @inline
  def next() : IP = {
    last = last.incrementBy(step)
    last
  }

  def hasNext() : Boolean = {
    if (last == end)
      false
    else
      true
  }
}



class IPRange(start: IP, end : IP, step : Int = 1) extends scala.collection.immutable.Iterable[IP] {
  if((end.value - start.value) % step != 0)
    throw new IllegalArgumentException("You just created an infinit iterable.")

  override def iterator: Iterator[IP] = new IPIterator(start, end, step)

  /**
    * Generate a new IPRange from the current IPRange but the step is know step with a new value.
    * @param step Increment IPs by step.
    * @return A new IPRange.
    */
  def by(step: Int) = new IPRange(start, end, step)
}