package com.example.androidproject02.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject02.network.Product
import com.example.androidproject02.network.SalesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "ProductListViewModel"

class ProductListViewModel: ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    init {
        getProducts()
    }

    fun refreshProducts() {
        getProducts()
    }

    private fun getProducts() {
        Log.i(TAG, "Preparing to request products list")
        coroutineScope.launch {
            val getProductsDeferred = SalesApi.retrofitService.getProducts()
            Log.i(TAG, "Loading products")

            val productsList = getProductsDeferred.await()
            _products.value = productsList
            Log.i(TAG, "Number of products ${productsList.size}")
        }
        Log.i(TAG, "Products list requested")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}