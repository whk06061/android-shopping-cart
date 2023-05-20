package woowacourse.shopping.database.cart

import android.provider.BaseColumns

object CartConstant : BaseColumns {
    const val TABLE_NAME = "cart_products"
    const val TABLE_COLUMN_PRODUCT_ID = "product_id"
    const val TABLE_COLUMN_PRODUCT_NAME = "product_name"
    const val TABLE_COLUMN_PRODUCT_PRICE = "product_price"
    const val TABLE_COLUMN_PRODUCT_IMAGE_URL = "product_img_url"
    const val TABLE_COLUMN_PRODUCT_COUNT = "product_count"
    const val TABLE_COLUMN_PRODUCT_CHECKED = "product_checked"
    const val TABLE_COLUMN_PRODUCT_SAVE_TIME = "product_save_time"
}
