package librame.secondary.adapter.rdb

import SlickDatabaseConfig.profile
import slick.lifted.Tag


/**
 * @param tag
 * @param tableName
 * @tparam M
 */
abstract class SlickTable[M](tag: Tag, tableName: String)
  extends profile.Table[M](tag, tableName)
  with SlickCustomColumnType
