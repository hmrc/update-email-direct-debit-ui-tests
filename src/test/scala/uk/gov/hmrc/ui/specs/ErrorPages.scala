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

package uk.gov.hmrc.ui.specs

import uk.gov.hmrc.ui.pages.TestOnlyStartPage.passcode
import uk.gov.hmrc.ui.pages.{CheckOrChangeEmailAddressPage, EnterCodeToConfirmPage, TestOnlyStartPage}
import uk.gov.hmrc.ui.specs.tags.*

class ErrorPages extends BaseSpec {

  Feature("Error Pages") {

    Scenario("User does not provide an email address", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user is on the Check or Change Email Address Page and does not select an email")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The the user is presented with Select an email address error message")
      CheckOrChangeEmailAddressPage.noEmailSelectedError()
    }

    Scenario("Too many incorrect passcode error page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("stored")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user selects stored email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      When("The user enters incorrect passcode too many times")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.tooManyPasscodes()

      Then("Too many passcode attempts page shows")
      EnterCodeToConfirmPage.tooManyPasscodesPageLoaded()
    }

    Scenario("Too many verify same email address error page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Individual")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user tries to verify the same email address on the Check or Change Email Address Page 6 times")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.tooManySameEmails(6)

      Then("Too many email verify attempts page shows")
      EnterCodeToConfirmPage.tooManySameEmailsPageLoaded()
    }

    Scenario("Too many different email addresses error page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user tries to verify a different email address on the Check or Change Email Address Page 10 times")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.tooManyDifferentEmails(10)

      Then("Too many different email verify attempts page shows")
      EnterCodeToConfirmPage.tooManyEmailsPageLoaded()
    }



  }
}