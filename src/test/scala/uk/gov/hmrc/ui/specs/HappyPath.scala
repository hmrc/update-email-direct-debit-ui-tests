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

class HappyPath extends BaseSpec {

  Feature("Happy Path") {

    Scenario("Organisation - BTA - User is directed to Check or change you email address page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user is on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.emailAddressDisplayed("testEmail")

      Then("The email address is displayed in the radio buttons text")
      CheckOrChangeEmailAddressPage.emailAddressDisplayedAsRadio("testEmail")
    }

    Scenario("Organisation - EPAYE - User is directed to Check or change you email address page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user is on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.emailAddressDisplayed("testEmail")

      Then("The email address is displayed in the radio buttons text")
      CheckOrChangeEmailAddressPage.emailAddressDisplayedAsRadio("testEmail")
    }

    Scenario("Individual - BTA - User is directed to Check or change you email address page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Individual")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user is on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.emailAddressDisplayed("testEmail")

      Then("The email address is displayed in the radio buttons text")
      CheckOrChangeEmailAddressPage.emailAddressDisplayedAsRadio("testEmail")
    }

    Scenario("Individual - EPAYE - User is directed to Check or change you email address page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Individual")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user is on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.emailAddressDisplayed("testEmail")

      Then("The email address is displayed in the radio buttons text")
      CheckOrChangeEmailAddressPage.emailAddressDisplayedAsRadio("testEmail")
    }

    Scenario("Back link on 'Check or change your email address' page", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("testEmail")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user is on the Check or Change Email Address Page and clicks back link")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.clickBack()

      Then("The user should be on the Test Only Back Page")
      TestOnlyStartPage.testOnlyBackPage()
    }

    Scenario("Organisation - BTA - User selects different email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same valid email address")
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Organisation - EPAYE - User selects different email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same valid email address")
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Individual - BTA - User selects different email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Individual")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same valid email address")
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Individual - EPAYE - User selects different email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Individual")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same valid email address")
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterEmailAddress("valid")
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Organisation - BTA - User selects Stored email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same stored email address")
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Organisation - EPAYE - User selects Stored email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same stored email address")
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Individual - BTA - User selects Stored email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Individual")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same stored email address")
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Individual - EPAYE - User selects Stored email that is already confirmed", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Individual")
      TestOnlyStartPage.selectOrigin("EPAYE")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("valid")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same stored email address")
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }

    Scenario("Too many different email addresses - already verified", allTests) {

      Given("user is on Test only starter page")
      TestOnlyStartPage.goTo()

      When("user signs in and starts a journey")
      TestOnlyStartPage.selectUserType("Organisation")
      TestOnlyStartPage.selectOrigin("BTA")
      TestOnlyStartPage.selectRegime("paye")
      TestOnlyStartPage.enterEmailAddress("verified")
      TestOnlyStartPage.selectEmailBouncedYes()
      TestOnlyStartPage.clickStartNow()

      When("The user enters a different valid email address on the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.pageLoaded()
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is on the code to confirm email page and enters the passcode")
      EnterCodeToConfirmPage.pageLoaded()
      EnterCodeToConfirmPage.goToPasscode()
      EnterCodeToConfirmPage.passCodeBox(passcode)
      EnterCodeToConfirmPage.clickContinue()
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits 9 other email address and then tries the stored email address")
      CheckOrChangeEmailAddressPage.selectDifferentEmailAddress()
      CheckOrChangeEmailAddressPage.enterNumberEmailAddress(9)
      EnterCodeToConfirmPage.tooManyEmailsPageLoaded()

      When("The user goes back to the Check or Change Email Address Page")
      CheckOrChangeEmailAddressPage.goToCheckOrChangePage()

      And("Submits the same stored email address")
      CheckOrChangeEmailAddressPage.selectStoredEmail()
      CheckOrChangeEmailAddressPage.clickContinue()

      Then("The user is presented with the Email address verified page")
      EnterCodeToConfirmPage.emailVerifiedPageLoaded()
    }
  }
}
