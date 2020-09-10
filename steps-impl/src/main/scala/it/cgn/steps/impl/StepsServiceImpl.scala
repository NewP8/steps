package it.cgn.steps.impl

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import it.cgn.steps.api.StepsService

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class StepsServiceImpl(
    // clusterSharding: ClusterSharding,
    // persistentEntityRegistry: PersistentEntityRegistry
)(implicit ec: ExecutionContext)
    extends StepsService {

  override def hello(nome: String): ServiceCall[NotUsed, String] =
    ServiceCall { _ =>
      Future.successful("Ciao " + nome + "!")
    }
}
