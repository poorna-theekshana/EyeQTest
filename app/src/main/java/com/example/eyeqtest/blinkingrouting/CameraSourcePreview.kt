package com.example.eyeqtest.blinkingrouting

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.eyeqtest.blinkingrouting.GraphicOverlay
import com.google.android.gms.common.images.Size
import com.google.android.gms.vision.CameraSource
import java.io.IOException

class CameraSourcePreview(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
    private val TAG = "CameraSourcePreview"
    private val mContext: Context = context
    private var mSurfaceView: SurfaceView = SurfaceView(context)
    private var mStartRequested = false
    private var mSurfaceAvailable = false
    private var mCameraSource: CameraSource? = null
    private var mOverlay: GraphicOverlay? = null

    init {
        mSurfaceView.holder.addCallback(SurfaceCallback())
        addView(mSurfaceView)
    }

    @Throws(IOException::class)
    fun start(cameraSource: CameraSource) {
        if (cameraSource == null) {
            stop()
        }
        mCameraSource = cameraSource
        if (mCameraSource != null) {
            mStartRequested = true
            startIfReady()
        }
    }

    @Throws(IOException::class)
    fun start(cameraSource: CameraSource, overlay: GraphicOverlay) {
        mOverlay = overlay
        start(cameraSource)
    }

    fun stop() {
        mCameraSource?.stop()
    }

    fun release() {
        mCameraSource?.release()
        mCameraSource = null
    }

    @Throws(IOException::class)
    private fun startIfReady() {
        if (mStartRequested && mSurfaceAvailable) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // Dexter will perform here (prapon)
                // do not touch here
                return
            }
            mCameraSource?.start(mSurfaceView.holder)
            mOverlay?.let {
                val size: Size? = mCameraSource?.previewSize
                if (size != null) {
                    val min = Math.min(size.width, size.height)
                    val max = Math.max(size.width, size.height)
                    if (isPortraitMode()) {
                        mCameraSource?.cameraFacing?.let { it1 -> it.setCameraInfo(min, max, it1) }
                    } else {
                        mCameraSource?.cameraFacing?.let { it1 -> it.setCameraInfo(max, min, it1) }
                    }
                    it.clear()
                }
            }
            mStartRequested = false
        }
    }

    private inner class SurfaceCallback : SurfaceHolder.Callback {
        override fun surfaceCreated(surface: SurfaceHolder) {
            mSurfaceAvailable = true
            try {
                startIfReady()
            } catch (e: IOException) {
                Log.e(TAG, "Could not start camera source.", e)
            }
        }

        override fun surfaceDestroyed(surface: SurfaceHolder) {
            mSurfaceAvailable = false
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var previewWidth = 320
        var previewHeight = 240
        if (mCameraSource != null) {
            val size: Size? = mCameraSource?.previewSize
            if (size != null) {
                previewWidth = size.width
                previewHeight = size.height
            }
        }

        if (isPortraitMode()) {
            val tmp = previewWidth
            previewWidth = previewHeight
            previewHeight = tmp
        }

        val viewWidth: Int = right - left
        val viewHeight: Int = bottom - top

        val childWidth: Int
        val childHeight: Int
        var childXOffset = 0
        var childYOffset = 0
        val widthRatio = viewWidth.toFloat() / previewWidth.toFloat()
        val heightRatio = viewHeight.toFloat() / previewHeight.toFloat()

        if (widthRatio > heightRatio) {
            childWidth = viewWidth
            childHeight = (previewHeight * widthRatio).toInt()
            childYOffset = (childHeight - viewHeight) / 2
        } else {
            childWidth = (previewWidth * heightRatio).toInt()
            childHeight = viewHeight
            childXOffset = (childWidth - viewWidth) / 2
        }

        for (i in 0 until childCount) {
            getChildAt(i).layout(
                -1 * childXOffset, -1 * childYOffset,
                childWidth - childXOffset, childHeight - childYOffset
            )
        }

        try {
            startIfReady()
        } catch (e: IOException) {
            Log.e(TAG, "Could not start camera source.", e)
        }
    }

    private fun isPortraitMode(): Boolean {
        val orientation = mContext.resources.configuration.orientation
        return orientation == Configuration.ORIENTATION_PORTRAIT
    }
}