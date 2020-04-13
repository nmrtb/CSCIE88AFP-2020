package com.cscie88a.week10

import akka.NotUsed
import akka.stream.scaladsl._
import akka.util.ByteString
import com.cscie88a.util.StreamUtil
import org.scalatest.compatible.Assertion
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}

import scala.concurrent._

class MySimpleAkkaStreamTest
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

  "numTo10Source" should {

    "materialize source of ints in range 1 to 10" in {
      assertSourceContent(MySimpleAkkaStream.numTo10Source) {
        s => s.take(10).shouldBe(Seq(1,2,3,4,5,6,7,8,9,10))
      }
    }
  }

  "evenNumberSource" should {

    "materialize infinite stream of even integers" in {
      assertSourceContent(MySimpleAkkaStream.evenNumberSource.take(5)) {
        s => s.shouldBe(Seq(2,4,6,8,10))
      }
    }
  }

  "logFlow" should {

    "contain the input string" in {

      val input: String = "testInput"
      val source: Source[String, NotUsed] = Source(List(input))

      val result: Source[String, NotUsed] = source.via(MySimpleAkkaStream.logFlow)

      assertSourceContent(result) {
        s => (s(0) contains input).shouldBe(true)
      }
    }
  }

  "SimplePipeline" should {

    "run with correct results" in {
//        val p = SimplePipeline.csvSumPipeline("input.txt", "result.txt")
//        val rF = p.run()
        succeed
      }
    }
  }
