package net.sharetrip.gnrads.apiinterface

import net.sharetrip.gnrads.model.AdRequest
import net.sharetrip.gnrads.model.AdResponse
import net.sharetrip.gnrads.network.GenericResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AdEndpoint {
    @POST("ad")
    suspend fun fetchAds(
        @Body adRequest: AdRequest
    ): GenericResponse<AdResponse>
}