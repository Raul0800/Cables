package com.barsum.cables.services

import com.barsum.cables.repositories.ContainerRepository

object ContainerService {
  def findContainersByName(name: String) = ContainerRepository.find(name)
}