package com.cscie88a.week6

import org.scalatest.{ WordSpec, BeforeAndAfterAll, Matchers }

final class FunctionUtils2Test
    extends WordSpec
    with Matchers
    with BeforeAndAfterAll {

  "prefixLogger" should {
      "concatenate" in {
        FunctionUtils2.prefixLogger("<scala>")("rocks") should be("<scala> rocks")
      }
    }

  "sumInt" should {
      "sum items in a list" in {
        FunctionUtils2.sumInt(List(1, 2, 3, 4)) should be(10)
      }
    }

  "productInt" should {
      "multiply items in a list" in {
        FunctionUtils2.productInt(List(1, 2, 3, 4)) should be(24)
      }
    }

  "all" should {
      "return true if all items are true" in {
        FunctionUtils2.all(List(true, true)) should be(true)
      }
      "return false if not all items are true" in {
        FunctionUtils2.all(List(true, false)) should be(false)
      }
    }

  "any" should {
      "return true if one item is true" in {
        FunctionUtils2.any(List(true, false)) should be(true)
      }

      "return false if no items are true" in {
        FunctionUtils2.any(List(false, false)) should be(false)
      }
    }

  "tokenize" should {
    val sentence1 = List("I am a list")
    val sentence2 = List("I am", "a list to tokenize")
    val sentence3 = List("I", "am", "a", "list")
    val sentence4 = List("I", "am", " ", "list")
    "return a list of strings" in {
      FunctionUtils2.tokenize(sentence1) should be(List("I", "am", "a", "list"))
      FunctionUtils2.tokenize(sentence2) should be(List("I", "am", "a", "list", "to", "tokenize"))
      FunctionUtils2.tokenize(sentence3) should be(List("I", "am", "a", "list"))
      FunctionUtils2.tokenize(sentence4) should be(List("I", "am", "list"))
    }
  }

  "updateCounts" should {
    "update counts of word" in {
      FunctionUtils2.updateCounts("this", Map("this" -> 5, "is" -> 1)) should be(Map("this" -> 6, "is" -> 1))
    }
    "update counts with empty Map" in {
      FunctionUtils2.updateCounts("this", Map()) should be(Map("this" -> 1))
    }
    "adds word if not in map" in {
      FunctionUtils2.updateCounts("a", Map("this" -> 5, "is" -> 1)) should be(Map("this" -> 5, "is" -> 1, "a" -> 1))
    }
  }

  "wordCount" should {
    val sentence1 = List("I am a list")
    val map1 = Map("I" -> 1, "am" -> 1, "a" -> 1, "list" -> 1)
    val sentence2 = List("I am I list")
    val map2 = Map("I" -> 2, "am" -> 1, "list" -> 1)

    "returns Map if one of each word" in {
      FunctionUtils2.wordCount(sentence1) should be(map1)
    }

    "adds to wordcount if word in list" in {
      FunctionUtils2.wordCount(sentence2) should be(map2)
    }
  }
}
