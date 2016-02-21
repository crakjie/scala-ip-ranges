#Scala IP Range is a light lib that allow to iterate over ips.
- Scala IP Range don't use any dependency and don't pollute your dependencies.

#Exemple
Instanciate a new ip.
```scala
  import ipranges.v4._
  val myIp = IP(192,0,0,2)
```

Build a new range.
```scala
  val myRange = IP(192,0,0,1) to IP(192,0,0,15)
  myRange: v4.IPRange = IPRange(192.0.0.2, 192.0.0.3, 192.0.0.4, 192.0.0.5, 192.0.0.6, 192.0.0.7, 192.0.0.8, 192.0.0.9, 192.0.0.10, 192.0.0.11, 192.0.0.12, 192.0.0.13, 192.0.0.14)
```

Iterate over this range is easy a one two tree.

```scala
  for(ip <- IP(192,0,0,1) to IP(192,0,0,255)) {
    println(ip)
  }
```

You can also change the steps
```scala
  val myRange = IP(192,0,0,0) to IP(192,0,15,0) by 256
  myRange: v4.IPRange = IPRange(192.0.1.0, 192.0.2.0, 192.0.3.0, 192.0.4.0, 192.0.5.0, 192.0.6.0, 192.0.7.0, 192.0.8.0, 192.0.9.0, 192.0.10.0, 192.0.11.0, 192.0.12.0, 192.0.13.0, 192.0.14.0, 192.0.15.0)
```


#Generator
The Gen object allow you to easily get random generated IP.

```scala
  Gen.randomIp()
  res1: ipranges.v4.IP = 76.78.201.206
```

Or generate an IP between to IP.

```scala
  Gen.randomIpBetween(IP(192,0,0,1),IP(192,0,0,255))
  res1: v4.IP = 192.0.0.201
```
