package librame.adapter.primary

import org.atnos.eff._
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.{either, future}
import cats.effect.IO

import librame.application.service.EffService.ServiceEither


trait EffExtension extends either with future {
  implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext

  type FutureStack = Fx.fx2[ServiceEither, TimedFuture]
  type IOStack     = Fx.fx2[ServiceEither, IO]

}
