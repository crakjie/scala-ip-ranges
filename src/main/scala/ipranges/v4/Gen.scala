package ipranges.v4
import scala.util.Random

/**
  * Created by admin on 21/02/2016.
  */
class Gen {
  def randomIp() : IP = new IP(Random.nextInt)

  def randomIpBetween(start: IP, end : IP) : IP = {
    new IP(Random.nextInt(end.value - start.value) + start.value)
  }
}


