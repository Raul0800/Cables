package com.barsum.cables.repositories

import cats.effect.IO
import com.barsum.cables.models.db.Container
import doobie.implicits._
import doobie.{ConnectionIO, Transactor}

import scala.concurrent.ExecutionContext

object ContainerRepository {
  implicit val cs = IO.contextShift(ExecutionContext.global)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", "jdbc:postgresql://localhost:5432/postgres", "postgres", ""
  )

  def find(n: String): List[Container] =
    sql"select id, additional, description, name, parent_id, type from public.container where name = $n"
      .query[Container]
      .to[List]
      .transact(xa)
      .unsafeRunSync
}
