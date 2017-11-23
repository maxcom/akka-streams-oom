import java.time.LocalDateTime

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.{ActorMaterializer, Materializer}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Run {
  def main(args: Array[String]): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val mat: Materializer = ActorMaterializer()
    implicit val ec: ExecutionContext = actorSystem.dispatcher

    val value = Source
      .repeat(1)
      .map(_ ⇒ Array.fill(100 * 1024)(0))
      .grouped(10)
      .flatMapConcat(Source.apply)

    value
      .to(Sink.ignore)
      .run()

    actorSystem.scheduler.schedule(0 millis, 1 minute, new Runnable() {
      override def run() = println(s"Running! ${LocalDateTime.now()}")
    })
  }
}
