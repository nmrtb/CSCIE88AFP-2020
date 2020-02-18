package com.cscie88a.week3

class Student(var firstName: String, var lastName: String) {

  def greet: String = {
    s"Hello ${firstName.split("").map(_.capitalize).mkString("")} " +
      s"${lastName.split("").map(_.capitalize).mkString("")}"
  }
}


