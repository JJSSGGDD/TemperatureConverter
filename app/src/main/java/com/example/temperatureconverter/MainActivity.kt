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
    private lateinit var resultTypeText: TextView

    //Input Type
    private lateinit var selectedUnit: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initilizing Views
        selectedUnitLayout = findViewById(R.id.selectType)
        selectedUnitText = findViewById(R.id.textResultType)
        editInput = findViewById(R.id.editInput)
        textResult = findViewById(R.id.textResultType)
        resultTypeText = findViewById(R.id.textResult)

        //by Default Fahrenheit is input unit
        selectedUnit = "Fahrenheit"

        //Setting alert Dialog to appear for slection of input until
        selectedUnitLayout.setOnClickListener() {
            showAlertDialog()
        }

        editInput.addTextChangedListener() {
            var resultText: String = ""
            var inputVal = editInput.text.toString()
            val df = DecimalFormat("#.##")

            if (inputVal.isNotEmpty()) {
                var doubleInput = inputVal.toDouble()
                if (selectedUnit == "Fahrenheit") {
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    selectedUnitText.text = "Celsius"
                } else {
                    //(0°C × 9/5) + 32
                    resultText = df.format((doubleInput * 9 / 5) + 32)
                    selectedUnitText.text = "Fahrenheit"
                }

                selectedUnitText.text = resultText

            }
        }
    }

    private fun showAlertDialog() {
        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Select Input Unit ") //   Setting title of alert dialog
        val items = arrayOf("Fahrenheit", "Celsius") // Options in alert Dialog
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
