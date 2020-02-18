package com.cscie88a.week3

import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

class HarvardStudentTest
    extends WordSpec
    with Matchers
    with BeforeAndAfterAll {


  "HarvardStudent case class" should {
    val student = HarvardStudent("Truman", "Biro", "CS", 100)

    "create a HarvardStudent instance without using the new operator" in {
      student.firstName should be("Truman")
      student.lastName should be("Biro")
      student.subjectName should be("CS")
      student.percentScore should be(100)
    }

    "fullName should return full name all capitalized" in {
      student.fullName should be("TRUMAN BIRO")
    }

    "failedSubject should return true if percentScore > 50" in {
      student.failedSubject should be(true)
    }
    "failedSubject should return false if percentScore < 50" in {
      student.percentScore = 1
      student.failedSubject should be(false)
    }
  }

  "HarvardStudent list operations" should {
    "fromCSVStrings should return List[HarvardStudent]" in {
      val csvList = List("James,Dean,Math,75", "Bradd,Pitt,Math,35")
      HarvardStudent.fromCSVStrings(csvList) should be(List(HarvardStudent("James","Dean","Math",75),
                                                            HarvardStudent("Bradd","Pitt","Math",35)))
    }

    "avgStudentScore returns average scores" in {
      val list = List(HarvardStudent("James","Dean","Math",75), HarvardStudent("Bradd","Pitt","Math",35))
      HarvardStudent.avgStudentScore(list) should be(55)
    }
  }
}
