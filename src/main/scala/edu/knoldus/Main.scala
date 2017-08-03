package edu.knoldus

object Main extends App{

  val query: List[List[String]] = CSVFiles.readAndParse("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDDL.csv")
  val connection = MySqlConnect.connectToDatabase
  val time: List[Long] = query.map(MySqlConnect.executeQuery(connection, _))
  MySqlConnect.closeConnection(connection)
  CSVFiles.writeToFile(query, time, "MySQLDDLoutput.csv")

  val query1 = CSVFiles.readAndParse("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDML.csv")
  val connection1 = MySqlConnect.connectToDatabase
  val time1: List[Long] = query1.map(MySqlConnect.executeQuery(connection1, _))
  MySqlConnect.closeConnection(connection1)
  CSVFiles.writeToFile(query1, time1, "MySQLDMLoutput.csv")

  val query2 = CSVFiles.readAndParse("/home/knoldus/IdeaProjects/ScalaDatabase/postDDL.csv")
  val connection2 = PostgresqlConnect.connectToDatabase
  val time2: List[Long] = query2.map(PostgresqlConnect.executeQuery(connection2, _))
  PostgresqlConnect.closeConnection(connection2)
  CSVFiles.writeToFile(query2, time2, "postDDLoutput.csv")

  val query3 = CSVFiles.readAndParse("/home/knoldus/IdeaProjects/ScalaDatabase/SQLiteDDL.csv")
  val connection3 = SQLiteConnect.connectToDatabase
  val time3: List[Long] = query3.map(SQLiteConnect.executeQuery(connection3, _))
  SQLiteConnect.closeConnection(connection2)
  CSVFiles.writeToFile(query3, time3, "SQLiteDDLoutput.csv")

  CSVFiles.writeCombinedFile("/home/knoldus/IdeaProjects/ScalaDatabase/MySQLDDLoutput.csv", "/home/knoldus/IdeaProjects/ScalaDatabase/postDDLoutput.csv", "/home/knoldus/IdeaProjects/ScalaDatabase/SQLiteDDLoutput.csv", "Output.csv")

}
