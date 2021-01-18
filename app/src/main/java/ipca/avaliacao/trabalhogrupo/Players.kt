package ipca.avaliacao.trabalhogrupo

import org.json.JSONObject

class Players {
    var nome: String? = null
    var posicao: String? = null
    var pontuacao: Int? = null
    var idade: Int? = null
    var altura: String? = null
    var peso: String? = null

    constructor(){
    }

    constructor(nome: String?, posicao: String?, pontuacao: Int?, idade: Int?, altura: String?, peso: String?) {
        this.nome = nome
        this.posicao = posicao
        this.pontuacao = pontuacao
        this.idade = idade
        this.altura = altura
        this.peso = peso
    }

    companion object{
        fun fromJson(jsonObject: JSONObject):Players{
            var player = Players()

            player.nome = jsonObject.getString("player_name")
            player.posicao = jsonObject.getString("position")
            player.idade = jsonObject.getInt("age")
            player.altura = jsonObject.getString("height")
            player.peso = jsonObject.getString("weight")

            return player
        }
    }
}