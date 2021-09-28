package com.sami.store.di

import android.app.Application
import androidx.room.Room
import com.sami.store.data.local.StoreDatabase
import com.sami.store.data.local.dao.ProductDao
import com.sami.store.data.remote.StoreApi
import com.sami.store.data.repository.ProductRepositoryImp
import com.sami.store.domaine.repository.ProductRepository
import com.sami.store.domaine.use_case.get_cart.AddProduct
import com.sami.store.domaine.use_case.get_cart.CartUseCases
import com.sami.store.domaine.use_case.get_cart.DeleteProduct
import com.sami.store.domaine.use_case.get_cart.GetProducts
import com.sami.store.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getProvideStoreApi(): StoreApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StoreApi::class.java)

    @Provides
    @Singleton
    fun provideStoreDatabase(app: Application): StoreDatabase {
        return Room.databaseBuilder(
            app,
            StoreDatabase::class.java,
            StoreDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun getProvideRepository(api: StoreApi, db: StoreDatabase): ProductRepository =
        ProductRepositoryImp(api, db.productDao)

    @Provides
    @Singleton
    fun provideCartUseCases(repository: ProductRepository): CartUseCases {
        return CartUseCases(
            getProducts = GetProducts(repository),
            deleteProduct = DeleteProduct(repository),
            addProduct = AddProduct(repository)
        )
    }
}