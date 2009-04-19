package no.java.scalaweb.snippet

import _root_.java.net.{URLConnection, URL}
import scala.xml._

class OnpNews {
  def news = <span>Her vil det komme nyeter fra java.no</span>

  def newsFeed(): Elem = {
    XML.load(Configuration.javaBinCategoryGet + "?category=1122")
  }

  def show = {
    <div>{for (article <- articles) yield {
      <div class="article">
      <h3>{article.title}</h3>
      <p class="author">{"Skrevet av: " + article.author}</p>{article.getSource()}{Unparsed(article.body)}</div>
    }}</div>
  }

  def articles = {
    for (article <- newsFeed \ "article") yield {
      val art = new Article();
      for (field <- article \ "field") {
        field.attribute("name").get.text match {
          case "tittel" => art.title = field.text
          case "brødtekst" => art.body = field.text
          case "forfatter" => art.author = field.text
          case "url" => art.url = field.text
          case "ingress" => art.shortdesc = field.text
          case "kilde" => art.source = field.text
          case _ =>
        }
      }
      art
    }
  }
}

class Article {
  var author: String = _
  var title: String = _
  var body: String = _
  var url: String = ""
  var shortdesc: String = _
  var source: String = _

  def getSource() = {
    if (url == "") {
      <p/>
    } else {
      <p>{"Kilde: "}<a href={url}>{source}</a> </p>
    }
  }
}
