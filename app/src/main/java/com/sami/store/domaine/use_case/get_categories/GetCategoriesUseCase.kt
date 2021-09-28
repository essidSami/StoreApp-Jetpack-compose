package com.sami.store.domaine.use_case.get_categories

import com.sami.store.domaine.repository.ProductRepository
import com.sami.store.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading<List<String>>())
            val category = repository.getCategories()
            emit(Resource.Success<List<String>>(category))
        }catch (e: HttpException){
            emit(Resource.Error<List<String>>(e.localizedMessage ?: "An unexpected error occurred."))
        }catch (e: IOException){
            emit(Resource.Error<List<String>>("Couldn't reach server. Check your internet connection."))
        }
    }
}