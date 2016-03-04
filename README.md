#Scala IP Range is a light lib that allows to iterate over ips.
- Scala IP Range doesn't use any dependency and doesn't pollute your dependencies.

#Example
Instanciate a new ip.
```scala
  import ipranges._
  val myIp = v4.IP(192,0,0,2)
```

Build a new range.
```scala
  val myRange = v4.IP(192,0,0,1) to v4.IP(192,0,0,15)
  myRange: v4.IPRange = IPRange(192.0.0.2, 192.0.0.3, 192.0.0.4, 192.0.0.5, 192.0.0.6, 192.0.0.7, 192.0.0.8, 192.0.0.9, 192.0.0.10, 192.0.0.11, 192.0.0.12, 192.0.0.13, 192.0.0.14)
```

Iterate over this range is easy as one two tree.

```scala
  for(ip <- v4.IP(192,0,0,1) to v4.IP(192,0,0,255)) {
    println(ip)
  }
```

You can also change the steps
```scala
  val myRange = IP(192,0,0,0) to IP(192,0,15,0) by 256
  myRange: v4.IPRange = IPRange(192.0.1.0, 192.0.2.0, 192.0.3.0, 192.0.4.0, 192.0.5.0, 192.0.6.0, 192.0.7.0, 192.0.8.0, 192.0.9.0, 192.0.10.0, 192.0.11.0, 192.0.12.0, 192.0.13.0, 192.0.14.0, 192.0.15.0)
```

Generate a range with the subnetMask

```scala
val range : IPRange = IP(10,9,8,7) / 16
range.head  //print IP(10,9,0,0)
range.last  //print IP(10,9,255,255)
```

## IPv6

```scala
  import ipranges._
  val myIpv6 = v6.IP(192,0,0,2)
```



#Generator
The Gen object allows you to easily get random generated IP.

```scala
  Gen.randomIp()
  res1: ipranges.v4.IP = 76.78.201.206
```

Or generate an IP between to IP.

```scala
  Gen.randomIpBetween(IP(192,0,0,1),IP(192,0,0,255))
  res1: v4.IP = 192.0.0.201
```
