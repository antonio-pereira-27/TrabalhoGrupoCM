package ipca.avaliacao.trabalhogrupo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONArray
import org.json.JSONObject

class Squad : AppCompatActivity() {

    var players : MutableList<Players> = ArrayList()
    lateinit var playersAdapter : PlayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squad)


        val listViewPlayers = findViewById<ListView>(R.id.listView_Players)
        playersAdapter = PlayersAdapter()
        listViewPlayers.adapter = playersAdapter

        val preferencesHelper = PreferencesHelper(this)


        GlobalScope.async{
            var result = preferencesHelper.prefCodigo?.let { Beckend.getPlayers(it) }
            if (result == "Sem internet!") {
                runOnUiThread {
                    Toast.makeText(this@Squad, "Sem internet!", Toast.LENGTH_LONG).show()
                }
            } else {
                val jsonObject = JSONObject(result)

                val jsonObjectResult : JSONObject = jsonObject.get("api") as JSONObject
                if (jsonObject.getJSONObject("api").getInt("results")!= 0) {
                    var jsonArray: JSONArray = jsonObjectResult.getJSONArray("players")
                    for (index in 0 until jsonArray.length()) {
                        val jsonPlayers = jsonArray.getJSONObject(index)
                        val player = Players.fromJson(jsonPlayers)
                        players.add(player)
                    }
                    runOnUiThread{
                        playersAdapter.notifyDataSetChanged()
                    }
                }
            }

        }
    }

    inner class PlayersAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return players.size
        }

        override fun getItem(position: Int): Any {
            return players[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.roll_players, parent,false)

            val textViewPlayerNome = rowView.findViewById<TextView>(R.id.textViewPlayerNome)
            val textViewPlayerIdade = rowView.findViewById<TextView>(R.id.textViewPlayerIdade)
            val textViewPlayerPosicao = rowView.findViewById<TextView>(R.id.textViewPlayerPosicao)
            val textViewPlayerAltura = rowView.findViewById<TextView>(R.id.textViewPlayerAltura)
            val textViewPlayerPeso = rowView.findViewById<TextView>(R.id.textViewPlayerPeso)


            textViewPlayerNome.text = players[position].nome
            textViewPlayerPosicao.text = players[position].posicao
            textViewPlayerIdade.text = players[position].idade.toString()
            textViewPlayerAltura.text = players[position].altura
            textViewPlayerPeso.text = players[position].peso

            return rowView
        }
    }
}