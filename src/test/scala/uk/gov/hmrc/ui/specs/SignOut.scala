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

import uk.gov.hmrc.ui.pages.{CheckOrChangeEmailAddressPage, TestOnlyStartPage}
import uk.gov.hmrc.ui.specs.tags.*

class SignOut extends BaseSpec {

  Feature("Sign Out") {

    Scenario(" User is directed to gov.uk after clicking signOut button", allTests) {

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

      And("And Clicks Sign Out")
      CheckOrChangeEmailAddressPage.clickSignOut()

      Then("The User is on the gov.uk Page")
      CheckOrChangeEmailAddressPage.govPageLoaded()
    }
  }
}
