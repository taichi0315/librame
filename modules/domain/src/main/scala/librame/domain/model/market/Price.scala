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

/** @param money
  */
case class Price(
  money: Money
) {
  val SALES_TAX_RATE = 0.1

  lazy val tax: Money = money * SALES_TAX_RATE

  def +(that: Price): Price          = plus(that)
  def plus(that: Price): Price       = Price(this.money + that.money)
  def -(that: Price): Price          = minus(that)
  def minus(that: Price): Price      = Price(this.money - that.money)
  def *(factor: BigDecimal): Price   = mul(factor)
  def mul(factor: BigDecimal): Price = Price(this.money * factor)
  def /(factor: BigDecimal): Price   = div(factor)
  def div(factor: BigDecimal): Price = Price(this.money / factor)
}

object Price {

  /** @param value
    * @param currency
    * @return
    */
  def apply(value: BigDecimal, currency: Currency = Currency.JPY): Either[Unit, Price] =
    Money(value, currency)
      .map(new Price(_))
}
