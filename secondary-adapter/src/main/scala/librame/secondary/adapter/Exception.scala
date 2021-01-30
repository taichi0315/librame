package librame.secondary.adapter

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException

object exception {
  type ConstraintException = MySQLIntegrityConstraintViolationException
}
