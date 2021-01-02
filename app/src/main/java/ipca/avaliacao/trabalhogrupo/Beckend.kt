package ipca.avaliacao.trabalhogrupo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

class Beckend {
    companion object{
        suspend fun getTeams() : String = suspendCoroutine {
            var result = ""


            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/v2/teams/league/2826")
                .get()
                .addHeader("x-rapidapi-key", "f5d65a9065msh8bb619a81c4131cp18113ejsnd843ff142416")
                .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                .build()


            val response = client.newCall(request).execute()
            Log.e("teste", response.body()?.string().toString())
            val stream = response.body()?.string()

            if (stream != null) {
                result = stream
            }
            else {
                result = "Sem Internet!"
            }

            it.resume(result)
        }
    }
}