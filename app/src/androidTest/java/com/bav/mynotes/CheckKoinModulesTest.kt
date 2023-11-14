package com.bav.mynotes

import com.bav.mynotes.di.appModule
import com.bav.mynotes.di.dataModule
import com.bav.mynotes.di.domainModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.verify.verify
import kotlin.test.Test

class CheckKoinModulesTest : KoinTest {

    private val allModules = module {
        includes(
            domainModule,
            dataModule,
            appModule,
        )
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        allModules.verify()
    }
}
