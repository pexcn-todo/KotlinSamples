package me.pexcn.kotlin.samples.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import me.pexcn.kotlin.samples.R
import me.pexcn.kotlin.samples.base.BaseActivity
import me.pexcn.kotlin.samples.utils.ImageUtils
import me.pexcn.kotlin.samples.utils.PictureManager
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by pexcn on 2017-05-03.
 */
class ImageActivity : BaseActivity() {
    private lateinit var mManager: PictureManager
    private lateinit var mImage: ImageView
    private lateinit var mButton: Button

    companion object {
        const val RC_REQUEST_PERMISSION = 0x01
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_image
    }

    override fun init() {
        super.init()

        requiresPermission()

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
        // val bitmap = ImageUtils.getBitmapFromUri(this, uri)

        val thumbnail = ImageUtils.getImageThumbnail(uri.path, mImage.width, mImage.height)
        mImage.setImageBitmap(thumbnail)
    }

    @AfterPermissionGranted(RC_REQUEST_PERMISSION)
    fun requiresPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {

        } else {
            EasyPermissions.requestPermissions(this, "需要有读取外部存储设备的权限", RC_REQUEST_PERMISSION, *perms)
        }
    }
}
