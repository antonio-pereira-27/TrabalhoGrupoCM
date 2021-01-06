package ipca.avaliacao.trabalhogrupo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    // variáveis
    private val TAG =  "LOGIN"
    private lateinit var authentication: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // variáveis
        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val login = findViewById<Button>(R.id.button_activityLogin)

        authentication = Firebase.auth

        login.setOnClickListener{
            authentication.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this){
                task ->
                    if (task.isSuccessful)
                    {
                        // login feito com sucesso, atualiza o UI da aplicação com o utilizador que se autenticou
                        Log.d(TAG, "Login with email: Successful")

                        // guarda o email do utilizador
                        val preferencesHelper = PreferencesHelper(this@LoginActivity)
                        preferencesHelper.prefEmail = email.text.toString()
                        preferencesHelper.savePreferences()

                        // verifica se tem ou não equipa
                        if (preferencesHelper.prefTeam == ""){
                            val intent = Intent(this, ChooseTeamActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            val intent = Intent(this, MainMenu::class.java)
                            startActivity(intent)
                        }

                    } else {
                        // o login deu erro, é necessário atirar uma exceção
                        Log.w(TAG, " Login with email : Fail", task.exception)
                        Toast.makeText(baseContext, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}