package it.cgn.steps.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}


object StepsService  {
  // val TOPIC_NAME = "greetings"
}

trait StepsService extends Service {

  def hello(nome: String): ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._

    named("hello")
      .withCalls(
        pathCall("/api/hello/:id", hello _),
      )
      .withAutoAcl(true)
  }
}