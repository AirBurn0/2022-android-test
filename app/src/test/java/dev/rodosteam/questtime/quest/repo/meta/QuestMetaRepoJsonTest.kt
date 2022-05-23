package dev.rodosteam.questtime.quest.repo.meta

import dev.rodosteam.questtime.quest.model.QuestMeta
import dev.rodosteam.questtime.utils.InternalStorage
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.*
import io.qameta.allure.junit4.DisplayName
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class QuestMetaRepoJsonTest {
    companion object {
        private const val TEST_FILES_DIR = "../test_files"
        private const val TEST_INCORRECT_FILES_DIR = "../incorrect_test_files"
        private val TEST_META = QuestMeta(
            id = -1,
            title = "Test quest",
            description = "Test quest description",
            author = "Lev Saskov",
            downloads = 0,
            favorites = 0,
            created = 0L,
            iconUrl = "quests_library/images/test_icon.png",
            jsonContent = "quests_library/test_quest.json"
        )
    }

    @Test
    @Owner("Saskov Lev")
    @DisplayName("ABCDEF")
    @Description("Indicates whether json metadata parsing correct or not")
    fun reading_isCorrect() {
        var questMetaRepo : QuestMetaRepoJson? = null
        var meta : QuestMeta? = null
        Allure.step("Find files in storage") {
            questMetaRepo = QuestMetaRepoJson(InternalStorage(File(TEST_FILES_DIR)))
            assertNotNull(questMetaRepo)
        }
        Allure.step("Find same meta in repository") {
            meta = questMetaRepo?.findById(TEST_META.id)
            assertNotNull(meta)
        }
        Allure.step("Check equality") {
            assertEquals(TEST_META, meta)
        }
    }

    @Test
    @Owner("Baidiya Rosan")
    @Feature("Json Metadata")
    @Stories(Story("Reading is incorrect!"))
    @DisplayName("Json incorrect metadata parsing")
    @Description("Indicates whether json metadata parsing correct or not, even it's incorrect")
    fun reading_isIncorrect() {
        var questMetaRepo : QuestMetaRepoJson? = null
        var meta : QuestMeta? = null
        Allure.step("Find files in storage") {
            questMetaRepo = QuestMetaRepoJson(InternalStorage(File(TEST_INCORRECT_FILES_DIR)))
            assertNotNull(questMetaRepo)
        }
        Allure.step("Find same meta in repository") {
            meta = questMetaRepo?.findById(TEST_META.id)
            assertNotNull(meta)
        }
        Allure.step("Check equality") {
            assertEquals(TEST_META, meta)
        }
    }
}