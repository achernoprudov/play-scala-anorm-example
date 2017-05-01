package controllers

import javax.inject.Inject

import models._
import play.api.i18n._
import play.api.mvc._

import net.liftweb.json._
import net.liftweb.json.Serialization.write


/**
  * Manage a database of computers
  */
class RestController @Inject()(computerService: ComputerService,
                               companyService: CompanyService,
                               restAdapter: RestAdapter,
                               val messagesApi: MessagesApi)
  extends Controller with I18nSupport {

  /**
    * Display the paginated list of computers.
    *
    * @param page    Current page number (starts from 0)
    * @param orderBy Column to be sorted
    * @param filter  Filter applied on computer names
    */
  def list(page: Int, orderBy: Int, filter: String) = Action { implicit request =>
    val filterQuery = "%" + filter + "%"
    val dbPage = computerService.list(page = page, orderBy = orderBy, filter = filterQuery)
    val restPage = restAdapter.map(dbPage)
    implicit val formats = DefaultFormats
    val json = write(restPage)
    Ok(json)
  }

  /**
    * Display the 'edit form' of a existing Computer.
    *
    * @param id Id of the computer to edit
    */
  def get(id: Long) = Action {
    computerService.findFullById(id)
      .map { item => restAdapter.map(item) }
      .map { computer =>
        implicit val formats = DefaultFormats
        val json = write(computer)
        Ok(json)
      }.getOrElse(NotFound)
  }

  def similar(id: Long) = Action {
    val items = computerService.similar(id)
      .map { item => restAdapter.toSimpleComputer(item) }

    implicit val formats = DefaultFormats
    val json = write(items)
    Ok(json)
  }
}
            
