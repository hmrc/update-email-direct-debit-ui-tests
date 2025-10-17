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
import uk.gov.hmrc.ui.pages.TestOnlyStartPage.*

object CheckOrChangeEmailAddressPage extends BasePage {

  private val CheckOrChangeUrl: String =
    TestEnvironment.url("direct-debit-verify-email-frontend") + "/check-or-change-email-address"

  def goToCheckOrChangePage(): Unit = {
    get(CheckOrChangeUrl)
    fluentWait.until(ExpectedConditions.urlContains(CheckOrChangeUrl))
  }

  def emailAddressDisplayed(emailType: String): Unit = {
    val main: By = By.id("main-content")
    emailType match {
      case "testEmail" => getText(main).contains(testEmail)
      case "stored"    => getText(main).contains(storedEmail)
      case "valid"     => getText(main).contains(validEmail)
    }
  }

  def emailAddressDisplayedAsRadio(emailType: String): Unit = {
    val radio: By = By.xpath("//*[@id=\"main-content\"]/div/div/div[1]/form/div/fieldset/div/div[3]/label")
    emailType match {
      case "testEmail" => getText(radio).contains(testEmail)
      case "stored"    => getText(radio).contains(storedEmail)
      case "valid"     => getText(radio).contains(validEmail)
    }
  }
  //
  //  def differentEmailAddress(): Unit = {
  //    val different: By = By.xpath("//*[@id=\"main-content\"]/div/div/div[1]/form/div/fieldset/div/div[1]/label")
  //  }

  def selectDifferentEmailAddress(): Unit = {
    val different: By = By.xpath("//*[@id=\"main-content\"]/div/div/div[1]/form/div/fieldset/div/div[1]/label")
    click(different)
  }

  def selectStoredEmail(): Unit = {
    val storedEmail: By = By.xpath("//*[@id=\"main-content\"]/div/div/div[1]/form/div/fieldset/div/div[3]/label")
    click(storedEmail)
  }

  def enterEmailAddress(email: String): Unit = {
    val emailField: By = By.id("newEmailInput")
    email match {
      case "without@"   => sendKeys(emailField, emailWithoutAt)
      case "valid"      => sendKeys(emailField, validEmail)
      case "verified"   => sendKeys(emailField, verifiedEmail)
      case "verifiable" => sendKeys(emailField, verifiableEmail)
      case other        => sendKeys(emailField, email)
    }
  }

  def enterNumberEmailAddress(numberofEmails: Int): Unit = {
    pageLoaded()
    CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
    val emailField: By = By.id("newEmailInput")
    for (count <- 1 to numberofEmails) {
      sendKeys(emailField, "test" + count + "@email.com")
      clickContinue()
      if (count != numberofEmails)
        EnterCodeToConfirmPage.pageLoaded()
        EnterCodeToConfirmPage.clickBack()
    }

  }

  def clickContinue(): Unit = {
    val continue: By = By.id("continue")
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
      ExpectedConditions.titleIs(
        "Check or change your email address - Check or change your Direct Debit email address - GOV.UK"
      )
    )

  def errorPageLoaded(): Unit =
    fluentWait.until(
      ExpectedConditions.titleIs(
        "Error: Check or change your email address - Check or change your Direct Debit email address - GOV.UK"
      )
    )

  def clickSignOut(): Unit = {
    val signOut: By = By.className("hmrc-sign-out-nav")
    click(signOut)
  }

  def govPageLoaded(): Unit =
    fluentWait.until(ExpectedConditions.urlContains("https://www.gov.uk/"))

  def tooManySameEmails(times: Int): Unit = {
    pageLoaded()
    selectStoredEmail()
    for (_ <- 2 to times) {
      pageLoaded()
      clickContinue()
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.clickBack()
    }
    pageLoaded()
    clickContinue()
  }

  def tooManyDifferentEmails(times: Int): Unit = {
    pageLoaded()
    selectDifferentEmailAddress()
    for (count <- 1 to times) {
      pageLoaded()
      enterEmailAddress("test" + count + "@email.com")
      clickContinue()
      if (count != times)
        EnterCodeToConfirmPage.pageLoaded()
        EnterCodeToConfirmPage.clickBack()
    }
  }

}
