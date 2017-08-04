package edu.knoldus

import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class CSVFilesTest extends FunSuite with MockitoSugar{

  test("testing read file method"){
    assert(CSVFiles.readFile("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDDL.csv") === List("1, Creating Employee table, \"DROP TABLE IF EXISTS Employee;\", \"CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\", \"DROP TABLE Employee;\"", "2, Altering Employee table by adding EmployeeAge column, \"CREATE TABLE IF NOT EXISTS Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\", \"ALTER TABLE Employee ADD COLUMN EmployeeAge INT;\", \"DROP TABLE Employee;\""))
  }

  test("testing parse CSV method"){
    assert(CSVFiles.parseCSV("1, Creating Employee table, \"DROP TABLE IF EXISTS Employee;\", \"CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);\", \"DROP TABLE Employee;\"") === List("1", " Creating Employee table", " DROP TABLE IF EXISTS Employee;", " CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);", " DROP TABLE Employee;"))
  }

  test("testing writeToFile method"){
    assert(CSVFiles.writeToFile(List(List("1",  "Creating Employee table",  "DROP TABLE IF EXISTS Employee;",  "CREATE TABLE Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);",  "DROP TABLE Employee;"), List("2",  "Altering Employee table by adding EmployeeAge column",  "CREATE TABLE IF NOT EXISTS Employee(name VARCHAR(20), EmployeeID INT, zipcode INT, PhoneNo BIGINT);",  "ALTER TABLE Employee ADD COLUMN EmployeeAge INT;",  "DROP TABLE Employee;")), List(318848097, 511625821), "MySQLDDLoutput.csv") === "/home/knoldus/IdeaProjects/ScalaDatabase/MySQLDDLoutput.csv")
  }

  test("testing write combined output file method"){
    assert(CSVFiles.writeCombinedOutputFile("/home/knoldus/IdeaProjects/ScalaDatabase/MySQLDDLoutput.csv", "/home/knoldus/IdeaProjects/ScalaDatabase/postDDLoutput.csv", "/home/knoldus/IdeaProjects/ScalaDatabase/SQLiteDDLoutput.csv", "Output.csv") === "/home/knoldus/IdeaProjects/ScalaDatabase/Output.csv")
  }

}
