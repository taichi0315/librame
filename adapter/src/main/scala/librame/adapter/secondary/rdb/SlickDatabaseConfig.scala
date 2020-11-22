package librame.adapter.secondary.rdb

import slick.jdbc.JdbcBackend.Database


trait SlickDatabaseConfig {
  val db: Database = Database.forConfig("rdb")

  val profile = slick.jdbc.MySQLProfile
}

object SlickDatabaseConfig extends SlickDatabaseConfig

