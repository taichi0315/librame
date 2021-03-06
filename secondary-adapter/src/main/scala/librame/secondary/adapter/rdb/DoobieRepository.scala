package librame.secondary.adapter.rdb

import play.api.db.DBApi

class DoobieRepository(db: DBApi) extends DoobieDatabaseConfig(db) with DoobieCustomColumnType
