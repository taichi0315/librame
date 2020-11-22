package librame.adapter.primary

import org.atnos.eff._
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.{either, future}

import librame.application.service.EffService.ServiceEither


trait EffExtension extends either with future {
  implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext

  type Stack = Fx.fx2[ServiceEither, TimedFuture]

}
