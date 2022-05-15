package dev.rodosteam.questtime

import android.view.View
import androidx.test.core.app.launchActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.rodosteam.questtime.screen.common.MainActivity
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString
import org.junit.Test

class SearchTest : TestCase() {

    companion object {
        const val QUERY = "test"
    }

    @Test
    fun testSearchButton() = run {
        launchActivity<MainActivity>()
        step("Press Search button") {
            val searchButton = KButton {
                withId(R.id.search_bar)
            }
            searchButton {
                isVisible()
                click()

            }
        }
        step("Enter search query") {
            val searchBar = KTextInputLayout {
                withId(R.id.search_bar)
                withClassName(containsString("SearchView"))
            }
            searchBar {
                isVisible()
                click()
                edit.typeText(QUERY)
            }
        }
        step("Click on first matching result") {
            val recycler = KRecyclerView({
                withId(R.id.library_recycler_view)
            }) {
                itemType(::Item)
            }
            recycler {
                childAt<KRecyclerItem<Item>>(0) {
                    this.click()
                }
            }
        }
        step("Check for query in title of item preview") {
            val title = KTextView {
                withId(R.id.fragment_preview__title)
            }
            title {
                hasText(containsString(QUERY))
            }
        }
    }

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) // future additions can be made
}