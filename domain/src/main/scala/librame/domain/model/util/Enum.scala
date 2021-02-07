package librame.domain.model.util

trait Enum
trait EnumInt    extends Enum { val code: Int }
trait EnumString extends Enum { val code: String }

object Enum {
  abstract class Of[T <: Enum] {
    val values: Seq[T]
  }
}

object EnumInt {
  abstract class Of[T <: EnumInt] extends Enum.Of[T] {
    def apply(code: Int): T = values.find(_.code == code).get
  }
}

object EnumString {
  abstract class Of[T <: EnumString] extends Enum.Of[T] {
    def apply(code: String): T = values.find(_.code == code).get
  }
}
