import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc" %% "ui-test-runner" % "0.48.0" % Test,
    "org.playframework" %% "play-json" % "3.0.6",
    "org.scalatestplus" %% "selenium-4-21" % "3.2.19.0" % "test"
  )

}
