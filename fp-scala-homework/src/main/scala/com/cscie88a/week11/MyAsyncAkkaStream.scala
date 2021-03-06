package com.cscie88a.week11

import akka.NotUsed
import akka.stream._
import akka.stream.scaladsl._
import com.cscie88a.util.StreamUtil

import scala.concurrent.Future
import scala.concurrent.duration._

object MyAsyncAkkaStream {

  val numTo20Source = Source(1 to 20)

  val add2Flow: Flow[Int, Int, NotUsed] =
    Flow[Int]
      .map(_ + 2)

  val simplePipeline: RunnableGraph[Future[Seq[Int]]] =
    numTo20Source
      .via(add2Flow)
      .throttle(1, 1 seconds)
      .toMat(Sink.seq[Int])(Keep.right)

  val delayedPipeline: RunnableGraph[Future[Seq[Int]]] =
    numTo20Source
      .via(add2Flow)
      .delay(5 seconds, OverflowStrategy.backpressure)
      .toMat(Sink.seq[Int])(Keep.right)

  val bufferedPipeline: RunnableGraph[Future[Seq[Int]]] =
    numTo20Source
      .via(add2Flow)
      .buffer(5, OverflowStrategy.dropHead)
      .throttle(1, 5 seconds)
      .toMat(Sink.seq[Int])(Keep.right)
}
