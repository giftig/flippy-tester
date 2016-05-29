package com.xantoria.flippytester

import scala.concurrent.ExecutionContext

import akka.actor._
import akka.io.{IO => AkkaIO}
import net.liftweb.json._
import spray.can.Http

import com.xantoria.flippy.api.{API => FlippyAPI}
import com.xantoria.flippy.db.RedisBackend
import com.xantoria.flippy.serialization.DefaultFormats

object Main {
  def main(args: Array[String]): Unit = {
    implicit val ec = ExecutionContext.global
    implicit val system = ActorSystem("flippy-test")
    implicit val formats: Formats = DefaultFormats

    val backend = new RedisBackend("redis", 6379, "flippy-tester")
    val service = system.actorOf(Props(new FlippyAPI(backend)))

    AkkaIO(Http) ! Http.Bind(service, interface = "0.0.0.0", port = 9001)
  }
}
