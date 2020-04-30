package tables

import java.sql.{ Blob, Clob, Date, Time, Timestamp }
import java.time.{ Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, ZonedDateTime }
import java.util.UUID

import slick.jdbc.JdbcProfile

class ColumnTypesTable(val profile: JdbcProfile) { // どうにかしてDIする
  import profile.api._

  class Schema(tag: Tag) extends Table[ColumnTypesTable.Record](tag, "column_types") {
    def int            = column[Int]("int", O.PrimaryKey, O.AutoInc)
    def long           = column[Long]("long")
    def float          = column[Float]("float")
    def double         = column[Double]("double")
    def bigDecimal     = column[BigDecimal]("big_decimal")
    def string         = column[String]("string")
    def char           = column[Char]("char")
    def boolean        = column[Boolean]("boolean")
    def byte           = column[Byte]("byte")
    def byteArray      = column[Array[Byte]]("byte_array")
    def blob           = column[Blob]("blob")
    def clob           = column[Clob]("clob")
    def uuid           = column[UUID]("uuid")
    def date           = column[Date]("date")
    def time           = column[Time]("time")
    def timestamp      = column[Timestamp]("timestamp")
    def localTime      = column[LocalTime]("local_time")
    def localDate      = column[LocalDate]("local_date")
    def localDateTime  = column[LocalDateTime]("local_date_time")
    def offsetDateTime = column[OffsetDateTime]("offset_date_time")
    def zonedDateTime  = column[ZonedDateTime]("zoned_date_time")
    def instant        = column[Instant]("instant")

    def * =
      (
        int,
        long,
        float,
        double,
        bigDecimal,
        string,
        char,
        boolean,
        byte,
        byteArray,
        blob,
        clob,
        uuid,
        date,
        time,
        timestamp,
        localTime,
        localDate,
        localDateTime,
        offsetDateTime,
        zonedDateTime,
        instant,
      ) <> (ColumnTypesTable.Record.tupled, ColumnTypesTable.Record.unapply)
  }

  val query = TableQuery[Schema]
}

object ColumnTypesTable {
  case class Record(
    int: Int,
    long: Long,
    float: Float,
    double: Double,
    bigDecimal: BigDecimal,
    string: String,
    char: Char,
    boolean: Boolean,
    byte: Byte,
    byteArray: Array[Byte],
    blob: Blob,
    clob: Clob,
    uuid: UUID,
    date: Date,
    time: Time,
    timestamp: Timestamp,
    localTime: LocalTime,
    localDate: LocalDate,
    localDateTime: LocalDateTime,
    offsetDateTime: OffsetDateTime,
    zonedDateTime: ZonedDateTime,
    instant: Instant,
  )
}
