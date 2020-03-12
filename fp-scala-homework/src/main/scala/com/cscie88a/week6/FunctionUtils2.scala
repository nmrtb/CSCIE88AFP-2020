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
    val a = counts getOrElse(word, 1)

    if (counts contains word) {
      counts + (word -> (a + 1))
    } else {
      counts + (word -> a)
    }
  }
}
