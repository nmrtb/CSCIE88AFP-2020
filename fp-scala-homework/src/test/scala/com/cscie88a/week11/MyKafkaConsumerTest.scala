package com.cscie88a.week11

import akka.Done
import com.cscie88a.util.StreamUtil
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}

import scala.concurrent._

class MyKafkaConsumerTest
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

//  "kafkaConsumerAvgPipeline" should {
//    "print running average" in {
//      val result: Future[Done] = MyKafkaConsumer.kafkaConsumerAvgPipeline.run()
//      result.map(_ => succeed)
//    }
//  }
//
//  "myKafkaSampler" should {
//    "print random sample" in {
//      val result: Future[Done] = MyKafkaConsumer.myKafkaSampler.run()
//      result.map(_ => succeed)
//    }
//  }

  "myKafkaSamplerSummary" should {
    "print statistics" in {
      val result: Future[Done] = MyKafkaConsumer.myKafkaSamplerSummary.run()
      result.map(_ => succeed)
    }
  }
}
