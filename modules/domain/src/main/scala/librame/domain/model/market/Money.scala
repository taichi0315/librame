/*
 * Copyright (c) 2021 Kushiro Taichi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package librame.domain.model.market

import scala.math.BigDecimal

import cats.Monoid

/** @param value
  * @param currency
  */
case class Money(
  value: BigDecimal,
  currency: Currency
) {

  /** 不変条件 */
  assert(value >= 0, "金額が0以上")

  /** @param that
    * @return
    */
  def +(that: Money): Money    = plus(that)
  def plus(that: Money): Money =
    that.currency match {
      case this.currency => this.copy(value = this.value + that.value)
      case _             =>
        throw new UnsupportedOperationException("plus not supported for cross-currency comparison")
    }

  /** @param that
    * @return
    */
  def -(that: Money): Money     = minus(that)
  def minus(that: Money): Money = {
    that.currency match {
      case this.currency => this.copy(value = this.value - that.value)
      case _             =>
        throw new UnsupportedOperationException("minus not supported for cross-currency comparison")
    }
  } ensuring (_.value >= 0, "金額が0以上")

  /** @param factor
    * @return
    */
  def *(factor: BigDecimal): Money = mul(factor)
  def mul(factor: BigDecimal): Money = {
    require(factor >= 0, "掛ける値は0以上")
    this.copy(value = this.value * factor)
  }

  /** @param factor
    * @return
    */
  def /(factor: BigDecimal): Money = div(factor)
  def div(factor: BigDecimal): Money = {
    require(factor > 0, "割る値は0より大きい")
    this.copy(value = this.value / factor)
  }
}

object Money {

  /** @param value
    * @param currency
    * @return
    */
  def apply(value: BigDecimal, currency: Currency = Currency.JPY): Either[Unit, Money] =
    Right(value)
      .filterOrElse(_ >= 0, ())
      .map(v => new Money(v, currency))

  /** implict value for Monoid */
  implicit val moneyMonoid: Monoid[Money] = new Monoid[Money] {
    def empty: Money = new Money(0, Currency.JPY)

    def combine(x: Money, y: Money): Money = x + y
  }
}
