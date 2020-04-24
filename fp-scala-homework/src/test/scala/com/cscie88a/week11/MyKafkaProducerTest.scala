package com.cscie88a.week11

import akka.Done
import com.cscie88a.util.StreamUtil
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}

import scala.concurrent._

class MyKafkaProducerTest
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

  "KafkaProducer" should {
    "produce int values" in {
      val result: Future[Done] = MyKafkaProducer.kafkaProducerPipeline.run()
      result.map(_ => succeed)
    }
  }
}
