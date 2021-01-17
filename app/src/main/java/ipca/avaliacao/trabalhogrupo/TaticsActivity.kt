package ipca.avaliacao.trabalhogrupo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class TaticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tatics)

        val seekBarEstiloAtaque = findViewById<SeekBar>(R.id.seekBarEstiloAtaque)
        val textViewEstiloAtaque = findViewById<TextView>(R.id.textViewEstiloAtaque)
        val estilo1 = "Posse de bola"
        val estilo2 = "Contra-Ataque"
        val estilo3 = "Construção Rápida"
        val estilo4 = "Bola Longa"

        seekBarEstiloAtaque?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress >= 0 && progress <= 25){
                    textViewEstiloAtaque.text = estilo1
                }
                if (progress > 25 && progress <= 50){
                    textViewEstiloAtaque.text = estilo2
                }
                if (progress > 50 && progress <= 75){
                    textViewEstiloAtaque.text = estilo3
                }
                if (progress > 75 && progress <= 100){
                    textViewEstiloAtaque.text = estilo4
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@TaticsActivity,
                        "Estilo de Ataque",
                        Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@TaticsActivity,
                        "Stoped",
                        Toast.LENGTH_SHORT).show()
            }

        })

        val seekbarEstiloPressao = findViewById<SeekBar>(R.id.seekBarPressao)
        val textViewPressao = findViewById<TextView>(R.id.textViewPressao)
        val pressao1 = "Estacionar o Autocarro"
        val pressao2 = "Pressão Media-Baixa"
        val pressao3 = "Pressão Media"
        val pressao4 = "Pressao Media-Alta"

        seekbarEstiloPressao?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress >= 0 && progress <= 25){
                    textViewPressao.text = pressao1
                }
                if (progress > 25 && progress <= 50){
                    textViewPressao.text = pressao2
                }
                if (progress > 50 && progress <= 75){
                    textViewPressao.text = pressao3
                }
                if (progress > 75 && progress <= 100){
                    textViewPressao.text = pressao4
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@TaticsActivity,
                        "Estilo de Pressao",
                        Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@TaticsActivity,
                        "Stoped",
                        Toast.LENGTH_SHORT).show()
            }
        })

        val seekBarNivelAgressividade = findViewById<SeekBar>(R.id.seekBarNivelAgressividade)
        val textViewAgressividade = findViewById<TextView>(R.id.textViewAgressividade)
        val agressividade1 = "Cuidadoso"
        val agressividade2 = "Normal"
        val agressividade3 = "Agressivo"
        val agressividade4 = "Muito Agressivo"

        seekBarNivelAgressividade?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress >= 0 && progress <= 25){
                    textViewAgressividade.text = agressividade1
                }
                if (progress > 25 && progress <= 50){
                    textViewAgressividade.text = agressividade2
                }
                if (progress > 50 && progress <= 75){
                    textViewAgressividade.text = agressividade3
                }
                if (progress > 75 && progress <= 100){
                    textViewAgressividade.text = agressividade4
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@TaticsActivity,
                        "Nivel de Agressividade",
                        Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@TaticsActivity,
                        "Stoped",
                        Toast.LENGTH_SHORT).show()
            }
        })

        val switchMarcacao = findViewById<Switch>(R.id.switchMarcacao)
        val textViewMarcacao = findViewById<TextView>(R.id.textViewMaracacao)
        switchMarcacao?.setOnCheckedChangeListener({_, isChecked -> val message = if (isChecked) "Homem a Homem" else "Zona"
        textViewMarcacao.text = message})

    }
}