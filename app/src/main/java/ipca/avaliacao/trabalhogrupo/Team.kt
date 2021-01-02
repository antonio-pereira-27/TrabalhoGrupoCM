package ipca.avaliacao.trabalhogrupo

import org.json.JSONObject

class Team {
    var name : String? = null
    var country : String? = null

    constructor(name: String?, country: String?)
    {
        this.name = name
        this.country = country
    }

    constructor(){

    }

    companion object{
        fun fromJson(jsonObject: JSONObject):Team{
            var team = Team()
            team.name = jsonObject.getString("name")
            team.country = jsonObject.getString("country")
            return team
        }
    }
}