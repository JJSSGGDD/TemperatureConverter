package com.example.temperatureconverter

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    //Declaring Views
    private lateinit var selectedUnitLayout: LinearLayout
    private lateinit var selectedUnitText: TextView
    private lateinit var editInput: EditText
    private lateinit var textResult: TextView
    private lateinit var textResultType: TextView
    private lateinit var textResult2: TextView
    private lateinit var textResultType2: TextView

    //Input Type
    private lateinit var selectedUnit: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initilizing Views
        selectedUnitLayout = findViewById(R.id.selectType)
        selectedUnitText = findViewById(R.id.textSelect)
        editInput = findViewById(R.id.editInput)
        textResult = findViewById(R.id.textResult)
        textResultType = findViewById(R.id.textResultType)
        textResult2 = findViewById(R.id.textResult2)
        textResultType2 = findViewById(R.id.textResultType2)

        //by Default Fahrenheit is input unit
        selectedUnit = "Fahrenheit"

        //Setting alert Dialog to appear for slection of input until
        selectedUnitLayout.setOnClickListener() {
            showAlertDialog()
        }

        editInput.addTextChangedListener() {
            var resultText: String = ""
            var resultText2: String = ""
            var inputVal = editInput.text.toString()

            val df = DecimalFormat("#.##")

            if (inputVal.isNotEmpty()) {
                var doubleInput = inputVal.toDouble() // To convert string to double
                //Taking decision as per current Input type
                if (selectedUnit == "Fahrenheit") {
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    textResultType.text = "Celsius"

                    resultText2 = df.format(((doubleInput - 32) * 5 / 9) + 273.15)
                    textResultType2.text = "Kelvin"


                } else if (selectedUnit == "Kelvin") {
                    // Convertir a kelvin
                    resultText = df.format(((doubleInput - 273.15) * 9 / 5) + 32)
                    textResultType.text = "Fahrenheit"

                    resultText2 = df.format((doubleInput - 273.15))
                    textResultType2.text = "Celsius"

                } else  {
                    //(0°C × 9/5) + 32
                    resultText = df.format((doubleInput * 9 / 5) + 32)
                    textResultType.text = "Fahrenheit"

                    resultText2 = df.format((doubleInput + 273.15))
                    textResultType2.text = "Kelvin"
                }

                textResult.text = resultText
                textResult2.text = resultText2
            }
        }
    }

    private fun showAlertDialog() {
        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Select Input Unit ") //   Setting title of alert dialog
        val items = arrayOf("Fahrenheit", "Celsius", "Kelvin") // Options in alert Dialog
        val checkedItem = -1 //No item by default selected

        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener() { dialog, which ->
                selectedUnit = items[which] // which user has selected
                selectedUnitText.setText(selectedUnit)
            })

        alertDialog.setPositiveButton(android.R.string.ok,
            DialogInterface.OnClickListener() { dialog, which ->
                dialog.dismiss()
            })

        val alert: AlertDialog = alertDialog.create()
        alert
        alertDialog.show()

    }
}
