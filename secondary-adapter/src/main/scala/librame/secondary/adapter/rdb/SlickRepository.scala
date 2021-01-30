package librame.secondary.adapter.rdb

import scala.concurrent.ExecutionContext

trait SlickRepository extends SlickDatabaseConfig with SlickCustomColumnType {
  implicit val ec: ExecutionContext = ExecutionContext.global
}
