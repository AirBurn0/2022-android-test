package dev.rodosteam.questtime

import androidx.test.core.app.launchActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.rodosteam.questtime.screen.common.MainActivity
import io.github.kakaocup.kakao.text.KButton

import org.junit.Test

class SimpleTest : TestCase() {

    @Test
    fun testSearchButton() = run {
        launchActivity<MainActivity>()
        step("Check") {
            val searchButton = KButton {
                withId(R.id.search_bar)
            }
            searchButton {
                isVisible()
                click()
            }
        }
    }
}