package com.cscie88a.week10

import java.nio.file.Paths

import akka.NotUsed
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString
import com.cscie88a.util.StreamUtil

import scala.concurrent._

object MySimpleAkkaStream {

  import StreamUtil.defaultActorSystem._

  val numTo10Source: Source[Int, NotUsed] = Source(1 to 10)

  val evenNumberSource: Source[Int, NotUsed] = Source.fromIterator(() => Stream.iterate( 2 )( _ + 2 ).iterator)

  val logFlow: Flow[String, String, NotUsed] =
    Flow[String].map(s"${Thread.currentThread().getName}: " + _)

//  val byteStringFlow: Flow[String, ByteString, NotUsed] =
}
