package librame.adapter.secondary.rdb

import play.api.db.DBApi

class DoobieRepository(db: DBApi)
  extends DoobieDatabaseConfig(db)
  with DoobieCustomColumnType
