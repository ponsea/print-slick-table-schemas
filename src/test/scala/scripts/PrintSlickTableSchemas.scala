package scripts

// ※ ScalaTest 3.1.0からFlatSpec等非推奨でこっちに移るらしい
import org.scalatest.flatspec.AnyFlatSpec
import slick.jdbc.{DB2Profile, DerbyProfile, H2Profile, HsqldbProfile, MySQLProfile, OracleProfile, PostgresProfile, SQLServerProfile, SQLiteProfile}
import org.scalatest.DoNotDiscover
import tables.{ColumnTypesTable, TableConstraintsTable}

// 以下は`sbt testOnly scripts.PrintSlickTableSchemas`で実行できる
// 使わない時は`@DoNotDiscover`アノテーションをつけると他のテストの邪魔にならないようできる
// 簡易なスクリプトをまとめるsbtのsub-moduleを作ってそこに置いても良さそう
class PrintSlickTableSchemas extends AnyFlatSpec {
  val jdbcProfileMap = Map(
    "DB2"       -> DB2Profile,
    "Derby"     -> DerbyProfile,
    "H2"        -> H2Profile,
    "Hsqldb"    -> HsqldbProfile,
    "MySQL"     -> MySQLProfile,
    "Oracle"    -> OracleProfile,
    "Postgres"  -> PostgresProfile,
    "SQLite"    -> SQLiteProfile,
    "SQLServer" -> SQLServerProfile
  )

  it should "print slick table schemas" in {
    val dbName      = "MySQL"
    val jdbcProfile = jdbcProfileMap(dbName)

    import jdbcProfile.api._

    val columnTypesTable      = new ColumnTypesTable(jdbcProfile)
    val tableConstraintsTable = new TableConstraintsTable(jdbcProfile, columnTypesTable)

    println(s"# Create table `column_types` in $dbName --------------------------------------")
    columnTypesTable.query.schema.createStatements.foreach(println)
    println(s"# Create table `table_constraints` in $dbName --------------------------------------")
    tableConstraintsTable.query.schema.createStatements.foreach(println)
  }
}
