package com.example.eyeqtest.blinkingrouting2

import android.graphics.PointF
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.face.Face

class FaceTracker2(
    private val mOverlay: GraphicOverlay2,
    private val blinkCountCallback: (Int) -> Unit,
    private val pleaseShowFaceTextView: TextView
) : Tracker<Face>() {
    private val EYE_CLOSED_THRESHOLD = 0.4f
    private val EYE_OPEN_THRESHOLD = 0.6f
    private val BLINK_DETECTION_DELAY_MS = 150
    private val BLINK_RESET_DELAY_MS = 2000

    private val mPreviousProportions: MutableMap<Int, PointF> = HashMap()
    private var lastBlinkTime = 0L
    private var totalClosedEyesCount = 0
    private var lastBlinkResetTime = 0L

    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onNewItem(id: Int, face: Face?) {
        lastBlinkResetTime = System.currentTimeMillis()
    }

    override fun onUpdate(detectionResults: Detector.Detections<Face>, face: Face?) {
        updatePreviousProportions(face)

        val currentTime = System.currentTimeMillis()
        val leftOpenScore = face?.isLeftEyeOpenProbability ?: Face.UNCOMPUTED_PROBABILITY
        val rightOpenScore = face?.isRightEyeOpenProbability ?: Face.UNCOMPUTED_PROBABILITY

        val isLeftOpen = leftOpenScore > EYE_CLOSED_THRESHOLD
        val isRightOpen = rightOpenScore > EYE_CLOSED_THRESHOLD

        if (!isLeftOpen && !isRightOpen && currentTime - lastBlinkTime >= BLINK_DETECTION_DELAY_MS) {
            totalClosedEyesCount++
            lastBlinkTime = currentTime
            blinkCountCallback(totalClosedEyesCount)
        }


        mainHandler.post {
            if (face == null) {
                pleaseShowFaceTextView.visibility = View.VISIBLE
            } else {
                pleaseShowFaceTextView.visibility = View.INVISIBLE
            }
        }
    }

    override fun onMissing(detectionResults: Detector.Detections<Face>?) {
        mainHandler.post {
            pleaseShowFaceTextView.visibility = View.VISIBLE
        }
    }

    override fun onDone() {
        totalClosedEyesCount = 0
        lastBlinkResetTime = System.currentTimeMillis()
    }

    private fun updatePreviousProportions(face: Face?) {
        face?.landmarks?.forEach { landmark ->
            val position = landmark.position
            val xProp = (position.x - face.position.x) / face.width
            val yProp = (position.y - face.position.y) / face.height
            mPreviousProportions[landmark.type] = PointF(xProp, yProp)
        }
    }
}
