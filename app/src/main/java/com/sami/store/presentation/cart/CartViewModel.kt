package com.sami.store.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.store.domaine.model.Product
import com.sami.store.domaine.use_case.get_cart.CartUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
): ViewModel() {

    private val _stateGetProduct = mutableStateOf(ProductState())
    val stateGetProduct: State<ProductState> = _stateGetProduct

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val quantity = mutableStateOf(1)

    init {
        getProducts()
    }

    private var getProductsJob: Job? = null

    private fun getProducts() {
        getProductsJob?.cancel()
        getProductsJob = cartUseCases.getProducts()
            .onEach { products ->
                _stateGetProduct.value = stateGetProduct.value.copy(
                    products = products
                )
            }
            .launchIn(viewModelScope)
    }

    fun addProductToCart(product: Product){
        viewModelScope.launch {
            cartUseCases.addProduct(
                Product(
                    title = product.title,
                    id = product.id,
                    image = product.image,
                    description = product.description,
                    price = product.price,
                    ratingCount = product.ratingCount,
                    ratingRate = product.ratingRate,
                    category = product.category,
                    quantity = quantity.value
                )
            )
            _eventFlow.emit(UiEvent.SaveProduct)
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveProduct: UiEvent()
    }
}