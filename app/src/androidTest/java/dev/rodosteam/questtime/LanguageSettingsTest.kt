package dev.rodosteam.questtime

import androidx.test.core.app.launchActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.rodosteam.questtime.screen.common.MainActivity
import dev.rodosteam.questtime.utils.Languages
import io.github.kakaocup.kakao.bottomnav.KBottomNavigationView
import io.github.kakaocup.kakao.dialog.KAlertDialog
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Before

import org.junit.Test

class LanguageSettingsTest : TestCase() {

    @Before
    fun initDefaultLang() = run {
        launchActivity<MainActivity>()
        step("Open Settings panel") {
            val navBar = KBottomNavigationView {
                withId(R.id.nav_view)
            }
            navBar {
                setSelectedItem(R.id.navigation_settings)
            }
        }

        step("Click on Language Setting") {
            val searchButton = KButton {
                withId(R.id.fragment_settings_lang_container)
            }
            searchButton {
                isVisible()
                click()
            }
        }
        step("Select default language") {
            val dialog = KAlertDialog()
            dialog {
                onChoiceItem(Languages.DEFAULT.label) {
                    this.click()
                }
            }
        }
    }

    @Test
    fun testSearchButton() = run {
        step("Click on Language Setting") {
            val searchButton = KButton {
                withId(R.id.fragment_settings_lang_container)
            }
            searchButton {
                isVisible()
                click()
            }
        }
        step("Select other language") {
            val dialog = KAlertDialog()
            dialog {
                onChoiceItem(Languages.RUSSIAN.label) {
                    this.click()
                }
            }
        }
        step("Check changes") {
            val searchButton = KTextView {
                withId(R.id.fragment_settings_lang_container__value)
            }
            searchButton {
                isVisible()
                hasText(Languages.RUSSIAN.label)
            }
        }
    }
}