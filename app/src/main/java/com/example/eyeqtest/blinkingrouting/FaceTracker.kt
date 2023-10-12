import android.graphics.PointF
import com.example.eyeqtest.blinkingrouting.EyesActivity
import com.example.eyeqtest.blinkingrouting.EyesGraphics
import com.example.eyeqtest.blinkingrouting.GraphicOverlay
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.Landmark

class FaceTracker(
    private val mOverlay: GraphicOverlay,
    private val blinkCountCallback: (Int) -> Unit
) : Tracker<Face>() {
    private val EYE_CLOSED_THRESHOLD = 0.4f
    private val EYE_OPEN_THRESHOLD = 0.6f
    private val BLINK_DETECTION_DELAY_MS = 150 // Adjust this delay as needed
    private val BLINK_RESET_DELAY_MS = 2000 // Adjust this delay as needed

    private val mPreviousProportions: MutableMap<Int, PointF> = HashMap()
    private var lastBlinkTime = 0L
    private var totalClosedEyesCount = 0
    private var lastBlinkResetTime = 0L


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

        // Check for blink if both eyes are closed and enough time has passed
        if (!isLeftOpen && !isRightOpen && currentTime - lastBlinkTime >= BLINK_DETECTION_DELAY_MS) {
            totalClosedEyesCount++
            lastBlinkTime = currentTime
            blinkCountCallback(totalClosedEyesCount)
        }
        // Reset blink count after a certain delay
    }



    override fun onMissing(detectionResults: Detector.Detections<Face>?) {}

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