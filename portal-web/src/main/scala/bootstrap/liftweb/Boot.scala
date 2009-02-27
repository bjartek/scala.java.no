package bootstrap.liftweb

import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import _root_.javax.servlet.http.{HttpServletRequest}

/**
* A class that's instantiated early and run.  It allows the application
* to modify lift's environment
*/
class Boot {
  def boot {

    LiftRules.addToPackages("no.java.scalaweb")

    val entries = Menu(Loc("Hjem", List("index"), "Hjem")) ::
    Menu(Loc("Prosjekter", List("projects"),"Prosjekter")) :: 
    Menu(Loc("Medlemmer", List("members"),"Medlemmer")) :: 
    Menu(Loc("Ressurser", List("resources"),"Ressurser")) :: Nil

    LiftRules.setSiteMap(SiteMap(entries:_*))
   
    LiftRules.determineContentType = {
      case _ => "text/html"
    }
  }
   
  private def makeUtf8(req: HttpServletRequest) {
    req.setCharacterEncoding("UTF-8")
  }

}

