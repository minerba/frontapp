package com.example.kakaoapp.data

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// ── 모델 ──────────────────────────────────────────────

data class CreditScore(
    val id: Int = 0,
    val agency: String,
    val score: Int,
    @SerializedName("updated_at") val updatedAt: String? = null
)

data class CreditHistoryItem(
    val id: Int = 0,
    val agency: String,
    val type: String,          // "loan" | "score"
    val year: String,
    val date: String,
    // loan 필드
    val title: String? = null,
    val description: String? = null,
    @SerializedName("has_card") val hasCard: Boolean = false,
    // score 필드
    @SerializedName("change_amount") val changeAmount: Int? = null,
    @SerializedName("score_from") val scoreFrom: Int? = null,
    @SerializedName("score_to") val scoreTo: Int? = null
)

data class UpdateScoreRequest(val agency: String, val score: Int)

// ── Retrofit 인터페이스 ────────────────────────────────

interface CreditApiService {
    @GET("api/credit-scores")
    suspend fun getScores(): Response<List<CreditScore>>

    @PUT("api/credit-scores")
    suspend fun updateScore(@Body body: UpdateScoreRequest): Response<CreditScore>

    @GET("api/credit-history")
    suspend fun getHistory(@Query("agency") agency: String): Response<List<CreditHistoryItem>>

    @POST("api/credit-history")
    suspend fun addHistory(@Body body: CreditHistoryItem): Response<CreditHistoryItem>

    @PUT("api/credit-history")
    suspend fun updateHistory(@Query("id") id: Int, @Body body: CreditHistoryItem): Response<CreditHistoryItem>

    @DELETE("api/credit-history")
    suspend fun deleteHistory(@Query("id") id: Int): Response<Map<String, Boolean>>
}

// ── Singleton ─────────────────────────────────────────

object ApiClient {
    private const val BASE_URL = "https://namyeonghwan.netlify.app/"

    val service: CreditApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CreditApiService::class.java)
    }
}
