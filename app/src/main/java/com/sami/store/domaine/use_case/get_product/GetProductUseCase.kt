package com.sami.store.domaine.use_case.get_product

import com.sami.store.data.remote.dto.toProduct
import com.sami.store.domaine.model.Product
import com.sami.store.domaine.repository.ProductRepository
import com.sami.store.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(productId: Int): Flow<Resource<Product>> = flow {
        try {
            emit(Resource.Loading<Product>())
            val product = repository.getProductDetails(productId = productId).toProduct()
            emit(Resource.Success<Product>(product))
        } catch (e: HttpException) {
            emit(Resource.Error<Product>(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<Product>("Couldn't reach server. Check your internet connection."))
        }
    }
}