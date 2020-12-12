package librame.adapter.primary

import scala.concurrent.{Future, ExecutionContext}

import cats.effect.IO
import org.atnos.eff._
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.addon.cats.effect._
import org.atnos.eff.syntax.either._
import org.atnos.eff.syntax.future._

import librame.application.service.EffService.ServiceEither

class EffExtension()(implicit ec: ExecutionContext)
  extends {
  implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext

  type FutureStack = Fx.fx2[ServiceEither, TimedFuture]
  type IOStack     = Fx.fx2[ServiceEither, IO]

  implicit class FutureStackOps[T](future: Eff[FutureStack, T]) {
    def run: Future[ServiceEither[T]] =
      future.runEither.runAsync
  }

  implicit class IOStackOps[T](io: Eff[IOStack, T]) {
    def run: Future[ServiceEither[T]] =
      io.runEither.unsafeToFuture
  }
}
