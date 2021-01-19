package ipca.avaliacao.trabalhogrupo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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

class Practice : AppCompatActivity() {

    var players : MutableList<Players> = ArrayList()
    lateinit var playersAdapter : PlayersAdapter
    var counter = 0
    var isPractice = false
    var player = Players()
    lateinit var textViewPlayerName: TextView
    lateinit var textViewPlayerAge: TextView
    lateinit var textViewPlayerPosition: TextView
    lateinit var textViewPlayerScore: TextView
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        textViewPlayerName = findViewById<TextView>(R.id.textViewPlayerName)
        textViewPlayerAge = findViewById<TextView>(R.id.textViewPlayerAge)
        textViewPlayerPosition = findViewById<TextView>(R.id.textViewPlayerPosition)
        textViewPlayerScore = findViewById<TextView>(R.id.textViewScore)

        val listViewPlayers = findViewById<ListView>(R.id.listViewPratice)
        playersAdapter = PlayersAdapter()
        listViewPlayers.adapter = playersAdapter

        val preferencesHelper = PreferencesHelper(this)


        GlobalScope.async{
            var result = preferencesHelper.prefCodigo?.let { Beckend.getPlayers(it) }
            if (result == "Sem internet!") {
                runOnUiThread {
                    Toast.makeText(this@Practice, "Sem internet!", Toast.LENGTH_LONG).show()
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

    fun startTimeCounter(view: View){
        val countTime = findViewById<TextView>(R.id.textViewCountTime)

        object : CountDownTimer(10000, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = counter.toString()
                counter++
                isPractice = true
            }

            override fun onFinish() {
                countTime.text = "Treino Acabou!"
                isPractice = false
                player.pontuacao = player.pontuacao?.plus(5)
                players[i] = player
                textViewPlayerScore.text = player.pontuacao.toString()
                playersAdapter.notifyDataSetChanged()
            }
        }.start()
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

            if (isPractice == false)
            {
                rowView.isClickable = true
                rowView.setOnClickListener{
                    player.nome = players[position].nome
                    player.idade = players[position].idade
                    player.posicao = players[position].posicao
                    player.pontuacao = players[position].pontuacao

                    textViewPlayerName.text = player.nome
                    textViewPlayerAge.text = player.altura
                    textViewPlayerPosition.text = player.posicao
                    textViewPlayerScore.text = player.pontuacao.toString()

                    i = position

                }
            }
            return rowView
        }
    }
}