package ipca.avaliacao.trabalhogrupo

import android.content.Intent
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

class LeagueActivity : AppCompatActivity() {
    var teams : MutableList<Team> = ArrayList()
    lateinit var teamsAdapter : TeamsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

        val listViewTeams = findViewById<ListView>(R.id.listViewLeagueTeams)
        teamsAdapter = TeamsAdapter()
        listViewTeams.adapter = teamsAdapter



        GlobalScope.async{
            var result = Beckend.getTeams()
            if (result == "Sem internet!") {
                runOnUiThread {
                    Toast.makeText(this@LeagueActivity, "Sem internet!", Toast.LENGTH_LONG).show()
                }
            } else {
                val jsonObject = JSONObject(result)

                val jsonObjectResult : JSONObject = jsonObject.get("api") as JSONObject
                if (jsonObject.getJSONObject("api").getInt("results")!= 0) {
                    var jsonArray: JSONArray = jsonObjectResult.getJSONArray("teams")
                    for (index in 0 until jsonArray.length()) {
                        val jsonTeam = jsonArray.getJSONObject(index)
                        val team = Team.fromJson(jsonTeam)
                        teams.add(team)
                    }
                    runOnUiThread{
                        teamsAdapter.notifyDataSetChanged()
                    }
                }
            }

        }
    }

    inner class TeamsAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return teams.size
        }

        override fun getItem(position: Int): Any {
            return teams[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var contador = 1
            var auxiliar = 0

            val rowView = layoutInflater.inflate(R.layout.roll_league_teams, parent,false)

            val textViewTeamName = rowView.findViewById<TextView>(R.id.textViewLeagueTeamName)
            val textViewPosition = rowView.findViewById<TextView>(R.id.textViewPosition)
            val textViewGolosMarcados = rowView.findViewById<TextView>(R.id.textViewGolosMarcados)
            val textViewGolosSofridos = rowView.findViewById<TextView>(R.id.textViewGolosSofridos)
            val textViewPontos = rowView.findViewById<TextView>(R.id.textViewPontos)

            textViewTeamName.text = teams[position].nome
            textViewGolosMarcados.text = "0"
            textViewGolosSofridos.text = "0"
            textViewPontos.text = "0"
            textViewPosition.text = position.plus(1).toString()
            return rowView
        }
    }
}