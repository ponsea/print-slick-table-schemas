package tables

import slick.jdbc.JdbcProfile

class TableConstraintsTable(val profile: JdbcProfile, val columnTypesTable: ColumnTypesTable) { // どうにかしてDIする
  import profile.api._

  class Schema(tag: Tag) extends Table[TableConstraintsTable.Record](tag, "table_constraints") {
    def unique           = column[Long]("unique", O.Unique)
    def option           = column[Option[Long]]("option")
    def default          = column[Long]("default", O.Default(0L))
    def varyingLength    = column[String]("varyingLength", O.Length(16, varying = true))
    def notVaryingLength = column[String]("notVaryingLength", O.Length(16, varying = false))

    // composite primary key
    def primaryKey1 = column[Long]("primary_key_1")
    def primaryKey2 = column[Long]("primary_key_2")

    def foreignKey1 = column[Long]("foreign_key_1")
    def foreignKey2 = column[Long]("foreign_key_2")
    def foreignKey3 = column[Long]("foreign_key_3")

    def pk = primaryKey("pk", (primaryKey1, primaryKey2))

    def fk1 = foreignKey("fk1", foreignKey1, columnTypesTable.query)(
      (_: columnTypesTable.Schema).long,
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.Restrict
    )

    def fk2 = foreignKey("fk2", foreignKey2, columnTypesTable.query)(
      (_: columnTypesTable.Schema).long,
      onUpdate = ForeignKeyAction.SetDefault,
      onDelete = ForeignKeyAction.SetNull
    )

    def fk3 = foreignKey("fk3", foreignKey2, columnTypesTable.query)(
      (_: columnTypesTable.Schema).long,
      onUpdate = ForeignKeyAction.Cascade,
      onDelete = ForeignKeyAction.Cascade
    )

    def idx       = index("idx", foreignKey1)
    def idxUnique = index("idx_unique", (foreignKey2, foreignKey3), unique = true)

    def * =
      (
        unique,
        option,
        default,
        varyingLength,
        notVaryingLength,
        primaryKey1,
        primaryKey2,
        foreignKey1,
        foreignKey2,
        foreignKey3
      ) <> (TableConstraintsTable.Record.tupled, TableConstraintsTable.Record.unapply)
  }

  val query = TableQuery[Schema]
}

object TableConstraintsTable {
  case class Record(
    unique: Long,
    option: Option[Long],
    default: Long,
    varyingLength: String,
    notVaryingLength: String,
    primaryKey1: Long,
    primaryKey2: Long,
    foreignKey1: Long,
    foreignKey2: Long,
    foreignKey3: Long
  )
}
