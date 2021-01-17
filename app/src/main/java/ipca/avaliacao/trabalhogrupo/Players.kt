package ipca.avaliacao.trabalhogrupo

import org.json.JSONObject

class Players {
    var nome: String? = null
    var posicao: String? = null
    var pontuacao: Int? = null
    var idade: Int? = null
    var condFisica: Int? = null


    constructor(){
    }

    constructor(nome: String?, posicao: String?, pontuacao: Int?, idade: Int?, condFisica: Int?) {
        this.nome = nome
        this.posicao = posicao
        this.pontuacao = pontuacao
        this.idade = idade
        this.condFisica = condFisica
    }

    companion object{
        fun fromJson(jsonObject: JSONObject):Players{
            var player = Players()

            player.nome = jsonObject.getString("name")
            player.posicao = jsonObject.getString("country")
            player.pontuacao = jsonObject.getInt("")
            player.idade = jsonObject.getInt("")
            player.condFisica = jsonObject.getInt("")

            return player
        }
    }
}