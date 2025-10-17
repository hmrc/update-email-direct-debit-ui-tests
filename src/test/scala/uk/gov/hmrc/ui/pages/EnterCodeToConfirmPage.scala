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
import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.pages.TestOnlyStartPage.*

object EnterCodeToConfirmPage extends BasePage {

  def passCodeBox(code: String): Unit = {
    val passcode: By = By.id("passcode")
    sendKeys(passcode, code)
  }

  def tooManyPasscodes(): Unit =
    (0 to 5).foreach { _ =>
      emailVerificationPageLoaded()
      val passcode: By = By.id("passcode")
      sendKeys(passcode, "DNCLRK")
      clickContinue()
      emailVerificationPageLoaded()
    }

  def clickContinue(): Unit = {
    val continue: By = By.cssSelector("button.govuk-button")
    click(continue)
  }

  def clickBack(): Unit = {
    val back: By = By.className("govuk-back-link")
    click(back)
  }

  def languageSelector(): Unit = {
    val languageSelector: By = By.cssSelector("li.hmrc-language-select__list-item > a")
    click(languageSelector)
  }

  def pageLoaded(): Unit =
    fluentWait.until(
      ExpectedConditions.titleIs("Enter code to confirm your email address - Email Verification - GOV.UK")
    )

  private val passCodeurl: String = TestEnvironment.url("direct-debit-verify-email-frontend")

  def goToPasscode(): Unit = {
    get(passCodeurl + "/test-only/email-verification-passcodes")
    fluentWait.until(ExpectedConditions.urlContains(passCodeurl))
    val page: By      = By.tagName("html")
    val body          = getText(page)
    val json: JsValue = Json.parse(body)
    passcode = (json \ "passcodes" \ 0 \ "passcode").as[String]
    Driver.instance.navigate().back()
    fluentWait.until(
      ExpectedConditions.titleIs("Enter code to confirm your email address - Email Verification - GOV.UK")
    )
  }

  def emailVerificationPageLoaded(): Unit =
    fluentWait.until(ExpectedConditions.urlContains("/email-verification"))

  def emailVerifiedPageLoaded(): Unit =
    fluentWait.until(ExpectedConditions.urlContains("/email-address-verified"))

  def tooManyEmailsPageLoaded(): Unit =
    fluentWait.until(ExpectedConditions.urlContains("/tried-to-verify-too-many-email-addresses"))

  def tooManySameEmailsPageLoaded(): Unit =
    fluentWait.until(ExpectedConditions.urlContains("/tried-to-verify-email-address-too-many-times"))

  def tooManyPasscodesPageLoaded(): Unit =
    fluentWait.until(ExpectedConditions.urlContains("/email-verification-code-entered-too-many-times"))
}
