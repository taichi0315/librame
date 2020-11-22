package ddd.lib.application.service

import scala.concurrent.{Future, ExecutionContext}

import org.atnos.eff._
import org.atnos.eff.either._
import org.atnos.eff.future._


trait EffService {
  type ServiceEither[T]  = Either[error.ServiceErr, T]
  type _serviceEither[R] = ServiceEither |= R

  implicit class FutureOps[T](futureValue: Future[T])(implicit ec: ExecutionContext) {
    def toEff[R: _future]: Eff[R, T] = fromFuture(futureValue)
  }

  implicit class ServiceEitherOps[T](eitherValue: Either[error.ServiceErr, T])(implicit ec: ExecutionContext) {
    def toEff[R: _serviceEither]: Eff[R, T] = fromEither(eitherValue)
  }
}

object EffService extends EffService
