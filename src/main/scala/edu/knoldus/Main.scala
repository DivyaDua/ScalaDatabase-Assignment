package edu.knoldus

object Main extends App with CSVFiles{

  MySqlConnect.executeQueries("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDDL.csv","MySQLDDLoutput.csv" )
  MySqlConnect.executeQueries("/home/knoldus/IdeaProjects/ScalaDatabase/MySqlDML.csv", "MySQLDMLoutput.csv")

  PostgresqlConnect.executeQueries("/home/knoldus/IdeaProjects/ScalaDatabase/postDDL.csv", "postDDLoutput.csv")

  SQLiteConnect.executeQueries("/home/knoldus/IdeaProjects/ScalaDatabase/SQLiteDDL.csv", "SQLiteDDLoutput.csv")

  writeCombinedOutputFile("/home/knoldus/IdeaProjects/ScalaDatabase/MySQLDDLoutput.csv",
    "/home/knoldus/IdeaProjects/ScalaDatabase/postDDLoutput.csv",
    "/home/knoldus/IdeaProjects/ScalaDatabase/SQLiteDDLoutput.csv", "Output.csv")

}
