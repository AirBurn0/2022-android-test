package dev.rodosteam.questtime.utils

import android.graphics.Bitmap
import androidx.test.platform.app.InstrumentationRegistry
import io.qameta.allure.kotlin.Allure
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.ByteArrayOutputStream

class MyTestWatcher : TestWatcher() {

    override fun failed(e: Throwable?, description: Description?) {
        ByteArrayOutputStream().use {
            InstrumentationRegistry.getInstrumentation()
                .uiAutomation.takeScreenshot()
                .compress(Bitmap.CompressFormat.PNG, 80, it)
            Allure.lifecycle.addAttachment(
                "screenshot",
                it.toByteArray(),
                "image/png",
                "png"
            )
            super.failed(e, description)
        }
    }


}