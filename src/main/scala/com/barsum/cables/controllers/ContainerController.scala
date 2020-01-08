package com.barsum.cables.controllers

import cats.effect.Sync
import cats.implicits.{toFoldableOps => _, _}
import com.barsum.cables.models.rest.ContainerName
import com.barsum.cables.services.ContainerService
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe.{jsonEncoder, jsonOf}
import org.http4s.dsl.Http4sDsl
import org.http4s.{EntityDecoder, HttpRoutes}

class ContainerController[IO[_] : Sync]() extends Http4sDsl[IO] {
  implicit val decoderInpData: EntityDecoder[IO, ContainerName] = jsonOf[IO, ContainerName]

  def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case req@POST -> Root => {
      for {
        inputData <- req.as[ContainerName].map(x => x.check)
        resp <- inputData match {
          case Left(x) => BadRequest(x)
          case Right(x) => {
            val res = ContainerService.findContainersByName(x)
            Ok(res.asJson)
          }
        }
      } yield (resp)
    }
  }
}
