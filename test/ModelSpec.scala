import models.ComputerService
import org.scalatestplus.play._

class ModelSpec extends PlaySpec with OneAppPerSuite {

  var computerService: ComputerService = app.injector.instanceOf(classOf[ComputerService])

  import models._

  // -- Date helpers
  
  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  }
  
  // --
  
  "Computer model" should {
    
    "be retrieved by id" in {
        val macintosh = computerService.findFullById(21).get
      
        macintosh.computer.name must equal("Macintosh")
        macintosh.computer.introduced.value must matchPattern {
          case date:java.util.Date if dateIs(date, "1984-01-24") =>
        }
    }
    
    "be listed along its companies" in {

        val computers = computerService.list()

        computers.total must equal(574)
        computers.items must have length(10)
    }
    
    "be updated if needed" in {

        computerService.update(21, Computer(name="The Macintosh",
          introduced=None,
          discontinued=None,
          companyId=Some(1)))
        
        val macintosh = computerService.findFullById(21).get
        
        macintosh.computer.name must equal("The Macintosh")
        macintosh.computer.introduced mustBe None
    }
    
  }
  
}