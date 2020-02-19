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
    csvList.foreach { student => students += apply(student)}
    students.toList
  }

  def avgStudentScore(list: List[HarvardStudent]): Double = {
    var scores = new ListBuffer[Int]
    list.foreach { student => scores += student.percentScore}
    scores.reduce(_ + _) / scores.length
  }

  def avgPassingScore(list: List[HarvardStudent]): Double = {
    val newList = list.filter(_.failedSubject == false)
    avgStudentScore(newList)
  }
}