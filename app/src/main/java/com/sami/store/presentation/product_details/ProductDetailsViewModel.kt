package com.sami.store.presentation.product_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.store.domaine.model.Product
import com.sami.store.domaine.use_case.get_cart.CartUseCases
import com.sami.store.domaine.use_case.get_product.GetProductUseCase
import com.sami.store.util.Constants.PARAM_PRODUCT_ID
import com.sami.store.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _stateProduct = mutableStateOf(ProductState())
    val stateProduct: State<ProductState> = _stateProduct

    init {
        savedStateHandle.get<String>(PARAM_PRODUCT_ID)?.let { productId ->
            getProductDetail(productId = productId.toInt())
        }
    }

    private fun getProductDetail(productId: Int) {
        getProductUseCase(productId = productId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateProduct.value = ProductState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _stateProduct.value = ProductState(
                        product = result.data
                    )
                }
                is Resource.Error -> {
                    _stateProduct.value = ProductState(
                        error = result.message ?: "An ProductState error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}