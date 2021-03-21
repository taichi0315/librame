/*
 * Copyright (c) 2021 io.github.neppyaga
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

import play.api.libs.json.{Reads, JsSuccess, JsError}
import play.api.mvc.Results.BadRequest
import play.api.mvc._

trait JsonHelper {

  /** @param request
    * @param reads
    * @tparam T
    * @return
    */
  def bindFromRequest[T](implicit
    request: Request[AnyContent],
    reads: Reads[T]
  ): Either[Result, T] =
    for {
      json   <- request.body.asJson.toRight(BadRequest)
      result <- json.validate[T] match {
        case JsSuccess(v, _) => Right(v)
        case JsError(e)      => Left(BadRequest(JsError.toJson(e)))
      }
    } yield result
}

object JsonHelper extends JsonHelper
