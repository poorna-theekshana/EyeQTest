package com.example.eyeqtest.blinkingrouting


import android.graphics.PointF
import android.os.SystemClock

class EyePhysics {
    private val TIME_PERIOD_MS: Long = 1000
    private val FRICTION = 2.2f
    private val GRAVITY = 0.5f
    private val BOUNCE_MULTIPLIER = 0.8f
    private val ZERO_TOLERANCE = 0.001f

    private var mLastUpdateTimeMs = SystemClock.elapsedRealtime()
    private var mEyePosition: PointF? = null
    private var mEyeRadius = 0f
    private var mIrisPosition: PointF? = null
    private var mIrisRadius = 0f
    private var vx = 0.0f
    private var vy = 0.0f
    private var mConsecutiveBounces = 0

    fun nextIrisPosition(eyePosition: PointF, eyeRadius: Float, irisRadius: Float): PointF {
        mEyePosition = eyePosition
        mEyeRadius = eyeRadius
        if (mIrisPosition == null) {
            mIrisPosition = eyePosition
        }
        mIrisRadius = irisRadius

        val nowMs = SystemClock.elapsedRealtime()
        val elapsedTimeMs = nowMs - mLastUpdateTimeMs
        val simulationRate = elapsedTimeMs.toFloat() / TIME_PERIOD_MS
        mLastUpdateTimeMs = nowMs

        if (!isStopped()) {
            vy += GRAVITY * simulationRate
        }

        vx = applyFriction(vx, simulationRate)
        vy = applyFriction(vy, simulationRate)

        val x = mIrisPosition!!.x + vx * mIrisRadius * simulationRate
        val y = mIrisPosition!!.y + vy * mIrisRadius * simulationRate
        mIrisPosition = PointF(x, y)

        makeIrisInBounds(simulationRate)

        return mIrisPosition!!
    }

    private fun applyFriction(velocity: Float, simulationRate: Float): Float {
        var updatedVelocity = velocity
        if (isZero(velocity)) {
            updatedVelocity = 0.0f
        } else if (velocity > 0) {
            updatedVelocity = Math.max(0.0f, velocity - FRICTION * simulationRate)
        } else {
            updatedVelocity = Math.min(0.0f, velocity + FRICTION * simulationRate)
        }
        return updatedVelocity
    }

    private fun makeIrisInBounds(simulationRate: Float) {
        val irisOffsetX = mIrisPosition!!.x - mEyePosition!!.x
        val irisOffsetY = mIrisPosition!!.y - mEyePosition!!.y
        val maxDistance = mEyeRadius - mIrisRadius
        val distance = Math.sqrt((irisOffsetX * irisOffsetX + irisOffsetY * irisOffsetY).toDouble()).toFloat()
        if (distance <= maxDistance) {
            mConsecutiveBounces = 0
            return
        }
        mConsecutiveBounces++
        val ratio = maxDistance / distance
        val x = mEyePosition!!.x + ratio * irisOffsetX
        val y = mEyePosition!!.y + ratio * irisOffsetY
        val dx = x - mIrisPosition!!.x
        vx = applyBounce(vx, dx, simulationRate) / mConsecutiveBounces
        val dy = y - mIrisPosition!!.y
        vy = applyBounce(vy, dy, simulationRate) / mConsecutiveBounces
        mIrisPosition = PointF(x, y)
    }

    private fun applyBounce(velocity: Float, distOutOfBounds: Float, simulationRate: Float): Float {
        var updatedVelocity = velocity
        if (isZero(distOutOfBounds)) {
            return updatedVelocity
        }
        updatedVelocity *= -1
        val bounce = BOUNCE_MULTIPLIER * Math.abs(distOutOfBounds / mIrisRadius)
        updatedVelocity += if (velocity > 0) {
            bounce * simulationRate
        } else {
            -bounce * simulationRate
        }
        return updatedVelocity
    }

    private fun isStopped(): Boolean {
        if (mEyePosition!!.y >= mIrisPosition!!.y) {
            return false
        }
        val irisOffsetY = mIrisPosition!!.y - mEyePosition!!.y
        val maxDistance = mEyeRadius - mIrisRadius
        return if (irisOffsetY < maxDistance) {
            false
        } else isZero(vx) && isZero(vy)
    }

    private fun isZero(num: Float): Boolean {
        return num < ZERO_TOLERANCE && num > -1 * ZERO_TOLERANCE
    }
}
