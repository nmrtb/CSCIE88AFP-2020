package com.cscie88a.week11

import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.kafka.scaladsl._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}
import com.cscie88a.util.StreamUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer

import scala.concurrent._
import scala.concurrent.duration._


object MyKafkaConsumer {
  import StreamUtil.defaultActorSystem._

  val MyTopic = "orders"
  val KafkaConfPath = "com.example.akka.kafka.consumer"
  val r = scala.util.Random

  // source config
  val config = akkaStreams1System.settings.config.getConfig(KafkaConfPath)
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)

  // Source
  val kafkaSource: Source[ConsumerRecord[String, String], Consumer.Control] =
    Consumer
      .plainSource(consumerSettings, Subscriptions.topics(MyTopic))

  // Flows
  val runningAvgFlow: Flow[ConsumerRecord[String, String], Double, NotUsed] =
    Flow[ConsumerRecord[String, String]]
      .map { record => record.value.toDouble }
      .conflateWithSeed(Seq(_))(_ :+ _)
      .map { s => s.sum / s.size }

  val randomSampleFlow: Flow[ConsumerRecord[String, String], Double, NotUsed] =
    Flow[ConsumerRecord[String, String]]
      .map { record => record.value.toInt }
      .conflateWithSeed(Seq(_))(_ :+ _)
      .map { s => s(r.nextInt(s.size)) }

  val statsFlow: Flow[ConsumerRecord[String, String], (Int, Double), NotUsed] =
    Flow[ConsumerRecord[String, String]]
      .map { record => record.value.toDouble }
      .conflateWithSeed(Seq(_))(_ :+ _)
      .map { s =>
        val n = s.size
        val mean = s.sum / n
        (n, mean)
      }

  // Sinks
  val printSinkAvg: Sink[Double, Future[Done]] =
    Sink.foreach(v => println(s"Avg of records: $v"))

  val printSinkSample: Sink[Double, Future[Done]] =
    Sink.foreach(v => println(s"Sample of records: $v"))

  val printSinkStats: Sink[(Int, Double), Future[Done]] =
    Sink.foreach(v => println(s"n: ${v._1} mean: ${v._2}"))

  // Processing pipelines
  val kafkaConsumerAvgPipeline: RunnableGraph[Future[Done]] =
    kafkaSource
      .via(runningAvgFlow)
      .throttle(1, 10 seconds)
      .toMat(printSinkAvg)(Keep.right)

  val myKafkaSampler: RunnableGraph[Future[Done]] =
    kafkaSource
      .via(randomSampleFlow)
      .throttle(1, 5 seconds)
      .toMat(printSinkSample)(Keep.right)

  val myKafkaSamplerSummary: RunnableGraph[Future[Done]] =
    kafkaSource
      .via(statsFlow)
      .throttle(1, 5 seconds)
      .toMat(printSinkStats)(Keep.right)

  // kafkaConsumerPipeline.run()
}
