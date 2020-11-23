package librame.adapter.secondary.rdb

import doobie._
import doobie.implicits._

trait DoobieRepositoryMock extends DoobieDatabaseConfig {
  def get: ConnectionIO[Option[String]] =
    sql"select email from users".query[String].option
}
