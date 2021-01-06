package ipca.avaliacao.trabalhogrupo

class PreferencesHelper {
    companion object{
        const val PREF_EMAIL = "email";
        const val PREF_TEAM_CHOOSE = "team_choose"
        const val PREF_TEAM = "team"
    }

    var prefEmail : String? = null
    var prefChooseTeam : Boolean = false
    var prefTeam : String? = null

    var hasChooseTeam : Boolean
        get() = prefChooseTeam
        set(value) {
                prefChooseTeam = value
        }


}