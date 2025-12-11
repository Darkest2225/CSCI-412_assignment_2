package com.example.greetingscard

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import androidx.test.uiautomator.UiDevice
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleUiAutomatorTest {

    private lateinit var device: UiDevice
    private val PACKAGE_NAME = "com.example.greetingscard"

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        // Wait until the home screen is fully loaded
        val launcherPackage = device.launcherPackageName
        assertNotNull("Launcher package not found!", launcherPackage)
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 5000)
    }

    @Test
    fun assignment5Test() {

        val launcherPackage = device.launcherPackageName


        var appLaunch = device.findObject(By.text("Greetings Card"))
        if (appLaunch == null) {
            appLaunch = device.findObject(By.desc("Greetings Card"))
        }
        if (appLaunch == null) {
            appLaunch = device.findObject(By.textContains("Greetings"))
        }
        if (appLaunch == null) {
            appLaunch = device.findObject(By.descContains("Greetings"))
        }

        assertNotNull("Couldn't find app", appLaunch)
        appLaunch!!.click()


        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), 5000)

        val activity2Button = device.findObject(By.textContains("Start Activity Explicitly"))
        assertNotNull("Button not found", activity2Button)
        activity2Button.click()

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), 5000)

        val exampleChallenge = device.findObject(By.textContains("Rapid Changes"))
        assertNotNull("could not find rapid changes challenge", exampleChallenge)
    }
}
