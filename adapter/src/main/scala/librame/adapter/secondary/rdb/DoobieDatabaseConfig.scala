package librame.adapter.secondary.rdb

import scala.concurrent.ExecutionContext

import doobie._
import doobie.util.ExecutionContexts
import cats.effect._
import play.api.db.DBApi

class DoobieDatabaseConfig(db: DBApi) {
  
  implicit val cs = IO.contextShift(ExecutionContext.global)

  val connection = db.database("default").getConnection()

  val xa = Transactor.fromConnection[IO](
    connection,
    Blocker.liftExecutionContext(ExecutionContexts.synchronous)
  )
}
