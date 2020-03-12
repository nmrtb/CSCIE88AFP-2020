package com.cscie88a.week6

import org.scalatest.{ WordSpec, BeforeAndAfterAll, Matchers }

final class FunctionUtils2Test
    extends WordSpec
    with Matchers
    with BeforeAndAfterAll {
    // add your tests below

  "prefixLogger" should {
      "concatenate" in {
        FunctionUtils2.prefixLogger("<scala>")("rocks") should be("<scala> rocks")
      }
    }
}
