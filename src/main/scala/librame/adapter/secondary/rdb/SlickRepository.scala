package ddd.lib.adapter.secondary.rdb

import scala.concurrent.ExecutionContext

trait SlickRepository extends SlickDatabaseConfig with SlickCustomColumnType {
  implicit val ec: ExecutionContext = ExecutionContext.global
}
