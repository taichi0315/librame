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

package librame.primary.adapter

import play.api.libs.json._
import play.api.mvc.Result
import play.api.mvc.Results._

case class Error(status: Status, errorCode: String, target: String) {
  def toResult: Result = status(Json.toJson(JsValueError(errorCode, target)))
}

trait Errors {
  def E_NOT_FOUND(target: String)       = Error(NotFound, "not-found", target)
  def E_BAD_REQUEST(target: String)     = Error(BadRequest, "bad-request", target)
  def E_VALIDATION(target: String)      = Error(BadRequest, "invalid-value", target)
  def E_DUPLICATION(target: String)     = Error(BadRequest, "duplicate-entry", target)
  def E_INTERNAL_SERVER(target: String) = Error(InternalServerError, "internal-server", target)
  def E_AUTHENTICATION(target: String)  = Error(Unauthorized, "autentication-failture", target)
  def E_AUTHORIZATION(target: String)   = Error(Unauthorized, "authorization-failture", target)
}

object Errors extends Errors
