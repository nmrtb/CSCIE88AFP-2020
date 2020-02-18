package com.cscie88a.week3

import scala.collection.mutable.ListBuffer

case class HarvardStudent(firstName: String,
                          lastName: String,
                          subjectName: String,
                          var percentScore: Int) {

  def fullName: String = {
    s"${firstName.split("").map(_.capitalize).mkString("")} " +
      s"${lastName.split("").map(_.capitalize).mkString("")}"
  }

  def failedSubject: Boolean =
    if (percentScore < 50) true else false

}

object HarvardStudent {
  def apply(csv: String): HarvardStudent = {
    val fields = csv.split(",")
    HarvardStudent(fields(0), fields(1), fields(2), fields(3).toInt)
  }

  def fromCSVStrings(csvList: List[String]): List[HarvardStudent] = {
    var students = new ListBuffer[HarvardStudent]()
    csvList.foreach { student =>
                      val fields = student.split(",")
                      students += HarvardStudent(fields(0), fields(1), fields(2), fields(3).toInt)
    }
    students.toList
  }

  def avgStudentScore(list: List[HarvardStudent]): Double = {
    var total = 0
    var count = 0
    list.foreach { student =>
                   total += student.percentScore
                   count += 1}
    total / count
  }

  def avgPassingScore(list: List[HarvardStudent]): Double = {
    var total = 0
    var count = 0
    list.foreach { student =>
                    if (student.failedSubject == false) {
                      total += student.percentScore
                      count += 1}
                    }
    total / count
  }
}