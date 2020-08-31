import com.google.inject.AbstractModule
import javax.inject._
import models.entities.Contact
import net.codingwell.scalaguice.ScalaModule
import models.toDatabase.{ContactsRepository, IContactsRepository}
import play.api.{Configuration, Environment}
import services.{ContactsService, IContactsService}

class Module(environment: Environment, configuration: Configuration)
    extends AbstractModule
    with ScalaModule {

  override def configure(): Unit = {
    bind[IContactsRepository[Contact]].to[ContactsRepository].in[Singleton]

    bind[IContactsService[Contact]].to[ContactsService].in[Singleton]
  }
}