package librame.adapter.secondary.rdb

import scala.concurrent.ExecutionContext

import doobie._
import cats.effect.{IO, Blocker}
import play.api.db.DBApi

class DoobieDatabaseConfig(db: DBApi) {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  val connection = db.database("default").getConnection()

  val xa = Blocker[IO].map {blocker =>
    Transactor.fromConnection[IO](
      connection,
      blocker
    )
  }
}
