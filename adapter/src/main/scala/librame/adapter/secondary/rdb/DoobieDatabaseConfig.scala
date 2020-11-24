package librame.adapter.secondary.rdb

import scala.concurrent.ExecutionContext

import doobie._
import cats.effect.IO
import play.api.db.DB

trait DoobieDatabaseConfig {
  
  implicit val cs = IO.contextShift(ExecutionContext.global)

  val connection = DB.getConnection()

  val xa = Transactor.fromConnection[IO](
    connection
  )
}
