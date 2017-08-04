package edu.knoldus

import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class CSVFilesTest extends FunSuite with MockitoSugar with CSVFiles{

  val csvFiles = mock[CSVFiles]

  val outputOfReadFile = List("1, Creating Employee table, \"DROP TABLE IF EXISTS Employee;\"," +
    " \"CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\"," +
    " \"DROP TABLE Employee;\"", "2, Altering Employee table by adding EmployeeAge column," +
    " \"CREATE TABLE IF NOT EXISTS Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\"," +
    " \"ALTER TABLE Employee ADD COLUMN EmployeeAge INT;\", \"DROP TABLE Employee;\"")

  val outputOfReadAndParse = List(List("1", " Creating Employee table",
    " DROP TABLE IF EXISTS Employee;",
    " CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);",
    " DROP TABLE Employee;"),
    List("2", " Altering Employee table by adding EmployeeAge column",
      " CREATE TABLE IF NOT EXISTS Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);" ,
      " ALTER TABLE Employee ADD COLUMN EmployeeAge INT;", " DROP TABLE Employee;"))

  test("testing read file method"){
    assert(readFile("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDDL.csv") === outputOfReadFile)
  }

  test("testing parse CSV method"){

    val outputOfParseCSV = List("1", " Creating Employee table",
      " DROP TABLE IF EXISTS Employee;",
      " CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);",
      " DROP TABLE Employee;")

    val inputString = "1, Creating Employee table, \"DROP TABLE IF EXISTS Employee;\", " +
      "\"CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\"," +
      " \"DROP TABLE Employee;\""

    assert(parseCSV(inputString) === outputOfParseCSV)
  }

  when(csvFiles.readFile("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDDL.csv")) thenReturn outputOfReadFile
  when(csvFiles.parseCSV("1, Creating Employee table, \"DROP TABLE IF EXISTS Employee;\"," +
    " \"CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\"," +
    " \"DROP TABLE Employee;\"")) thenReturn List("1", " Creating Employee table",
    " DROP TABLE IF EXISTS Employee;",
    " CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);",
    " DROP TABLE Employee;")

  when(csvFiles.parseCSV("2, Altering Employee table by adding EmployeeAge column," +
    " \"CREATE TABLE IF NOT EXISTS Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\"," +
    " \"ALTER TABLE Employee ADD COLUMN EmployeeAge INT;\", \"DROP TABLE Employee;\"")) thenReturn
    List("2", " Altering Employee table by adding EmployeeAge column",
      " CREATE TABLE IF NOT EXISTS Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);" ,
      " ALTER TABLE Employee ADD COLUMN EmployeeAge INT;", " DROP TABLE Employee;")


  test("testing Read and parse method"){
    assert(readAndParse("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDDL.csv") === outputOfReadAndParse)
  }


  test("testing writeToFile method"){
    val queryList = List(List("1",  "Creating Employee table",  "DROP TABLE IF EXISTS Employee;",
      "CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);",
      "DROP TABLE Employee;"), List("2",  "Altering Employee table by adding EmployeeAge column",
      "CREATE TABLE IF NOT EXISTS Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);",
      "ALTER TABLE Employee ADD COLUMN EmployeeAge INT;",  "DROP TABLE Employee;"))

    val timeList: List[Long] = List(318848097, 511625821)

    val outputFileName = "MySQLDDLoutput.csv"
    assert(writeToFile(queryList, timeList , outputFileName) === "/home/knoldus/IdeaProjects/ScalaDatabase/MySQLDDLoutput.csv")
  }

  test("testing write combined output file method"){
    val mysqlFilePath = "/home/knoldus/IdeaProjects/ScalaDatabase/MySQLDDLoutput.csv"
    val postgresFilePath = "/home/knoldus/IdeaProjects/ScalaDatabase/postDDLoutput.csv"
    val sqlitePath = "/home/knoldus/IdeaProjects/ScalaDatabase/SQLiteDDLoutput.csv"
    val outputFileName = "Output.csv"
    assert(writeCombinedOutputFile(mysqlFilePath, postgresFilePath, sqlitePath, outputFileName) === "/home/knoldus/IdeaProjects/ScalaDatabase/Output.csv")
  }

}
