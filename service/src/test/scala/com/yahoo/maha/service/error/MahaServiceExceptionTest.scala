package com.yahoo.maha.service.error

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MahaServiceExceptionTest extends AnyFunSuite with Matchers {
  test("MahaServiceExceptionTest: MahaServiceBadRequestException") {
    val ex : MahaServiceBadRequestException = MahaServiceBadRequestException(message = "Got a bad request exception!")
    assert(ex.getMessage.contains("MahaServiceBadRequestException") && ex.getMessage.contains("Got a bad request exception!"))

    assert(ex.errorCode.equals(400), "BadRequest should throw 400")
  }

  test("MahaServiceExceptionTest: MahaServiceExecutionException") {
    val ex : MahaServiceExecutionException = MahaServiceExecutionException(message = "Got an execution exception!")
    assert(ex.getMessage.contains("MahaServiceExecutionException") && ex.getMessage.contains("Got an execution exception!"))
    assert(ex.errorCode.equals(500), "ServiceExecution should throw 500")
  }

}
