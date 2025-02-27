package app.grapheneos.setupwizard.action

import android.app.Activity
import android.app.KeyguardManager
import android.content.Intent
import android.util.Log
import app.grapheneos.setupwizard.appContext
import app.grapheneos.setupwizard.data.SecurityData

object SecurityActions {
    private const val TAG = "SecurityActions"
    private const val ACTION_SETUP_LOCK_SCREEN = "com.android.settings.SETUP_LOCK_SCREEN"
    private const val ACTION_SETUP_BIOMETRIC = "android.settings.BIOMETRIC_ENROLL"
    private const val REQUEST_CODE = 101

    init {
        refreshSecurityStatus()
    }

    fun launchSetup(context: Activity): Int {
        val intent = Intent(ACTION_SETUP_BIOMETRIC)
        SetupWizard.startActivityForResult(context, intent, REQUEST_CODE)
        return REQUEST_CODE
    }

    fun handleResult(context: Activity, resultCode: Int) {
        if (resultCode != Activity.RESULT_CANCELED) {
            SetupWizard.next(context)
        }
        refreshSecurityStatus()
    }

    fun refreshSecurityStatus() {
        SecurityData.isDeviceSecure.value = getKeyguardManager().isDeviceSecure
    }

    private fun getKeyguardManager(): KeyguardManager {
        return appContext.getSystemService(KeyguardManager::class.java)
    }
}
