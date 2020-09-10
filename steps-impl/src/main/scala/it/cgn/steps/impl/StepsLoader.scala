package it.cgn.steps.impl
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.LagomApplication
import com.lightbend.lagom.scaladsl.server.LagomApplicationContext
import com.lightbend.lagom.scaladsl.server.LagomApplicationLoader
import com.lightbend.lagom.scaladsl.server.LagomServer
import com.softwaremill.macwire.wire
import it.cgn.steps.api.StepsService
import play.api.libs.ws.ahc.AhcWSComponents

class StepsLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new StepsApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new StepsApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[StepsService])
}

abstract class StepsApplication(context: LagomApplicationContext)
    extends LagomApplication(context)
    // with CassandraPersistenceComponents
    // with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = {
    serverFor[StepsService](wire[StepsServiceImpl])
  }

  //  override lazy val jsonSerializerRegistry: JsonSerializerRegistry =
  //    StepsSerializerRegistry

  //  clusterSharding.init(
  //    Entity(HelloState.typeKey)(
  //      entityContext => HelloBehavior.create(entityContext)
  //    )
  //  )

}
