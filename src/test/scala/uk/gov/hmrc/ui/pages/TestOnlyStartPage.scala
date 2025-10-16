/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.configuration.TestEnvironment

object TestOnlyStartPage extends BasePage {

  private val url: String = TestEnvironment.url("direct-debit-verify-email-frontend")

  def goTo(): Unit = {
    get(url + "/test-only/start")
    fluentWait.until(ExpectedConditions.urlContains(url))
  }

  protected val continueButton: By = By.className("govuk-button")
  val testEmail: String            = "dodgytestemail@test.com"
  val validEmail: String           = "hakangok@icloud.com"
  val emailWithoutAt: String       = "testemail2test.com"
  val verifiedEmail: String        = "hakangok@icloud.com"
  val storedEmail: String          = "hakangok@icloud.com"
  val verifiableEmail: String      = "hakangok@icloud.com"
  var passcode: String             = ""
  var statusCode: String           = ""

  def selectUserType(usertype: String): Unit =
    usertype match {
      case "Organisation" => asAnOrganisation()
      case "Individual"   => asAnIndividual()
    }

  def selectOrigin(origin: String): Unit =
    origin match {
      case "BTA"   => selectBTA()
      case "EPAYE" => selectEPAYE()
      case other   => throw new Exception(s"Unexpected option: $other")
    }

  def selectRegime(regime: String): Unit =
    regime match {
      case "zsdl" => selectZSDL()
      case "vatc" => selectVATC()
      case "cds"  => selectCDS()
      case "ppt"  => selectPPT()
      case "paye" => selectPAYE()
      case other  => throw new Exception(s"Unexpected option: $other")
    }

  def selectEmailBounced(bounced: String): Unit =
    bounced match {
      case "yes" => selectEmailBouncedYes()
      case "no"  => selectEmailBouncedNo()
      case other => throw new Exception(s"Unexpected option: $other")
    }

  def selectLanguage(language: String): Unit =
    language match {
      case "Welsh"   => CheckOrChangeEmailAddressPage.languageSelector()
      case "English" => CheckOrChangeEmailAddressPage.languageSelector()
      case other     => throw new Exception(s"Unexpected option: $other")
    }

  def asAnOrganisation(): Unit = {
    val asAnOrganisation: By = By.id("signInAs")
    selectCheckbox(asAnOrganisation)
  }

  def asAnIndividual(): Unit = {
    val asAnIndividual: By = By.id("signInAs-2")
    selectCheckbox(asAnIndividual)
  }

  def selectBTA(): Unit = {
    val bta: By = By.id("origin")
    selectCheckbox(bta)
  }

  def selectEPAYE(): Unit = {
    val epaye: By = By.id("origin-2")
    selectCheckbox(epaye)
  }

  def selectZSDL(): Unit = {
    val zsdl: By = By.id("taxRegime")
    selectCheckbox(zsdl)
  }

  def selectVATC(): Unit = {
    val vatc: By = By.id("taxRegime-2")
    selectCheckbox(vatc)
  }

  def selectCDS(): Unit = {
    val cds: By = By.id("taxRegime-3")
    selectCheckbox(cds)
  }

  def selectPPT(): Unit = {
    val ppt: By = By.id("taxRegime-4")
    selectCheckbox(ppt)
  }

  def selectPAYE(): Unit = {
    val paye: By = By.id("taxRegime-5")
    selectCheckbox(paye)
  }

  def enterEmailAddress(email: String): Unit = {
    val emailField: By = By.id("email")
    email match {
      case "testEmail"  => sendKeys(emailField, testEmail)
      case "stored"     => sendKeys(emailField, storedEmail)
      case "valid"      => sendKeys(emailField, validEmail)
      case "verified"   => sendKeys(emailField, verifiedEmail)
      case "verifiable" => sendKeys(emailField, verifiableEmail)
    }
  }

  def selectEmailBouncedYes(): Unit = {
    val yesOption: By = By.id("isEmailBounced")
    selectCheckbox(yesOption)
  }

  def selectEmailBouncedNo(): Unit = {
    val noOption: By = By.id("isEmailBounced-2")
    selectCheckbox(noOption)
  }

  def clickStartNow(): Unit = {
    val startNowButton: By = By.className("govuk-button")
    click(startNowButton)
  }

  def testOnlyBackPage(): Unit =
    fluentWait.until(ExpectedConditions.urlContains("/test-only/back"))

  val successful: String = "Pet requested"
}
