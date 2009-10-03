package no.java.scalaweb.snippet

import _root_.java.net.{URLConnection, URL}
import scala.xml._

class ShowTemplate {

  def index = fetchContent("index.html")
  def project = fetchContent("projects.html")
  def member = fetchContent("members.html") 
  def resource = fetchContent("resources.html") 

  private def fetchContent(file:String) = Unparsed(scala.io.Source.fromFile(Configuration.htmlFileDir + file, "utf-8").mkString)
  
}
