package ddd.lib.adapter.secondary.rdb

import SlickDatabaseConfig.profile
import slick.lifted.Tag


abstract class SlickTable[M](tag: Tag, tableName: String) extends profile.Table[M](tag, tableName) with SlickCustomColumnType
