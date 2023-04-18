package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityOprosnikBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class Oprosnik : AppCompatActivity() {

    var selectedChip = ""
    var selectedRadioButton = ""
    var anket_size = 0
    private lateinit var qtext_ansvs : Array<EditText>

    private lateinit var binding: ActivityOprosnikBinding
    public var pref : SharedPreferences? = null
    private val TAG = "MyLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOprosnikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)

        val qtextqs = arrayOf(binding.qtextq1, binding.qtextq2, binding.qtextq3, binding.qtextq4, binding.qtextq5, binding.qtextq6, binding.qtextq7, binding.qtextq8, binding.qtextq9, binding.qtextq10, binding.qtextq11, binding.qtextq12, binding.qtextq13, binding.qtextq14, binding.qtextq15, binding.qtextq16, binding.qtextq17, binding.qtextq18, binding.qtextq19, binding.qtextq20, binding.qtextq21, binding.qtextq22, binding.qtextq23, binding.qtextq24, binding.qtextq25, binding.qtextq26, binding.qtextq27, binding.qtextq28, binding.qtextq29, binding.qtextq30)
        qtext_ansvs = arrayOf(findViewById<EditText>(R.id.qtext_ansv1), findViewById<EditText>(R.id.qtext_ansv2), findViewById<EditText>(R.id.qtext_ansv3), findViewById<EditText>(R.id.qtext_ansv4), findViewById<EditText>(R.id.qtext_ansv5), findViewById<EditText>(R.id.qtext_ansv6), findViewById<EditText>(R.id.qtext_ansv7), findViewById<EditText>(R.id.qtext_ansv8), findViewById<EditText>(R.id.qtext_ansv9), findViewById<EditText>(R.id.qtext_ansv10), findViewById<EditText>(R.id.qtext_ansv11), findViewById<EditText>(R.id.qtext_ansv12), findViewById<EditText>(R.id.qtext_ansv13), findViewById<EditText>(R.id.qtext_ansv14), findViewById<EditText>(R.id.qtext_ansv15), findViewById<EditText>(R.id.qtext_ansv16), findViewById<EditText>(R.id.qtext_ansv17), findViewById<EditText>(R.id.qtext_ansv18), findViewById<EditText>(R.id.qtext_ansv19), findViewById<EditText>(R.id.qtext_ansv20), findViewById<EditText>(R.id.qtext_ansv21), findViewById<EditText>(R.id.qtext_ansv22), findViewById<EditText>(R.id.qtext_ansv23), findViewById<EditText>(R.id.qtext_ansv24), findViewById<EditText>(R.id.qtext_ansv25), findViewById<EditText>(R.id.qtext_ansv26), findViewById<EditText>(R.id.qtext_ansv27), findViewById<EditText>(R.id.qtext_ansv28), findViewById<EditText>(R.id.qtext_ansv29), findViewById<EditText>(R.id.qtext_ansv30))
        val radioGroups = arrayOf(findViewById<RadioGroup>(R.id.radioGroup1), findViewById<RadioGroup>(R.id.radioGroup2), findViewById<RadioGroup>(R.id.radioGroup3), findViewById<RadioGroup>(R.id.radioGroup4), findViewById<RadioGroup>(R.id.radioGroup5), findViewById<RadioGroup>(R.id.radioGroup6), findViewById<RadioGroup>(R.id.radioGroup7), findViewById<RadioGroup>(R.id.radioGroup8), findViewById<RadioGroup>(R.id.radioGroup9), findViewById<RadioGroup>(R.id.radioGroup10), findViewById<RadioGroup>(R.id.radioGroup11), findViewById<RadioGroup>(R.id.radioGroup12), findViewById<RadioGroup>(R.id.radioGroup13), findViewById<RadioGroup>(R.id.radioGroup14), findViewById<RadioGroup>(R.id.radioGroup15), findViewById<RadioGroup>(R.id.radioGroup16), findViewById<RadioGroup>(R.id.radioGroup17), findViewById<RadioGroup>(R.id.radioGroup18), findViewById<RadioGroup>(R.id.radioGroup19), findViewById<RadioGroup>(R.id.radioGroup20), findViewById<RadioGroup>(R.id.radioGroup21), findViewById<RadioGroup>(R.id.radioGroup22), findViewById<RadioGroup>(R.id.radioGroup23), findViewById<RadioGroup>(R.id.radioGroup24), findViewById<RadioGroup>(R.id.radioGroup25), findViewById<RadioGroup>(R.id.radioGroup26), findViewById<RadioGroup>(R.id.radioGroup27), findViewById<RadioGroup>(R.id.radioGroup28), findViewById<RadioGroup>(R.id.radioGroup29), findViewById<RadioGroup>(R.id.radioGroup30))
        var chipGroups = arrayOf(findViewById<ChipGroup>(R.id.chipGroup1), findViewById<ChipGroup>(R.id.chipGroup2), findViewById<ChipGroup>(R.id.chipGroup3), findViewById<ChipGroup>(R.id.chipGroup4), findViewById<ChipGroup>(R.id.chipGroup5), findViewById<ChipGroup>(R.id.chipGroup6), findViewById<ChipGroup>(R.id.chipGroup7), findViewById<ChipGroup>(R.id.chipGroup8), findViewById<ChipGroup>(R.id.chipGroup9), findViewById<ChipGroup>(R.id.chipGroup10), findViewById<ChipGroup>(R.id.chipGroup11), findViewById<ChipGroup>(R.id.chipGroup12), findViewById<ChipGroup>(R.id.chipGroup13), findViewById<ChipGroup>(R.id.chipGroup14), findViewById<ChipGroup>(R.id.chipGroup15), findViewById<ChipGroup>(R.id.chipGroup16), findViewById<ChipGroup>(R.id.chipGroup17), findViewById<ChipGroup>(R.id.chipGroup18), findViewById<ChipGroup>(R.id.chipGroup19), findViewById<ChipGroup>(R.id.chipGroup20), findViewById<ChipGroup>(R.id.chipGroup21), findViewById<ChipGroup>(R.id.chipGroup22), findViewById<ChipGroup>(R.id.chipGroup23), findViewById<ChipGroup>(R.id.chipGroup24), findViewById<ChipGroup>(R.id.chipGroup25), findViewById<ChipGroup>(R.id.chipGroup26), findViewById<ChipGroup>(R.id.chipGroup27), findViewById<ChipGroup>(R.id.chipGroup28), findViewById<ChipGroup>(R.id.chipGroup29), findViewById<ChipGroup>(R.id.chipGroup30),)


        val anket_number = pref?.getInt("opened_anket", 0)
        anket_size = pref?.getInt(anket_number.toString() + "size", 0)!!
        binding.anketName.text = pref?.getString(anket_number.toString() + "name", "?")
        Log.d(TAG, anket_size.toString())

        for (i in 0..anket_size!! - 1){
            val data = pref?.getString(anket_number.toString() + i.toString(), "?")?.split("||")?.toTypedArray()
            Log.d(TAG, pref?.getString(anket_number.toString() + i.toString(), "?").toString())
            qtextqs[i].visibility = View.VISIBLE
            qtextqs[i].text = data?.get(1)

            if("Текст" in data!![0]){
                qtext_ansvs[i].visibility = View.VISIBLE
            }
            else if ("Число" in data!![0]){
                qtext_ansvs[i].visibility = View.VISIBLE
                qtext_ansvs[i].inputType = InputType.TYPE_CLASS_NUMBER
            }
            else if ("Да" in data!![0]){
                Log.d(TAG, "Да нет")
                radioGroups[i].visibility = View.VISIBLE
                val radioGroup = radioGroups[i]
                val strings = listOf("Да", "Нет")
                strings.forEach {
                    val radioButton = RadioButton(this).apply {
                        text = it
                        isClickable = true
                        setOnCheckedChangeListener { radioButton, isChecked ->
                            if (isChecked) {
                                selectedRadioButton = radioButton.text.toString()
                                qtext_ansvs[i].setText(selectedRadioButton)
                            }
                        }
                    }
                    radioGroup.addView(radioButton)
                }

            }
            else if ("Несколько" in data!![0]){
                Log.d(TAG, "Несколько")
                chipGroups[i].visibility = View.VISIBLE
                var chipGroup = chipGroups[i]
                var strings = data[2].split(";").toTypedArray()
                strings.forEach {
                    val chip = Chip(this).apply {
                        text = it
                        isCheckable = true
                        isClickable = true
                        setOnCheckedChangeListener { chip, isChecked ->
                            if (isChecked) {
                                selectedChip += chip.text.toString() + ", "
                                qtext_ansvs[i].setText(selectedChip)
                            } else {
                                selectedChip = selectedChip.replace(chip.text.toString() + ", ", "")
                                qtext_ansvs[i].setText(selectedChip)
                            }
                        }
                    }
                    chipGroup.addView(chip)
                }
            }

        }

    }

    fun save_ansvers(view: View) {
        Log.d(TAG, "OK")
        var flag: Boolean = true
        for (i in 0..anket_size!! - 1){
            Log.i(TAG, qtext_ansvs[i].text.toString())
            if(qtext_ansvs[i].text.toString() == ""){
                flag = false
                Toast.makeText(this@Oprosnik, "Ошибка: пустой ответ в вопрсе номер" + i.toString(), Toast.LENGTH_SHORT).show()}
        }
        if (flag){
            var res : Array<String> = arrayOf()
            for (i in 0..anket_size!! - 1){res += qtext_ansvs[i].text.toString()}

            pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
            val editor = pref?.edit()
            val ankname = binding.anketName.text.toString()
            if (pref?.getString(ankname, "") == ""){editor?.putString(ankname, res.joinToString(separator = "||")) }
            else{
                val x = pref?.getString(ankname, "")
                val y = res.joinToString(separator = "||")
                val xy : Array<String> = arrayOf(x.toString(), y)
                val z = xy.joinToString(separator = "||")
                editor?.putString(ankname, z)
            }
            editor?.apply()
            Log.d(TAG, pref?.getString(ankname, "").toString())
        }

    }


}