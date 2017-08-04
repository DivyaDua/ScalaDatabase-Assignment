package edu.knoldus

object MySqlConnect extends DatabaseConnect{

  override val driver = "com.mysql.jdbc.Driver"
    override val url = "jdbc:mysql://localhost/mysql"
    override val username: String = "root"
    override val password: String = "divyadua"

}
