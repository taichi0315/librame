package librame.domain.model

/**
 * @param value
 */
case class Quantity(value: Int) extends SingleValueObject[Int] {
  // 不変条件
  assert(value >= 0)
}

object Quantity {
  def apply(value: Int): Either[Unit, Quantity] =
    Right(value)
      .filterOrElse(_ >= 0, ())
      .map(new Quantity(_))
}
