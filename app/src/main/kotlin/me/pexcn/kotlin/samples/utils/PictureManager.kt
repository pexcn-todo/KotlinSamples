package me.pexcn.kotlin.samples.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore

/**
 * Created by pexcn on 2017-07-15.
 */
class PictureManager(val activity: Activity, val mode: Int) {
    companion object {
        val CODE_CAMERA = 0x10
        val CODE_PICKER = 0x11

        val MODE_NORMAL = 0x20
        val MODE_CUTTER = 0x21
    }

    private lateinit var mImageUri: Uri
    private lateinit var onUriResult: (uri: Uri) -> Unit

    fun choose(onUriResult: (uri: Uri) -> Unit) {
        this.onUriResult = onUriResult

        AlertDialog.Builder(activity)
                .setCancelable(false)
                .setTitle("Select")
                .setMessage("Select picture where from?")
                .setPositiveButton("Camera", { _, _ -> handleCamera(mode) })
                .setNegativeButton("Picker", { _, _ -> handlePicker(mode) })
                .show()
    }

    fun onResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CODE_CAMERA -> {
                    ImageUtils.addImageToGallery(activity, mImageUri)
                    val dir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES).path
                    val uri = ImageUtils.getCompressImageFixRotate(mImageUri, dir)
                    onUriResult(uri)
                }
                CODE_PICKER -> {
                    val uri = data?.data
                    uri?.let { onUriResult(it) }
                }
            }
        }
    }

    private fun handleCamera(mode: Int) {
        if (mode == MODE_NORMAL) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            mImageUri = ImageUtils.getOutputMediaFileUri(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.name)
            intent.resolveActivity(activity.packageManager)?.let {
                activity.startActivityForResult(intent, CODE_CAMERA)
            }
        } else if (mode == MODE_CUTTER) {

        }
    }

    private fun handlePicker(mode: Int) {
        if (mode == MODE_NORMAL) {

        } else if (mode == MODE_CUTTER) {

        }
    }
}
