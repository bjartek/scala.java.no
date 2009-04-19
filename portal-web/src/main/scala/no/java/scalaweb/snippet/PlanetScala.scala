package no.java.scalaweb.snippet

import _root_.java.net.{URLConnection, URL}
import scala.xml._

class PlanetScala {
  def feed(): Elem = {

    val url = new URL(Configuration.planetScalaFeed)
    val conn = url.openConnection
    val atom = XML.load(conn.getInputStream)
    atom
  }

  def show() =
    for (entry <- feed \ "entry";
         title <- entry \ "title";
         if title.text != "";
         link <- entry \ "link") yield
      <li> <a href={link.attribute("href").get.text}>{title.text}</a> </li>

  def list(): Elem = {
    <ul>{show.slice(0, 10)}</ul>
  }
}
