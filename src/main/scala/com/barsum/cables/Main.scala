package com.barsum.cables

import cats.effect.{ExitCode, IO, IOApp}
import com.barsum.cables.controllers.ContainerController
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(new ContainerController[IO]().routes.orNotFound)
      .serve
      .compile
      .drain
      .map(_ => ExitCode.Success)
}
