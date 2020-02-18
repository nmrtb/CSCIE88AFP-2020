package com.cscie88a.week3

case class HarvardStudent(firstName: String,
                          lastName: String,
                          subjectName: String,
                          percentScore: String) {
}

object HarvardStudent {
  def apply(csv: String): HarvardStudent = {
    val fields = csv.split(",")
    HarvardStudent(fields(0), fields(1), fields(2), fields(3))
  }
}