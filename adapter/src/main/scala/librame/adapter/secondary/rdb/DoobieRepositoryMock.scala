package librame.adapter.secondary.rdb

import doobie._
import doobie.implicits._

trait DoobieRepositoryMock extends DoobieDatabaseConfig {
  def get(userName: String): ConnectionIO[Option[String]] =
    sql"select email from users where user_name = $userName".query[String].option
}
