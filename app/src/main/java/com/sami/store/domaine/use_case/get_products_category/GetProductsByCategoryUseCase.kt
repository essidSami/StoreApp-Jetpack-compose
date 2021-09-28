package com.sami.store.domaine.use_case.get_products_category

import com.sami.store.data.remote.dto.toProduct
import com.sami.store.domaine.model.Product
import com.sami.store.domaine.repository.ProductRepository
import com.sami.store.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(category: String): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
            val productList =
                repository.getProductsByCategory(category = category).map { it.toProduct() }
            emit(Resource.Success<List<Product>>(productList))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Product>>(e.localizedMessage ?: ""))
        } catch (e: IOException) {
            emit(Resource.Error<List<Product>>(""))
        }
    }
}