package librame.adapter.secondary.rdb

import doobie._
import cats.effect.IO
import scala.concurrent.ExecutionContext

trait DoobieDatabaseConfig {
  
  implicit val cs = IO.contextShift(ExecutionContext.global)

  val xa = Transactor.fromDriverManager[IO](
    "com.mysql.jdbc.Driver",
    "jdbc:mysql://127.0.0.1:3306/auth?useSSL=false",
    "user",
    "password"
  )
}
