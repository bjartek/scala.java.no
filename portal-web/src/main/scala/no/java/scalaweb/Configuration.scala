package no.java.scalaweb

import _root_.java.io.{IOException, FileInputStream, File}
import _root_.java.util.Properties

object Configuration {
  val configFile = new File("/opt/jb/scalabin/etc/config.properties");
  val config = new Properties

  config.setProperty("javaBinCategoryGet", "http://www4.java.no/web/modules/javabin/rest/category-get.jsp")
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

  var javaBinCategoryGet = config.getProperty("javaBinCategoryGet")
  var planetScalaFeed = config.getProperty("planetScalaFeed")

  println("javaBinCategoryGet: " + javaBinCategoryGet)
  println("planetScalaFeed: " + planetScalaFeed)
}
