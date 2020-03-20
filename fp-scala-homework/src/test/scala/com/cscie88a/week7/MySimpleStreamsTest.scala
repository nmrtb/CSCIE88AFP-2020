package com.cscie88a.week7

import cats.implicits._
import fs2.{INothing, Pure, Stream, io, text}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

class MySimpleStreamTest
  extends WordSpec
  with Matchers
  with BeforeAndAfterAll {
  "MySimpleStreams" should {

    "contain val numTo10 which contains a stream of range 1 to 10" in {
      val result = 1 to 10
      MySimpleStreams.numTo10.toList should be(result.toList)
    }

    "contain val numFrom10To20 which contains a stream of range 10 to 20" in {
      val result = 10 to 20
      MySimpleStreams.numFrom10To20.toList should be(result.toList)
    }

    "contain val numTo20 which contains a stream of range 1 to 20" in {
      val result = 1 to 20
      MySimpleStreams.numTo20.toList should be(result.toList)
    }

    "contain val evens which contains an infinite stream of all even numbers" in {
      val result = List(2, 4, 6, 8, 10)
      MySimpleStreams.evens.take(5).toList should be(result)
    }

    "contain val odds which contains an infinite stream of all odd numbers" in {
      val result = List(1, 3, 5, 7, 9)
      MySimpleStreams.odds.take(5).toList should be(result)
    }

    "contain val naturalNumbers which contains an infinite stream of all natural numbers" in {
      val result = 1 to 5
      MySimpleStreams.naturalNumbers.take(5).toList should be(result.toList)
    }

    "contain val mult3 which contains an infinite stream of all multiples of 3" in {
      val result = List(3, 6, 9, 12, 15)
      MySimpleStreams.mult3.take(5).toList should be(result)
    }

    "contain val mult4 which contains an infinite stream of all multiples of 4" in {
      val result = List(4, 8, 12, 16, 20)
      MySimpleStreams.mult4.take(5).toList should be(result)
    }

    "contain val mult34Pairs which contains an infinite stream of strings of the pairwise multiples of 3 and 4" in {
      val result = List("[3, 4]", "[6, 8]", "[9, 12]", "[12, 16]", "[15, 20]")
      MySimpleStreams.mult34Pairs.take(5).toList should be(result)
    }

    "contain val mult12 which contains an infinite stream of multiples of 12" in {
      val sample = MySimpleStreams.mult12.take(5).toList

      val result = sample.filterNot( (i) => i % 12 == 0)

      result.length should be(0)

    }
  }
}
