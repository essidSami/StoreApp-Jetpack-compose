package com.sami.store.domaine.use_case.get_cart

data class CartUseCases (
    val getProducts: GetProducts,
    val deleteProduct: DeleteProduct,
    val addProduct: AddProduct
        )