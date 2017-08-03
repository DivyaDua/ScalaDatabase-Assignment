package edu.knoldus

object MySqlConnect extends DatabaseConnect{

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost/mysql"
    override val username: String = "root"
    override val password: String = "divyadua"

}
