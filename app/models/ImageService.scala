package models

import javax.inject.Inject

import anorm.SqlParser.get
import anorm.{RowParser, ~}
import play.api.db.DBApi

case class Image(id: Option[Long] = None, link: String)

@javax.inject.Singleton
class ImageService @Inject()(dbapi: DBApi) {

  /**
    * Parse a Image from a ResultSet
    */
  val simple: RowParser[Image] = {
    get[Option[Long]]("image.id") ~
      get[String]("image.link") map {
      case id ~ link => Image(id, link)
    }
  }
}
