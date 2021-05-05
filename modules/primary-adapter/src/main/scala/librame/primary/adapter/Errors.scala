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

case class Error(status: Status, errorCode: String, errorMessage: String) {
  def toResult: Result = status(Json.toJson(JsValueError(errorCode, errorMessage)))
}

trait Errors {
  def E_NOT_FOUND(message: String)       = Error(NotFound, "not-found", message)
  def E_BAD_REQUEST(message: String)     = Error(BadRequest, "bad-request", message)
  def E_VALIDATION(message: String)      = Error(BadRequest, "invalid-value", message)
  def E_DUPLICATION(message: String)     = Error(BadRequest, "duplicate-entry", message)
  def E_INTERNAL_SERVER(message: String) = Error(InternalServerError, "internal-server", message)
  def E_AUTHENTICATION(message: String)  = Error(Unauthorized, "autentication-failture", message)
  def E_AUTHORIZATION(message: String)   = Error(Unauthorized, "authorization-failture", message)
}

object Errors extends Errors
