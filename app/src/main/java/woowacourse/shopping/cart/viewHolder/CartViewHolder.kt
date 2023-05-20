package woowacourse.shopping.cart.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.shopping.cart.CartItem
import woowacourse.shopping.databinding.CartItemBinding
import woowacourse.shopping.model.CartProductUIModel

class CartViewHolder private constructor(
    val binding: CartItemBinding,
    val onItemClick: (CartProductUIModel) -> Unit,
    val onRemove: (Int) -> Unit,
    onIncreaseCount: (Int, Int) -> Unit,
    onDecreaseCount: (Int, Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.customCountView.plusClickListener = {
            onIncreaseCount(binding.customCountView.count, bindingAdapterPosition)
        }
        binding.customCountView.minusClickListener = {
            onDecreaseCount(binding.customCountView.count, bindingAdapterPosition)
        }
    }

    fun bind(cart: CartItem) {
        binding.customCountView.count = cart.cartProduct.count
        binding.cartProduct = cart.cartProduct
        binding.root.setOnClickListener { onItemClick(cart.cartProduct) }
        binding.removeButton.setOnClickListener { onRemove(cart.cartProduct.product.id) }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onClick: (CartProductUIModel) -> Unit,
            onRemove: (Int) -> Unit,
            onIncreaseCount: (Int, Int) -> Unit,
            onDecreaseCount: (Int, Int) -> Unit,
        ): CartViewHolder {
            val binding =
                CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CartViewHolder(
                binding,
                onClick,
                onRemove,
                onIncreaseCount,
                onDecreaseCount,
            )
        }
    }
}
