package me.pexcn.kotlin.samples.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by pexcn on 2017-07-14.
 */
object ImageUtils {
    fun getOutputMediaFile(type: Int): File? {
        val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).path
        val camera = File.separator + "Camera"
        val dir = File(dcim + camera)
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return null
            }
        }
        val time = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val file: File
        if (type == MEDIA_TYPE_IMAGE) {
            file = File(dir.path + File.separator + "IMG_" + time + ".jpg")
        } else if (type == MEDIA_TYPE_VIDEO) {
            file = File(dir.path + File.separator + "VID_" + time + ".mp4")
        } else {
            return null
        }
        return file
    }

    fun getOutputMediaFileUri(type: Int): Uri {
        return Uri.fromFile(getOutputMediaFile(type))
    }

    fun addImageToGallery(context: Context, uri: Uri) {
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = uri
        context.sendBroadcast(intent)
    }

    fun getBitmapFromUri(context: Context, uri: Uri): Bitmap {
        return MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    }

    fun getCompressImageFixRotate(src: Uri, dstDir: String): Uri {
        val path = src.path
        val exif = ExifInterface(path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

        val angle = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }

        var bitmap = BitmapFactory.decodeFile(path)

        if (angle != 0) {
            val matrix = Matrix()
            matrix.setRotate(angle.toFloat())
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }

        var fos: FileOutputStream? = null
        val dstPath: String = dstDir + File.separator + System.currentTimeMillis() + ".jpg"
        try {
            fos = FileOutputStream(dstPath)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)
        } finally {
            fos?.close()
        }

        return Uri.fromFile(File(dstPath))
    }

    fun getCompressImage(src: Uri, dstDir: String): Uri {
        val bitmap = BitmapFactory.decodeFile(src.path)
        val dstPath: String = dstDir + File.separator + System.currentTimeMillis() + ".jpg"
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(dstPath)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)
        } finally {
            fos?.close()
        }

        return Uri.fromFile(File(dstPath))
    }

    fun getImageThumbnail(path: String, width: Int, height: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)

        val imageW = options.outWidth
        val imageH = options.outHeight
        val sampleSize = (imageW / width + imageH / height) / 2
        options.inSampleSize = sampleSize

        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path, options)
    }
}
