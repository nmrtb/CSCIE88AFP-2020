package com.cscie88a.week6

object FunctionUtils2 {
  def prefixLogger(prefix: String): String => String = (str: String) => {
    prefix + " " + str
  }
}
