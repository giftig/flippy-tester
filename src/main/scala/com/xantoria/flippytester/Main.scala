package com.xantoria.flippytester

import scala.concurrent.ExecutionContext

import akka.actor._
import akka.io.{IO => AkkaIO}
import net.liftweb.json._
import org.slf4j.LoggerFactory
import spray.can.Http

import com.xantoria.flippy.api.{API => FlippyAPI}
import com.xantoria.flippy.db.RedisBackend
import com.xantoria.flippy.serialization.DefaultFormats

object Main {
  private val logger = LoggerFactory.getLogger("main")

  def main(args: Array[String]): Unit = {
    val host = "0.0.0.0"
    val port = 9001
    val redisHost = "redis"
    val redisPort = 6379

    logger.info(s"Starting flippy-tester on interface $host:$port")
    logger.info(s"Using redis at $redisHost:$redisPort")

    implicit val ec = ExecutionContext.global
    implicit val system = ActorSystem("flippy-test")
    implicit val formats: Formats = DefaultFormats

    val backend = new RedisBackend(redisHost, redisPort, "flippy-tester")
    val service = system.actorOf(Props(new FlippyAPI(backend)))

    AkkaIO(Http) ! Http.Bind(service, interface = host, port = port)
  }
}
