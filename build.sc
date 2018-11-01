// build.sc
import mill._, scalalib._

object wsdPkgMetrics extends ScalaModule {
  //def scalaVersion = "2.12.6"
  def scalaVersion = "0.10.0-RC1"

  def ivyDeps = Agg(
    ivy"org.xerial:sqlite-jdbc:3.25.2".withDottyCompat(scalaVersion()),
    ivy"com.lihaoyi::ammonite-ops:1.3.2".withDottyCompat(scalaVersion())
  )
}

