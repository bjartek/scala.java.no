package bootstrap.liftweb

import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import _root_.net.liftweb.mapper.{DB, ConnectionManager, Schemifier, DefaultConnectionIdentifier, ConnectionIdentifier}
import _root_.java.sql.{Connection, DriverManager}
import _root_.no.java.scalaweb.model._

/**
  * A class that's instantiated early and run.  It allows the application
  * to modify lift's environment
  */
class Boot {
  def boot {
    if (!DB.jndiJdbcConnAvailable_?) DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)
    // where to search snippet
    LiftRules.addToPackages("no.java.scalaweb")
    Schemifier.schemify(true, Log.infoF _, User)

    LiftRules.addTemplateBefore(User.templates)

    // Build SiteMap
    val entries = Menu(Loc("Hjem", List("index"), "Hjem")) ::
    Menu(Loc("Prosjekter", List("projects"),"Prosjekter")) :: 
    Menu(Loc("Medlemmer", List("members"),"Medlemmer")) :: 
    Menu(Loc("Ressurser", List("resources"),"Ressurser")) :: Nil
    
    //User.sitemap
    LiftRules.setSiteMap(SiteMap(entries:_*))
    LiftRules.determineContentType = {
      case _ => "text/html"
    }


    S.addAround(User.requestLoans)
  }

}


object DBVendor extends ConnectionManager {
  def newConnection(name: ConnectionIdentifier): Can[Connection] = {
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver")
      val dm = DriverManager.getConnection("jdbc:derby:lift_example;create=true")
      Full(dm)
    } catch {
      case e : Exception => e.printStackTrace; Empty
    }
  }
  def releaseConnection(conn: Connection) {conn.close}
}

