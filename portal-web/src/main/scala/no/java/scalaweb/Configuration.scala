package no.java.scalaweb

import _root_.java.io.{IOException, FileInputStream, File}
import _root_.java.util.Properties

object Configuration {
  val configFile = new File("/opt/jb/scalabin/etc/config.properties");
  val config = new Properties

  config.setProperty("javaBinNews", "http://www4.java.no/web/modules/javabin/rest/category-get.jsp?category=1122")
  config.setProperty("planetScalaFeed", "http://www.planetscala.com/atom.xml")
  if (configFile.canRead) {
    var is: FileInputStream = null

    try {
      is = new FileInputStream(configFile)
      config.load(is)
    }
    finally {
      if (is != null) {
        is.close();
      }
    }
  }

  var javaBinNews = config.getProperty("javaBinNews")
  var planetScalaFeed = config.getProperty("planetScalaFeed")

  println("javaBinNews: " + javaBinNews)
  println("planetScalaFeed: " + planetScalaFeed)
}
