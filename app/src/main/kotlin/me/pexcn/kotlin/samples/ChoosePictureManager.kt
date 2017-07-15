package me.pexcn.kotlin.samples

class ChoosePictureManager(var model: Int) {
//    companion object {
//        private val PICTURE_MODEL_DEFAULT = 80
//        private val PICTURE_MODEL_ICON = 81
//
//        private val REQUEST_PHOTOGRAPH = 90
//        private val REQUEST_PHOTOS_STORAGE = 91
//
//        fun showEditIconDialog(f: NavigationFragment, onBitmapReturn: (bitmap: Bitmap) -> Unit) {
//            var choosePictureManager = ChoosePictureManager(PICTURE_MODEL_ICON)
//            initData(choosePictureManager, f, onBitmapReturn)
//            var d = EditIconDialog(choosePictureManager.onPhotographClick, choosePictureManager.onPhotosStorageClick)
//            d.show(f.childFragmentManager, "edit_icon_dialog")
//        }
//
//        fun showChoosePictureDialog(f: NavigationFragment, onBitmapReturn: (bitmap: Bitmap) -> Unit) {
//            var choosePictureManager = ChoosePictureManager(PICTURE_MODEL_DEFAULT)
//            initData(choosePictureManager, f, onBitmapReturn)
//            var d = EditIconDialog(choosePictureManager.onPhotographClick, choosePictureManager.onPhotosStorageClick)
//            d.show(f.childFragmentManager, "choose_picture_dialog")
//        }
//
//        private fun initData(choosePictureManager: ChoosePictureManager, f: NavigationFragment, onBitmapReturn: (bitmap: Bitmap) -> Unit) {
//            choosePictureManager.fragment = f
//            choosePictureManager.crmActivity = f.mainActivity
//            choosePictureManager.onBitmapReturn = onBitmapReturn
//            choosePictureManager.crmActivity?.onBitmapResult = choosePictureManager::onBitmapResult
//        }
//
//        private fun getImageBitmap(context: Context, intent: Intent, isCapture: Boolean): Bitmap? {
//            val bitmap: Bitmap
//            if (intent.extras != null && isCapture) {
//                bitmap = intent.extras.get("data") as Bitmap
//            } else {
//                val uri = intent.data
//                try {
//                    bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
//                } catch (e: FileNotFoundException) {
//                    e.printStackTrace()
//                    return null
//                }
//            }
//            return bitmap
//        }
//    }
//
//    private var crmActivity: CrmActivity? = null
//    private var fragment: NavigationFragment? = null
//    private var onBitmapReturn: (bitmap: Bitmap) -> Unit? = {}
//
//    private fun onBitmapResult(requestCode: Int, resultCode: Int, data: Intent?): Unit {
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == REQUEST_PHOTOGRAPH) {
//                var bitmap = getImageBitmap(crmActivity!!, data!!, true)!!
//                if (PICTURE_MODEL_DEFAULT == model) {
//                    onBitmapReturn(bitmap)
//                } else if (PICTURE_MODEL_ICON == model) {
//                    fragment?.pushFragment(CutIconFragment(bitmap, onBitmapCutReturn))
//                }
//            } else if (requestCode == REQUEST_PHOTOS_STORAGE) {
//                var bitmap = getImageBitmap(crmActivity!!, data!!, false)!!
//                if (PICTURE_MODEL_DEFAULT == model) {
//                    onBitmapReturn(bitmap)
//                } else if (PICTURE_MODEL_ICON == model) {
//                    fragment?.pushFragment(CutIconFragment(bitmap, onBitmapCutReturn))
//                }
//            }
//        }
//    }
//
//    private var onBitmapCutReturn: (cutBitmap: Bitmap) -> Unit = {
//        cutBitmap ->
//        onBitmapReturn(cutBitmap)
//    }
//
//    private var onPhotographClick: () -> Unit = {
//        var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (i.resolveActivity(crmActivity?.packageManager) != null) {
//            crmActivity?.startActivityForResult(i, REQUEST_PHOTOGRAPH)
//        }
//    }
//
//
//    private var onPhotosStorageClick: () -> Unit = {
//        var i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        i.type = "image/*"
//        crmActivity?.startActivityForResult(Intent.createChooser(i, ""), REQUEST_PHOTOS_STORAGE)
//    }
}
