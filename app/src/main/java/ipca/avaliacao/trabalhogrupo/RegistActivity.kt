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

class RegistActivity : AppCompatActivity() {

    // variáveis de classe
    private val TAG = "REGIST"
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)

        // a cada EditText e ao Button são introduzidas variáveis
        val newEmail = findViewById<EditText>(R.id.editTextNewEmail)
        val password = findViewById<EditText>(R.id.editTextNewPassword)
        val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)
        val buttonNewRegist = findViewById<Button>(R.id.buttonNewRegist)

        // autenticação com a firebase
        auth = Firebase.auth

        buttonNewRegist.setOnClickListener {
            if (password.text.toString() == confirmPassword.text.toString()) {
                auth.createUserWithEmailAndPassword(newEmail.text.toString(), password.text.toString()).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        Log.d(TAG, "newRegist with success")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Log.w(TAG, "newRegist failed", task.exception)
                        Toast.makeText(baseContext, "Cant Regist wiht this credentials.", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(baseContext, "Password must be the same.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}