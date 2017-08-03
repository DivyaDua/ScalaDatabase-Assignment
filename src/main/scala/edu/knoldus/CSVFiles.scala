package edu.knoldus

import java.io.{File, PrintWriter}

import scala.io.Source

object CSVFiles {

  def readFile(file: String): List[String] ={

    val source: File = new File(file)
    val content: String = Source.fromFile(source).getLines.mkString("\n")

    content.split("\n").toList
  }

  def parseCSV(line: String): List[String] = {

    def parse(line: String, list: List[String],value: String, count: Int, len: Int): List[String] = {

      if(len == line.length)
      {
        list ::: List(value)
      }
      else {
        val character = line(len)

        if(character != ','){
          if(character != '"') {
            parse(line, list, value + character, count, len + 1)
          }
          else {
            parse(line,list, value, count + 1, len + 1)
          }
        }
        else {
          if(count % 2 == 0) {
            parse(line, list ::: List(value), "", count, len + 1)
          }
          else {
            parse(line, list, value + character, count, len + 1)
          }
        }
      }

    }
    parse(line, Nil, "", 0, 0)
  }

  def readAndParse(file: String): List[List[String]] ={

    val lines: List[String] = readFile(file)
    val queryList: List[List[String]] = lines.map(l => parseCSV(l))
    queryList
  }

  def writeToFile(content: List[List[String]], time: List[Long], fileName: String): Boolean = {

    val testCaseId: List[String]  = content.map(_.head)
    val description: List[String] = content.map(_(1))
    val query: List[String] = content.map(_(3))
    val timeList: List[String] = time.map(_.toString)

    val outputList: List[String] = testCaseId zip description zip query zip timeList map {
      case (((a,b),c),d) => a + ", " + b + ", " + "\"" + c + "\", " + d
    }

    val writeToFile = new PrintWriter("/home/knoldus/IdeaProjects/ScalaDatabase/" + fileName)

    try {
      writeToFile.write(outputList.mkString("\n"))
      writeToFile.close()
      true
    }
    catch {
      case e: Exception => false
    }
  }

  def writeCombinedFile(mySQLFile: String, postgresFile: String, SQLiteFile: String, output: String) = {

    val mysqlContent: List[List[String]] = readAndParse(mySQLFile)
    val postgresContent: List[List[String]] = readAndParse(postgresFile)
    val sqliteContent: List[List[String]] = readAndParse(SQLiteFile)

    val testCaseId: List[String]  = mysqlContent.map(_.head)
    val description: List[String] = mysqlContent.map(_(1))
    val mysqlTime: List[String] = mysqlContent.map(_(3))

    val postgresTime: List[String] = postgresContent.map(_(3))

    val sqliteTime: List[String] = sqliteContent.map(_(3))

    val outputList: List[String] = testCaseId zip description zip mysqlTime zip postgresTime zip sqliteTime map {
      case ((((a,b),c),d),e) => a + ", " + b + ", " + c + ", " + d + ", " + e
    }

    val writeToFile = new PrintWriter("/home/knoldus/IdeaProjects/ScalaDatabase/" + output)

    try {
      writeToFile.write(outputList.mkString("\n"))
      writeToFile.close()
      true
    }
    catch {
      case e: Exception => false
    }





  }



}
