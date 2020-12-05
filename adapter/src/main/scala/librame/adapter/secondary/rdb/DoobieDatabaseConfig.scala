package librame.adapter.secondary.rdb

import scala.concurrent.ExecutionContext

import cats.effect.{IO, Blocker}
import doobie._
import play.api.db.DBApi

abstract class DoobieDatabaseConfig(db: DBApi) {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  val connection = db.database("default").getConnection()

  val xa: Transactor[IO] =
    Transactor.fromConnection[IO](
      connection,
      Blocker.liftExecutionContext(ExecutionContexts.synchronous)
    )
}
