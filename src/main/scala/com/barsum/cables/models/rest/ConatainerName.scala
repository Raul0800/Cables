package com.barsum.cables.models.rest

case class ContainerName(name: String) {

  import ContainerName._

  def check = checkCorrectData(name)
}

object ContainerName {
  def checkCorrectData(name: String): Either[String, String] = {
    Right(name)
  }
}
