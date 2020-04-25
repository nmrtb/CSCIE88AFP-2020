package com.cscie88a.week11

import akka.kafka.ProducerSettings
import akka.kafka.scaladsl._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}
import com.cscie88a.util.StreamUtil
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import scala.concurrent._
import scala.concurrent.duration._

object MyKafkaProducer {
  import StreamUtil.defaultActorSystem._

  val MyTopic = "orders"
  val KafkaConfPath = "com.example.akka.kafka.producer"
  val r = scala.util.Random

  // Source
  val intSource: Source[Int, NotUsed] =
    Source(for (i <- 1 to 100) yield r.nextInt(100) + 1) // +1 makes it 1:100 as 0-indexed

  // Flow
  def producerRecordFlow(topic: String): Flow[String, ProducerRecord[String, String], NotUsed] =
    Flow[String].map(new ProducerRecord[String, String](topic, _))

  // kafka producer settings
  val config = akkaStreams1System.settings.config.getConfig(KafkaConfPath)
  val producerSettings = ProducerSettings(config, new StringSerializer, new StringSerializer)

  // Pipeline
  val kafkaProducerPipeline: RunnableGraph[Future[Done]] =
    intSource
      .map(_.toString)
      .via(producerRecordFlow(MyTopic))
      .toMat(Producer.plainSink(producerSettings))(Keep.right)

  // kafkaProducer.run()

}
