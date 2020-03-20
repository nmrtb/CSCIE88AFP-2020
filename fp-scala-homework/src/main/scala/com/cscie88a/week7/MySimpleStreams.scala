package com.cscie88a.week7

import cats.implicits._
import fs2.{INothing, Pure, Stream, io, text}

object MySimpleStreams {

  // Problem 1
  val numTo10 = Stream.emits(1 to 10)

  val numFrom10To20 = Stream.emits(10 to 20)

  val numTo20 = numTo10 ++ numFrom10To20.drop(1)


  // Problem 2
  val evens = Stream.iterate( 2 )( _ + 2 )

  val odds = Stream.iterate( 1 )( _ + 2 )

  val naturalNumbers = odds.interleave(evens)

  // Problem 3
  def multN(n: Int): Stream[Pure, Int] =
    naturalNumbers.map( _ * n )

  val mult3 = multN(3)

  val mult4 = multN(4)

  val mult34Pairs =
    mult3.zipWith(mult4) { (threes, fours) =>  s"[${threes}, ${fours}]" }

  val mult12 = for (
    i <- mult3;
    j <- mult4
  ) yield i * j

  // Problem 4
//  def pythTest(t: Tuple3[Int, Int, Int]): Boolean = ???
//
//  val upto100: Stream[Pure, Int] = ???
//
//  val pythTriples: Stream[Pure, (Int, Int, Int)] = ???
//
//  def hello: String = "hello"
}
