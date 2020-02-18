package com.cscie88a.week3

import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

class HarvardStudentTest
    extends WordSpec
    with Matchers
    with BeforeAndAfterAll {


  "HarvardStudent case class" should {
    "create a HarvardStudent instance without using the new operator" in {
      val student = HarvardStudent("Truman", "Biro", "CS", "100")
      student.firstName should be("Truman")
      student.lastName should be("Biro")
      student.subjectName should be("CS")
      student.percentScore should be("100")
    }
  }

  "HarvardStudent list operations" should {
    // add unit tests for problem 5 below

  }

}
