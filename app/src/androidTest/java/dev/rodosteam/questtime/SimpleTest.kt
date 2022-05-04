package dev.rodosteam.questtime

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SimpleTest : TestCase() {

    @Test
    fun test() = run {
        step("Check") {

        }
    }
}