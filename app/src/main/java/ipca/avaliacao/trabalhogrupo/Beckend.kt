package ipca.avaliacao.trabalhogrupo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Beckend {
    companion object{
        suspend fun getTeams() : String = suspendCoroutine {
            var result = ""
            try{
                val urlc : HttpURLConnection = URL("https://github.com/openfootball/football.json/blob/master/2020-21/pt.1.clubs.json").openConnection() as HttpURLConnection
                urlc.setRequestProperty("User-Agent", "Test")
                urlc.setRequestProperty("Connection", "close")
                urlc.setConnectTimeout(1500)
                urlc.connect()
                val stream = urlc.inputStream
                val isReader = InputStreamReader(stream)
                val brin = BufferedReader(isReader)
                var str: String = ""

                var keepReading = true
                while (keepReading) {
                    var line = brin.readLine()
                    if (line == null) {
                        keepReading = false
                    } else {
                        str += line
                    }
                }
                brin.close()
                result = str
            }catch (e: Exception) {
                e.printStackTrace()
                result = "Sem internet!"
            }
            it.resume(result)
        }

        fun getBitmapFromUr(urlString: String, onBitmapResult: (Bitmap) -> Unit) {
            object : AsyncTask<Unit, Unit, Bitmap>() {
                override fun doInBackground(vararg p0: Unit?): Bitmap {
                    val input = URL(urlString).openStream()
                    var bmp = BitmapFactory.decodeStream(input)
                    return bmp
                }

                override fun onPostExecute(result: Bitmap?) {
                    super.onPostExecute(result)
                    result?.let {
                        onBitmapResult.invoke(result)
                    }

                }

            }.execute(null, null, null)
        }
    }
}