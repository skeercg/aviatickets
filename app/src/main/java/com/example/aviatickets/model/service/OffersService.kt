package com.example.aviatickets.model.service

import android.util.Log
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import retrofit2.awaitResponse

object OffersService {
    private val offersApi = ApiClient.offersApi

    suspend fun getAllOffers(): MutableList<Offer> {

        val offers: MutableList<Offer> = try {
            withContext(Dispatchers.IO) {
                val response = offersApi.getAllOffers().awaitResponse()

                val rawJson = response.body()
                Log.d("STATE", "Raw JSON response: $rawJson")

                response.body() ?: mutableListOf()
            }
        } catch (e: Exception) {
            mutableListOf()
        }

        return offers
    }
}