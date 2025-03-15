import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DespesasActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_despesas)

        recyclerView = findViewById(R.id.recyclerViewDespesas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ConfiguraÇÃo do Logging Interceptor
        val logging = HttpLoggingInterceptor { message ->
            Log.d("OkHttp", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // ConfiguraÇÃo do OkHttpClient com o interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        // Configuração do Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.135.111.20/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val apiService2 = retrofit.create(ApiService::class.java)
        apiService2.getDespesas().enqueue(object : Callback<List<Despesa_Receita>> {
            override fun onResponse(call: Call<List<Despesa_Receita>>, response: Response<List<Despesa_Receita>>) {
                if (response.isSuccessful) {
                    val despesas = response.body() ?: emptyList()
                    adapter = CustomAdapter(despesas)
                    recyclerView.adapter = adapter
                } else {
                    Log.e("API Error", "Response not successful. Code: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<Despesa_Receita>>, t: Throwable) {
                Log.e("API Failure", "Error fetching products", t)
            }
        })
    }
}
