package librame.adapter.secondary.rdb

import javax.inject.Inject
import scala.concurrent.ExecutionContext

import doobie._
import cats.effect._
import play.api.db.DBApi

class DoobieDatabaseConfig @Inject()(db: DBApi) {
  
  implicit val cs = IO.contextShift(ExecutionContext.global)

  val connection = db.database("default").getConnection()

  val xa = Blocker[IO].map { b =>
    Transactor.fromConnection[IO](connection, b)
  }
}
