package edu.knoldus

import java.sql.{Connection, DriverManager, SQLException, Statement}
import org.apache.log4j.Logger

trait DatabaseConnect extends CSVFiles{

  override val logger: Logger = Logger.getLogger(this.getClass)

  val driver: String = ""
  val url: String = ""
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

 def executeQuery(queryList: List[String]): Long = {

    val connection: Connection = connectToDatabase
    val statement: Statement = connection.createStatement()

    statement.execute(queryList(2))
    val start = System.nanoTime()
    statement.execute(queryList(3))
    val end = System.nanoTime()
    statement.execute(queryList(4))
    statement.close

    closeConnection(connection)
    logger.info(s"Execution of queries completed with time : ${end-start}")
    end - start

  }

  def closeConnection(connection: Connection): Unit = {
    connection.close()
  }

  def executeQueries(filePath: String, outputFileName: String): Unit ={
    val query: List[List[String]] = readAndParse(filePath)
    val time: List[Long] = query.map(executeQuery)
    writeToFile(query, time, outputFileName)
  }

}
