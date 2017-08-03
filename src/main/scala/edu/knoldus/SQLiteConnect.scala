package edu.knoldus

import java.sql.{Connection, DriverManager, SQLException}

object SQLiteConnect extends DatabaseConnect{

  val driver = "org.sqlite.JDBC"
  val url = "jdbc:sqlite:/home/knoldus/test.db"

  override def connectToDatabase: Connection = {
    try {
      // make the connection
      Class.forName(driver)
      val connection: Connection = DriverManager.getConnection(url)
      connection
    }
    catch{
      case e: ClassNotFoundException => throw new ClassNotFoundException
      case e: SQLException => throw new SQLException
    }
  }

}
