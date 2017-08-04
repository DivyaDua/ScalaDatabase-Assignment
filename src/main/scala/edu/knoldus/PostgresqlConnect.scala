package edu.knoldus


object PostgresqlConnect extends DatabaseConnect{

  override val driver = "org.postgresql.Driver"
  override val url = "jdbc:postgresql://localhost:5432/test_db"
  override val username: String = "postgres"
  override val password: String = "divyadua"

}
