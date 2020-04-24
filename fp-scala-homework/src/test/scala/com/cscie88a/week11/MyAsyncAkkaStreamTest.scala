package com.cscie88a.week11

import akka.NotUsed
import akka.stream.scaladsl._
import akka.util.ByteString
import com.cscie88a.util.StreamUtil
import org.scalatest.compatible.Assertion
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent._
import scala.util.{Failure, Success}

class MyAsyncAkkaStreamTest
  extends AsyncWordSpec
  with Matchers
  with BeforeAndAfterAll {

  // create the akka system for stream materialization
  implicit val (sys, mat, _) = StreamUtil.actorSystemInstances("KafkaStreamTest")

  // shutdown the akka system after tests
  override protected def afterAll() {
    sys.terminate()
    super.afterAll()
  }

  /**
    * helper function to test source contents
    * materializes source to a sequence and calls assertion on resulting sequence
    *
    * @param testSource input source
    * @param assertion assertion on sequence
    * @return
    */
  def assertSourceContent[O]
  (testSource: Source[O, NotUsed])
  (assertion: Seq[O] => Assertion): Future[Assertion] = {
    testSource
      .runWith(Sink.seq)
      .map(assertion(_))
  }

  "numTo20Source" should {

    "materialize source of ints in range 1 to 20" in {
      assertSourceContent(MyAsyncAkkaStream.numTo20Source) {
        s => s.take(20).shouldBe(1 to 20)
      }
    }
  }

  "simplePipeline" should {
    val future = MyAsyncAkkaStream.simplePipeline.run()

    "eventually be correct size" in {
      future map { seq => seq.size.shouldBe(20) }
    }
    "eventually contain correct elements" in {
      future map { seq => seq.shouldBe(3 to 22) }
    }
  }

  "delayedPipeline" should {
    val future = MyAsyncAkkaStream.delayedPipeline.run()

    "eventually be correct size" in {
      future map { seq => seq.size.shouldBe(20) }
    }
    "eventually contain correct elements" in {
      future map { seq => seq.shouldBe(3 to 22) }
    }
  }

  "bufferedPipeline" should {
    val future = MyAsyncAkkaStream.bufferedPipeline.run()

    "eventually be less than 20" in {
      future map { seq => assert(seq.size < 20) }
    }

    "eventually contain correct final element" in {
      future map { seq => seq.last.shouldBe(22) }
    }
  }
}
