package com.cscie88a.week6

object FunctionUtils2 {
  def prefixLogger(prefix: String): String => String = (str: String) => {
    prefix + " " + str
  }

  def sumInt(inList: List[Int]): Int = {
    inList.foldRight(0) { (a, b) => a + b }
  }

  def productInt(inList: List[Int]): Int = {
    inList.foldRight(1) { (a, b) => a * b }
  }

  def all(inList: List[Boolean]): Boolean = {
    inList.foldRight(true) { (a, b) => a && b }
  }

  def any(inList: List[Boolean]): Boolean = {
    inList.foldRight(false) { (a, b) => a || b }
  }

  def tokenize(text: List[String]): List[String] = {
    for {
      item <- text
      token <- item.split(" ")
    } yield (token)
  }

  def updateCounts(word: String, counts: Map[String, Int]): Map[String, Int] = {
    val v = counts getOrElse(word, 1)

    if (counts contains word) {
      counts + (word -> (v + 1))
    } else {
      counts + (word -> v)
    }
  }

  def wordCount(text: List[String]): Map[String, Int] = {

    // Below doesn't "increment" if word appears twice
    val a = for {
      token <- tokenize(text) // creates list of words for iterating
      (k, v) <- updateCounts(token, Map()) // creates tuples (word, 1))
    } yield (k, v) // Maps to List of all tuples

    // NOTE:  My problem is toMap (called below for return) overwrites duplicates
    //        The problem comes from passing in the empty Map() to updateCounts()
    //        Ideally I'd add another line and pass in (k, v) in the form of a Map
    //        But, no matter what I do, (k, v) remains a tuple - I can't map it to a Map

    // Below works, doesn't use updateCounts
    // https://janzhou.org/2015/scala-functional-programming-for-word-count.html
    //    tokenize(text).groupBy((word:String) => word).mapValues(_.length)
    a.toMap
  }
}

case class Student(
  id: String,
  firstName: String,
  lastName: String,
  email: String,
  gender: String,
  country: String
)

object Students {
  val allStudents: List[Student] = List(
    Student("1", "Emmy", "Conrart", "econrart0@gizmodo.com", "male", "China"),
    Student("2", "Marin", "Blasoni", "mblasoni1@edublogs.org", "female", "United States"),
    Student("3", "Jesse", "Chismon", "jchismon2@hostgator.com", "male", "China"),
    Student("4", "Delmore", "Scriver", "dscriver3@boston.com", "male", "United States"),
    Student("5", "Jocelyn", "Blaxlande", "jblaxlande4@europa.eu", "female", "China")
  )

  val allStudentsMap: Map[String, Student] = Map(
    "1" -> allStudents(0),
    "2" -> allStudents(1),
    "3" -> allStudents(2),
    "4" -> allStudents(3),
    "5" -> allStudents(4)
  )

  def studentNamesByCountry(country: String): List[String] =
    allStudents
      .filter(student => student.country == country)
      .map(student => student.firstName + " " + student.lastName)

  def studentTotalsByCountry(country: String): Int =
    allStudents.map(item => item.country)
    .filter(_ == country)
    .size
}

case class Subject(
  id: String,
  name: String
)

object Subjects {
  val allSubjects: Map[Int, Subject] = Map(
    1 -> Subject("1", "Physics"),
    2 -> Subject("2", "Chemistry"),
    3 -> Subject("3", "Math"),
    4 -> Subject("4", "English")
  )
}
case class Score(
  studentId: Int,
  subjectId: Int,
  numericScore: Int
)

object Scores {
  val allScores: List[Score] = List(
    Score(1, 1, 87),
    Score(1, 2, 92),
    Score(2, 1, 82),
    Score(2, 2, 80),
    Score(2, 3, 89),
    Score(2, 4, 85),
    Score(3, 3, 89),
    Score(3, 4, 90),
    Score(4, 1, 89),
    Score(4, 2, 86),
    Score(4, 4, 90),
    Score(5, 2, 90),
    Score(5, 3, 87),
    Score(5, 4, 92)
  )

  def scoresByStudentId(studentId: Int,
                        scores: List[Score],
                        subjectMap: Map[Int, Subject])
                        : Map[String, Int] = {

    scores
      .filter( score => score.studentId == studentId)
      .map( score => (subjectMap(score.subjectId).name, score.numericScore))
      .toMap
  }

  def scoresBySubjectId(subjectId: Int,
                        scores: List[Score],
                        studentMap: Map[String, Student])
                        : Map[String, Int] = {

    scores
      .filter( score => score.subjectId == subjectId)
      .map( score =>
            ( studentMap(score.studentId.toString).firstName + " " + studentMap(score.studentId.toString).lastName,
              score.numericScore) )
      .toMap
  }
}
