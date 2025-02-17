package app.grapheneos.setupwizard.view.activity

import android.content.Intent
import android.util.Log
import android.view.View
import app.grapheneos.setupwizard.R
import app.grapheneos.setupwizard.action.MigrationActions
import app.grapheneos.setupwizard.action.SecurityActions
import app.grapheneos.setupwizard.action.SetupWizard

class MigrationActivity : SetupWizardActivity(
    R.layout.activity_migration,
    R.drawable.baseline_restore_glif,
    R.string.restore_apps_and_data,
    R.string.data_restore_desc
) {
    companion object {
        private const val TAG = "MigrationActivity"
    }

    private lateinit var skip: View
    private lateinit var next: View

    override fun bindViews() {
        skip = findViewById(R.id.skip)
        next = findViewById(R.id.next)
    }

    override fun setupActions() {
        skip.setOnClickListener { SetupWizard.next(this) }
        next.setOnClickListener { MigrationActions.launchMigration(this) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult: $resultCode, data=$data")
        MigrationActions.handleResult(this, requestCode, resultCode)
        super.onActivityResult(requestCode, resultCode, data)
    }
}