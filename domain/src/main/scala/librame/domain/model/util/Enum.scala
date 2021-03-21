package librame.domain.model.util

trait Enum
trait IntEnum    extends Enum { val code: Int    }
trait StringEnum extends Enum { val code: String }

object Enum {
  abstract class Of[T <: Enum] {
    val values: Seq[T]
  }
}

object IntEnum {
  abstract class Of[T <: IntEnum] extends Enum.Of[T] {
    def apply(code: Int): T = values.find(_.code == code).get
  }
}

object StringEnum {
  abstract class Of[T <: StringEnum] extends Enum.Of[T] {
    def apply(code: String): T = values.find(_.code == code).get
  }
}
