package woowacourse.shopping.cart.contract.presenter

import com.example.domain.model.CartRepository
import woowacourse.shopping.cart.CartItem
import woowacourse.shopping.cart.contract.CartContract
import woowacourse.shopping.mapper.toUIModel
import woowacourse.shopping.model.CartUIModel
import woowacourse.shopping.model.ProductUIModel

class CartPresenter(
    val view: CartContract.View,
    private val repository: CartRepository,
    offset: Int = 0,
) : CartContract.Presenter {
    private var offset = offset
        set(value) {
            field = when {
                value < 0 -> 0
                value > repository.getAll().size -> repository.getAll().size
                else -> value
            }
        }

    override fun setUpCarts() {
        view.setCarts(
            repository.getSubList(offset, STEP).map { CartItem(it.toUIModel()) },
            CartUIModel(
                offset + STEP < repository.getAll().size,
                0 < offset,
                offset / STEP + 1,
            ),
        )
    }

    override fun pageUp() {
        offset += STEP
        setUpCarts()
    }

    override fun pageDown() {
        offset -= STEP
        setUpCarts()
    }

    override fun removeItem(id: Int) {
        repository.remove(id)
        if (offset == repository.getAll().size) {
            offset -= STEP
        }
        setUpCarts()
    }

    override fun navigateToItemDetail(product: ProductUIModel) {
        view.navigateToItemDetail(product)
    }

    override fun getOffset(): Int {
        return offset
    }

    companion object {
        private const val STEP = 5
    }
}
