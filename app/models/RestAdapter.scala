package models

import javax.inject.Singleton

case class ComputerResult(id: Option[Long], name: String, imageUrl: Option[String], company: Option[Company], description: Option[String])

/**
  * Adapter transforms data to Rest API Structures {@link models.RestAdapter.ComputerResult}
  * Created by andrey on 01/05/17.
  */
@Singleton
class RestAdapter {

  val loremIpsum: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore" +
    " et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea" +
    " commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla " +
    "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

  def map(item: ComputerItem): ComputerResult = {
    val url = item.image.map(_.link)
    ComputerResult(item.computer.id, item.computer.name, url, item.company, Option(loremIpsum))
  }

  def map(dbPage: Page[ComputerItem]): Page[ComputerResult] = {
    val restItems = dbPage.items.map { item => ComputerResult(item.computer.id, item.computer.name, None, item.company, None) }
    Page(restItems, dbPage.page, dbPage.offset, dbPage.total)
  }
}
