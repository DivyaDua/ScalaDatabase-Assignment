name := "ScalaDatabase"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.24"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.19.3"

libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1102-jdbc41"

libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5" % "test"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

coverageEnabled:=true
        