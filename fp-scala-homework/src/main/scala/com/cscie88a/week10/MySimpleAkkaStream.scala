package com.cscie88a.week10

import java.nio.file.Paths

import akka.NotUsed;
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString
import com.cscie88a.util.StreamUtil
import akka.stream.scaladsl.Framing

import scala.concurrent._

object MySimpleAkkaStream {

  import StreamUtil.defaultActorSystem._

  val numTo10Source: Source[Int, NotUsed] = Source(1 to 10)

  val evenNumberSource: Source[Int, NotUsed] =
    Source.fromIterator(() => Stream.iterate( 2 )( _ + 2 ).iterator)

  val logFlow: Flow[String, String, NotUsed] =
    Flow[String].map(s"${Thread.currentThread().getName}: " + _)

  val byteStringFlow: Flow[String, ByteString, NotUsed] =
   Flow[String].map((input: String) => akka.util.ByteString(input))

  val evensPipeline: RunnableGraph[NotUsed] =
    evenNumberSource
        .takeWhile(_ <= 100)
        .via(Flow[Int].map(_.toString))
        .via(byteStringFlow)
        .to(FileIO.toPath(Paths.get("stream.log")))

//  def lineSource(path: String): Source[String, Future[IOResult]] =
//    FileIO.fromPath(Paths.get(path))
//      .via(Framing.delimiter(ByteString("\r\n"), maximumFrameLength = 100, allowTruncation = true))
//      .map(_.utf8String)
//
//  val stringCSVSplitter: Flow[String, Array[Int], NotUsed] =
//    Flow[String]
//      .map(_.split(",")
//      .map(_.toInt))
//
//  val convertFlow: Flow[Array[Int], ByteString, NotUsed] =
//    Flow[Int]
//      .fold(0)(_+_)
//      .map(i => ByteString(i))
//
//  // changed input from String to ByteString (this also means convertFlow outputs ByteString)
//  def lineSink(filename: String): Sink[ByteString, Future[IOResult]] =
//    FileIO.toPath(Paths.get(filename))
//
//
//  def csvSumPipeline(input:String, output:String): RunnableGraph[Future[IOResult]] =
//    lineSource(input)
//      .via(stringCSVSplitter)
//      .via(convertFlow)
//      .to(lineSink(output))
}
