package woowacourse.shopping.productdetail.contract.presenter

import com.example.domain.model.CartRepository
import com.example.domain.model.RecentRepository
import woowacourse.shopping.mapper.toDomain
import woowacourse.shopping.model.ProductUIModel
import woowacourse.shopping.productdetail.contract.ProductDetailContract

class ProductDetailPresenter(
    private val view: ProductDetailContract.View,
    private val product: ProductUIModel,
    private val cartRepository: CartRepository,
    private val recentRepository: RecentRepository
) : ProductDetailContract.Presenter {

    override fun setUpProductDetail() {
        view.setProductDetail(product)
    }

    override fun addProductToCart() {
        cartRepository.add(product.toDomain())
    }

    override fun addProductToRecent() {
        recentRepository.findById(product.id)?.let {
            recentRepository.delete(it.id)
        }
        recentRepository.insert(product.toDomain())
    }
}