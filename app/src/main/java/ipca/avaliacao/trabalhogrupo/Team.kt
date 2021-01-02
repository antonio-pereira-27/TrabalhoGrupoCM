package ipca.avaliacao.trabalhogrupo

import org.json.JSONObject

class Team {
    var codigo : Int ?= null
    var nome : String ?= null
    var logo : String ?= null
    var pais : String ?= null
    var nomeEstadio : String ?= null
    var cidade : String ?= null
    var capacidadeEstadio : Int ?= null


    constructor(){

    }

    constructor(
        codigo: Int?,
        nome: String?,
        logo: String?,
        pais: String?,
        nomeEstadio: String?,
        cidade: String?,
        capacidadeEstadio: Int?
    ) {
        this.codigo = codigo
        this.nome = nome
        this.logo = logo
        this.pais = pais
        this.nomeEstadio = nomeEstadio
        this.cidade = cidade
        this.capacidadeEstadio = capacidadeEstadio
    }

    companion object{
        fun fromJson(jsonObject: JSONObject):Team{
            var team = Team()

            team.codigo = jsonObject.getInt("team_id")
            team.nome = jsonObject.getString("name")
            team.pais = jsonObject.getString("country")
            team.nomeEstadio = jsonObject.getString("venue_name")
            team.cidade = jsonObject.getString("venue_city")
            team.capacidadeEstadio = jsonObject.getInt("venue_capacity")

            return team
        }
    }
}