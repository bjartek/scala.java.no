package no.java.scalaweb.snippet

import _root_.java.net.{URLConnection, URL}
import scala.xml._ 

class OnpNews {

  def news = <span>Her vil det komme nyeter fra java.no</span>

  def newsFeed():Elem = {

    val url = new URL("http://www4.java.no/web/modules/javabin/rest/category-get.jsp?category=1122")
    val conn = url.openConnection
    val articles = XML.load(conn.getInputStream)
    articles
   }

  def show = {
    <div> {
    for(article <- articles) yield {
      <h3> { article.title} </h3>
      <p class="author"> { "Skrevet av: " + article.author } </p>
      <div class="newsBody">{ Unparsed(article.body) } </div>
    }
    }</div>
   }

  def articles = {
    for(article <- newsFeed \ "article") yield {
      val art = new Article();
      for(field <- article \ "field") {
        field.attribute("name").get.text match{
          case "tittel" => art.title = field.text
          case "brødtekst" => art.body = field.text
          case "forfatter" => art.author = field.text
          case _ => 
        }
      }
      art
     }
   }
 }

class Article {
  var author:String = _
  var title:String =_
  var body:String = _
}

