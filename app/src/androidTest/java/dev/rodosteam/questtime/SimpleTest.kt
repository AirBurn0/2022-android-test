package dev.rodosteam.questtime

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.rodosteam.questtime.screen.common.MainActivity
import dev.rodosteam.questtime.utils.MyTestWatcher
import io.github.kakaocup.kakao.text.KButton
import io.qameta.allure.android.allureScreenshot
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.*
import io.qameta.allure.kotlin.junit4.DisplayName
import io.qameta.allure.kotlin.junit4.Tag
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AllureAndroidJUnit4::class)
@Epic("Samples")
@DisplayName("MainActivity tests")
@Tag("Instrumentation")
class SimpleTest : TestCase() {

    // rule & way to screenshot
    @get:Rule
    var watcher = MyTestWatcher()

    @Before
    fun start() {
        ActivityScenario.launch(MainActivity::class.java)
        // launchActivity<MainActivity>()
    }

    @Test
    @Feature("Success")
    fun testSearchButton() = run {
        Allure.step("Check") {
            // way to screenshot
            allureScreenshot("screenshot")
            val searchButton = KButton {
                withId(R.id.search_bar)
            }
            searchButton {
                isVisible()
                click()
            }
        }
    }

    @Test
    @Feature("Failures")
    @Story("Initial state")
    @DisplayName("should initial state fail")
    @Description("Verification of initial state that should fail on purpose (just so it is shown as a broken test by Allure)")
    fun testSearchButtonMissing() = run {
        step("Check") {
            val searchButton = KButton {
                withId(R.id.search_bar)
            }
            searchButton {
                // must fail
                isNotClickable()
            }
        }
    }
}