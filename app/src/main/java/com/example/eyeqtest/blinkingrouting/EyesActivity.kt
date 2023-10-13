package com.example.eyeqtest.blinkingrouting

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.eyeqtest.HandEyeCoordination.HandEyeCoordinationHome
import com.example.eyeqtest.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.MultiProcessor
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.FaceDetector
import com.google.android.gms.vision.face.LargestFaceFocusingProcessor
import com.google.android.material.snackbar.Snackbar
import java.io.IOException

class EyesActivity : AppCompatActivity() {
    private val TAG = "GooglyEyes"
    private val RC_HANDLE_CAMERA_PERM = 2
    private var mIsFrontFacing = true
    private var mCameraSource: CameraSource? = null
    private var congratulationDialogShown = false
    private lateinit var faceTracker: FaceTracker
    private lateinit var pleaseShowFaceTextView: TextView


    private lateinit var faceOverlay: GraphicOverlay
    private lateinit var preview: CameraSourcePreview
    private lateinit var blinkCountTextView: TextView
    private var blinkCountingActive = true


    private var blinkCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_eye_blink)

        faceOverlay = findViewById(R.id.faceOverlay)
        preview = findViewById(R.id.preview)
        blinkCountTextView = findViewById(R.id.blinkCountTextView)

        // Initialize the pleaseShowFaceTextView
        pleaseShowFaceTextView = findViewById(R.id.pleaseShowFaceTextView)

        faceTracker = FaceTracker(faceOverlay, blinkCountCallback, pleaseShowFaceTextView)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            createCameraSource()
        } else {
            requestCameraPermission()
        }
    }

    private val blinkCountCallback: (Int) -> Unit = { count ->
        runOnUiThread {
            if (blinkCountingActive) {
                blinkCount = count
                blinkCountTextView.text = "Blink Count: $blinkCount"

                if (blinkCount >= 30 && !congratulationDialogShown) {
                    congratulationDialogShown = true
                    showCongratulationsDialog()
                    blinkCountingActive = false
                }
            }
        }
    }




    private val mFlipButtonListener = View.OnClickListener {
        mIsFrontFacing = !mIsFrontFacing
        mCameraSource?.release()
        mCameraSource = null
        createCameraSource()
        startCameraSource()
    }

    private fun requestCameraPermission() {
        val permissions = arrayOf(Manifest.permission.CAMERA)

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM)
            return
        }

        val listener = View.OnClickListener {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM)
        }

        Snackbar.make(faceOverlay, R.string.permission_camera_rationale, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.ok, listener)
            .show()
    }

    override fun onResume() {
        super.onResume()
        startCameraSource()
    }

    override fun onPause() {
        super.onPause()
        preview.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCameraSource?.release()
    }

    fun showCongratulationsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.congratulations_dialog, null)
        val congratulationsTitle = dialogView.findViewById<TextView>(R.id.congratulationsTitle)
        val congratulationsMessage = dialogView.findViewById<TextView>(R.id.congratulationsMessage)
        val okButton = dialogView.findViewById<Button>(R.id.okButton)
        val restartButton = dialogView.findViewById<Button>(R.id.restartButton)

        congratulationsTitle.text = "Congratulations!"
        congratulationsMessage.text = "You blinked 100 times! Well done!"

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        val dialog = builder.create()

        okButton.setOnClickListener {
            dialog.dismiss()
            val mainActivityIntent = Intent(this@EyesActivity, HandEyeCoordinationHome::class.java)
            startActivity(mainActivityIntent)
            finish()

        }


        restartButton.setOnClickListener {
            dialog.dismiss()
            val mainActivityIntent = Intent(this@EyesActivity, EyesActivity::class.java)
            startActivity(mainActivityIntent)
            finish() // Optional: finish the current activity if needed

        }

            dialog.show()
    }




    private fun createFaceDetector(context: Context): FaceDetector {
        val detector = FaceDetector.Builder(context)
            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
            .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
            .setTrackingEnabled(true)
            .setMode(FaceDetector.FAST_MODE)
            .setProminentFaceOnly(mIsFrontFacing)
            .setMinFaceSize(if (mIsFrontFacing) 0.35f else 0.15f)
            .build()

        val processor: Detector.Processor<Face> = if (mIsFrontFacing) {
            val tracker = FaceTracker(faceOverlay, blinkCountCallback, pleaseShowFaceTextView)

            LargestFaceFocusingProcessor.Builder(detector, tracker).build()
        } else {
            val factory = MultiProcessor.Factory<Face> {
                FaceTracker(faceOverlay, blinkCountCallback, pleaseShowFaceTextView)
            }
            MultiProcessor.Builder(factory).build()
        }

        detector.setProcessor(processor)

        if (!detector.isOperational) {
            val lowStorageFilter = IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW)
            val hasLowStorage = registerReceiver(null, lowStorageFilter) != null

            if (hasLowStorage) {
                Toast.makeText(this, R.string.low_storage_error, Toast.LENGTH_LONG).show()
                Log.w(TAG, getString(R.string.low_storage_error))
            }
        }
        return detector
    }

    private fun createCameraSource() {
        val context = applicationContext
        val detector = createFaceDetector(context)
        val facing = if (mIsFrontFacing) CameraSource.CAMERA_FACING_FRONT else CameraSource.CAMERA_FACING_BACK
        mCameraSource = CameraSource.Builder(context, detector)
            .setFacing(facing)
            .setRequestedPreviewSize(320, 240)
            .setRequestedFps(60.0f)
            .setAutoFocusEnabled(true)
            .build()
    }

    private fun startCameraSource() {
        val code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        if (code != ConnectionResult.SUCCESS) {
            val RC_HANDLE_GMS = 0
            val dlg = GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS)
            if (dlg != null) {
                dlg.show()
            }
        }

        mCameraSource?.let {
            try {
                val overlay = faceOverlay
                preview.start(it, overlay)
            } catch (e: IOException) {
                Log.e(TAG, "Unable to start camera source.", e)
                it.release()
                mCameraSource = null
            }
        }
    }
}
