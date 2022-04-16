package cr.ac.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    var texto : TextView ?= null

    var canDecimal = true
    var canPercentage = true
    var canNumber = true

    //val opreaciones = mapOf("Suma" to "+", "Resta" to "-", "Multiplica" to "x", "Divide" to "/")

    val operaciones = listOf("+", "-", "x", "/", ",")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = findViewById(R.id.textoPrincipal)


        // Buttons from 0 to 9
        val buttonCero : Button = findViewById(R.id.buttonCero)
        buttonCero.setOnClickListener{ numberClick( buttonCero.text ) }

        val buttonUno : Button = findViewById(R.id.buttonUno)
        buttonUno.setOnClickListener{ numberClick( buttonUno.text ) }

        val buttonDos : Button = findViewById(R.id.buttonDos)
        buttonDos.setOnClickListener{ numberClick( buttonDos.text ) }

        val buttonTres : Button = findViewById(R.id.buttonTres)
        buttonTres.setOnClickListener{ numberClick( buttonTres.text ) }

        val buttonCuatro : Button = findViewById(R.id.buttonCuatro)
        buttonCuatro.setOnClickListener{ numberClick( buttonCuatro.text ) }

        val buttonCinco : Button = findViewById(R.id.buttonCinco)
        buttonCinco.setOnClickListener{ numberClick( buttonCinco.text ) }

        val buttonSeis : Button = findViewById(R.id.buttonSeis)
        buttonSeis.setOnClickListener{ numberClick( buttonSeis.text ) }

        val buttonSiete : Button = findViewById(R.id.buttonSiete)
        buttonSiete.setOnClickListener{ numberClick( buttonSiete.text ) }

        val buttonOcho : Button = findViewById(R.id.buttonOcho)
        buttonOcho.setOnClickListener{ numberClick( buttonOcho.text ) }

        val buttonNueve : Button = findViewById(R.id.buttonNueve)
        buttonNueve.setOnClickListener{ numberClick( buttonNueve.text ) }
        // End of buttons from 0 to 9

        // Clear Button
        val buttonC : Button = findViewById(R.id.buttonC)
        buttonC.setOnClickListener{ clearClick () }

        // Sum Button
        val buttonSuma : Button = findViewById(R.id.buttonSuma)
        buttonSuma.setOnClickListener{ operationClick ( buttonSuma.text ) }

        // Resta Button
        val buttonResta : Button = findViewById(R.id.buttonResta)
        buttonResta.setOnClickListener{ operationClick ( buttonResta.text ) }

        // Multiply Button
        val buttonMutiplica : Button = findViewById(R.id.buttonMultiplica)
        buttonMutiplica.setOnClickListener{ operationClick ( buttonMutiplica.text ) }

        // Divide Button
        val buttonDivide : Button = findViewById(R.id.buttonDivide)
        buttonDivide.setOnClickListener{ operationClick ( buttonDivide.text ) }

        // Result Button
        val buttonResultado : Button = findViewById(R.id.buttonResultado)
        buttonResultado.setOnClickListener{ resultClick ( buttonResultado.text ) }

        // Decimal Button
        val buttonDecimal : Button = findViewById(R.id.buttonDecimal)
        buttonDecimal.setOnClickListener{ decimalClick ( buttonDecimal.text ) }

        // Percentage Button
        val buttonPorcentaje : Button = findViewById(R.id.buttonPorcentaje)
        buttonPorcentaje.setOnClickListener{ porcentajeClick ( buttonPorcentaje.text ) }

        // Sign Button
        val buttonSigno : Button = findViewById(R.id.buttonSigno)
        buttonSigno.setOnClickListener{ signoClick () }


        val expression = Expression("2+2")
        println("*********************" + expression.calculate())

        println(operaciones)
    }

    private fun resultClick(dato: CharSequence) {
        var operacion = texto?.text.toString()
        operacion = operacion.replace("x", "*")
        operacion = operacion.replace(",", ".")

        val expression = Expression(operacion)
        texto?.text = expression.calculate().toString()
    }

    private fun numberClick(dato : CharSequence) {
        if (canNumber) {
            if (texto?.text.toString() == "0") {
                texto?.text = dato
            } else {
                texto?.append(dato)
            }
        }
    }

    private fun clearClick() {
        texto?.text = "0"
        canNumber = true
        canPercentage = true
        canDecimal = true
    }

    private fun operationClick(simbolo : CharSequence) {
        if (!lastIsSimbol()) {
            texto?.append(simbolo)
            canDecimal = true
            canPercentage = true
        }
        else {
            texto?.text = texto?.text!!.dropLast(1)
            texto?.append(simbolo)
            canDecimal = true
            canPercentage = true
        }
    }

    private fun decimalClick(text: CharSequence?) {
        if (canDecimal && !lastIsSimbol()) {
            texto?.append(",")
            canDecimal = false
        }
    }

    private fun porcentajeClick(simbolo: CharSequence?) {
        if (canPercentage && !lastIsSimbol()) {
            texto?.append("%")
            canPercentage = false
            canNumber = false
            canDecimal = false
        }
    }

    private fun signoClick() {
        if (texto?.text!!.get(0).toString() != "-") {
            texto?.text = "-" + texto?.text
        }
        else {
            texto?.text = texto?.text!!.drop(1)
        }
    }

    private fun lastIsSimbol() : Boolean {
        if (texto?.text!!.takeLast(1).toString() in this.operaciones) {
            return true
        }
        return false
    }
}