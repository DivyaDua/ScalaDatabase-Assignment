package edu.knoldus

import java.sql.{Connection, DriverManager, SQLException, Statement}

trait DatabaseConnect {

  val driver: String
  val url: String
  val username: String = ""
  val password: String = ""

  def connectToDatabase: Connection = {
    try {
      // make the connection
      Class.forName(driver)
      val connection: Connection = DriverManager.getConnection(url, username, password)
      connection
    }
    catch{
      case e: ClassNotFoundException => throw new ClassNotFoundException
      case e: SQLException => throw new SQLException
    }
  }

 def executeQuery(connection: Connection, queryList: List[String]): Long = {

    val statement: Statement = connection.createStatement()

    statement.execute(queryList(2))
    val start = System.nanoTime()
    statement.execute(queryList(3))
    val end = System.nanoTime()
    statement.execute(queryList(4))
    statement.close

    end - start

    /*val resultSet = statement.executeQuery("SELECT host, user FROM user")
    while ( resultSet.next() ) {
      val host = resultSet.getString("host")
      val user = resultSet.getString("user")
      println("host, user = " + host + ", " + user)
    }*/
  }

  def closeConnection(connection: Connection): Unit = {
    connection.close()
  }

}
