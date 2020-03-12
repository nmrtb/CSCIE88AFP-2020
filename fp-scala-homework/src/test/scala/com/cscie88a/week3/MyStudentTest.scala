package com.cscie88a.week3

import org.scalatest.{ WordSpec, BeforeAndAfterAll, Matchers }

final class MyStudentTest
  extends WordSpec
  with Matchers
  with BeforeAndAfterAll {

  "Student class" should {
    val student = new Student("Truman","Biro")

    "creates a Student instance with correct parameters" in {
        student.firstName should be("Truman")
        student.lastName should be("Biro")
    }
    "has a greet method with firstName and lastName uppercased" in {
      student.greet should be("Hello TRUMAN BIRO")
    }
  }
}
