package no.java.scalaweb

import _root_.java.io.{IOException, FileInputStream, File}
import _root_.java.util.Properties

object Configuration {
  val configFile = new File("/opt/jb/scalabin/etc/config.properties");
  val config = new Properties

  config.setProperty("planetScalaFeed", "http://www.planetscala.com/atom.xml")
  config.setProperty("htmlFileDir", "/Users/bjartek/scalabin/")

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

  val templateDir = System.getProperty("templateDir");
  if(templateDir != null) {
    val f = new File(templateDir) 
    if(f.isDirectory && f.canRead) {
      config.setProperty("htmlFileDir", templateDir);
    }
  }

  val planetScalaFeed = config.getProperty("planetScalaFeed")
  val htmlFileDir = config.getProperty("htmlFileDir")

}
