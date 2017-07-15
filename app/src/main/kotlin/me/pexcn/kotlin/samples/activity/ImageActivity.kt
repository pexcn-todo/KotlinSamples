package me.pexcn.kotlin.samples.activity

import android.content.Intent
import android.media.ThumbnailUtils
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import me.pexcn.kotlin.samples.R
import me.pexcn.kotlin.samples.base.BaseActivity
import me.pexcn.kotlin.samples.utils.ImageUtils
import me.pexcn.kotlin.samples.utils.PictureManager

/**
 * Created by pexcn on 2017-05-03.
 */
class ImageActivity : BaseActivity() {
    private lateinit var mManager: PictureManager
    private lateinit var mImage: ImageView
    private lateinit var mButton: Button

    override fun getLayoutId(): Int {
        return R.layout.activity_image
    }

    override fun init() {
        super.init()

        mImage = findViewById(R.id.image) as ImageView
        mButton = findViewById(R.id.button) as Button

        mManager = PictureManager(this, PictureManager.MODE_NORMAL)

        mButton.setOnClickListener {
            mManager.choose(onUriResult)
        }
    }

    override fun isSubActivity(): Boolean {
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mManager.onResult(requestCode, resultCode, data)
    }

    private val onUriResult: (Uri) -> Unit = {
        uri ->
        val bitmap = ImageUtils.getBitmapFromUri(this, uri)

        var width = bitmap.width
        var height = bitmap.height

        while (width > mImage.width || height > mImage.height) {
            width = (width * 0.9).toInt()
            height = (height * 0.9).toInt()
        }

        val thumbnail = ThumbnailUtils.extractThumbnail(bitmap, width, height)
        mImage.setImageBitmap(thumbnail)
    }
}
