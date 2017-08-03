package edu.knoldus

object PostgresqlConnect extends DatabaseConnect{

  val driver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://localhost:5432/test_db"
  override val username: String = "postgres"
  override val password: String = "divyadua"

}
