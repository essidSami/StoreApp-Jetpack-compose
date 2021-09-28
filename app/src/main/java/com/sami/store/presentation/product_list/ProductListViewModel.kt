package com.sami.store.presentation.product_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.store.domaine.model.Product
import com.sami.store.domaine.use_case.get_categories.GetCategoriesUseCase
import com.sami.store.domaine.use_case.get_products.GetProductsUseCase
import com.sami.store.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoryUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _stateProduct = mutableStateOf(ProductListState())
    val stateProduct: State<ProductListState> = _stateProduct
    private var cachedProductList = listOf<Product>()
    private var isSearchString = true
    private var isFilterCategory = true

    private val _stateCategory = mutableStateOf(CategoryListState())
    val stateCategory: State<CategoryListState> = _stateCategory

    init {
        getProductList()
        getCategoryList()
    }

    private fun getProductList() {
        getProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateProduct.value = ProductListState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _stateProduct.value = ProductListState(
                        products = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _stateProduct.value = ProductListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategoryList() {
        getCategoryUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateCategory.value = CategoryListState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _stateCategory.value = CategoryListState(
                        categories = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _stateCategory.value = CategoryListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchProduct(query: String) {
        val listToSearch = if(isSearchString){
            _stateProduct.value.products
        }else{
            cachedProductList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()){
                _stateProduct.value = ProductListState(
                    products = cachedProductList ?: emptyList())
                isSearchString = false
                return@launch
            }
            val results = listToSearch.filter {
                it.title.contains(query.trim(), ignoreCase = true) ||
                        it.description.contains(query.trim(), ignoreCase = true)
            }
            if (isSearchString){
                cachedProductList = _stateProduct.value.products
                isSearchString = false
            }
            _stateProduct.value = ProductListState(
                products = results ?: emptyList()
            )
        }
    }

    fun filterProduct(category: String) {
        val listToFilter = if(isFilterCategory){
            _stateProduct.value.products
        }else{
            cachedProductList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (category == "All"){
                _stateProduct.value = ProductListState(
                    products = cachedProductList ?: emptyList())
                isFilterCategory = false
                return@launch
            }
            val results = listToFilter.filter {
                it.category == category
            }
            if (isFilterCategory){
                cachedProductList = _stateProduct.value.products
                isFilterCategory = false
            }
            _stateProduct.value = ProductListState(
                products = results ?: emptyList()
            )
        }
    }

}