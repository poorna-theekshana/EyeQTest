package com.example.eyeqtest.blinkingrouting3


import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF

class EyesGraphics3(overlay: GraphicOverlay3) : GraphicOverlay3.Graphic(overlay) {
    private val EYE_RADIUS_PROPORTION = 0.45f
    private val IRIS_RADIUS_PROPORTION = EYE_RADIUS_PROPORTION / 2.0f

    private var mEyeWhitesPaint: Paint = Paint()
    private var mEyeIrisPaint: Paint = Paint()
    private var mEyeOutlinePaint: Paint = Paint()
    private var mEyeLidPaint: Paint = Paint()

    private val mLeftPhysics: EyePhysics3 = EyePhysics3()
    private val mRightPhysics: EyePhysics3 = EyePhysics3()

    @Volatile private var mLeftPosition: PointF? = null
    @Volatile private var mLeftOpen: Boolean = false

    @Volatile private var mRightPosition: PointF? = null
    @Volatile private var mRightOpen: Boolean = false

    init {
        mEyeWhitesPaint.color = Color.TRANSPARENT
        mEyeWhitesPaint.style = Paint.Style.FILL

        mEyeLidPaint.color = Color.YELLOW
        mEyeLidPaint.style = Paint.Style.FILL

        mEyeIrisPaint.color = Color.RED
        mEyeIrisPaint.style = Paint.Style.STROKE
        mEyeIrisPaint.strokeWidth = 2f

        mEyeOutlinePaint.color = Color.BLACK
        mEyeOutlinePaint.style = Paint.Style.STROKE
        mEyeOutlinePaint.strokeWidth = 3f
    }

    fun updateEyes(
        leftPosition: PointF,
        leftOpen: Boolean,
        rightPosition: PointF,
        rightOpen: Boolean
    ) {
        mLeftPosition = leftPosition
        mLeftOpen = leftOpen

        mRightPosition = rightPosition
        mRightOpen = rightOpen

        postInvalidate()
    }

    override fun draw(canvas: Canvas) {
        val detectLeftPosition = mLeftPosition
        val detectRightPosition = mRightPosition
        if (detectLeftPosition == null || detectRightPosition == null) {
            return
        }

        val leftPosition = PointF(translateX(detectLeftPosition.x), translateY(detectLeftPosition.y))
        val rightPosition = PointF(translateX(detectRightPosition.x), translateY(detectRightPosition.y))

        val distance = Math.sqrt(
            Math.pow(rightPosition.x - leftPosition.x.toDouble(), 2.0) +
                    Math.pow(rightPosition.y - leftPosition.y.toDouble(), 2.0)
        ).toFloat()
        val eyeRadius = EYE_RADIUS_PROPORTION * distance
        val irisRadius = IRIS_RADIUS_PROPORTION * distance

        val leftIrisPosition = mLeftPhysics.nextIrisPosition(leftPosition, eyeRadius, irisRadius)
        drawEye(canvas, leftPosition, eyeRadius, leftIrisPosition, irisRadius, mLeftOpen)

        val rightIrisPosition = mRightPhysics.nextIrisPosition(rightPosition, eyeRadius, irisRadius)
        drawEye(canvas, rightPosition, eyeRadius, rightIrisPosition, irisRadius, mRightOpen)
    }

    private fun drawEye(
        canvas: Canvas,
        eyePosition: PointF,
        eyeRadius: Float,
        irisPosition: PointF,
        irisRadius: Float,
        isOpen: Boolean
    ) {
        if (isOpen) {
            canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyeWhitesPaint)
            canvas.drawCircle(irisPosition.x, irisPosition.y, irisRadius, mEyeIrisPaint)
        } else {
            canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyeLidPaint)
            val y = eyePosition.y
            val start = eyePosition.x - eyeRadius
            val end = eyePosition.x + eyeRadius
            canvas.drawLine(start, y, end, y, mEyeOutlinePaint)
        }
        canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyeOutlinePaint)
    }
}
