package ipca.avaliacao.trabalhogrupo

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

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

    var hasEmail : String?
        get() = prefEmail
        set(value) {
            prefEmail = value
        }

    var hasTeam : String?
        get() = prefTeam
        set(value) {
            prefTeam = value
        }

    var sharedPreferences : SharedPreferences

    constructor(context: Context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        this.prefEmail = sharedPreferences.getString(PREF_EMAIL, "")
        this.prefTeam = sharedPreferences.getString(PREF_TEAM, "")
        this.prefChooseTeam = sharedPreferences.getBoolean(PREF_TEAM_CHOOSE, false)
    }




    fun savePreferences(){
        val editor = sharedPreferences.edit()
        editor.putString(PREF_EMAIL, prefEmail)
        editor.putString(PREF_TEAM, prefTeam)
        editor.putBoolean(PREF_TEAM_CHOOSE, prefChooseTeam)
        editor.apply()
    }
}