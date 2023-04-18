package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityConstructorBinding
import kotlin.system.exitProcess

private lateinit var quetion_type_itog : String
private lateinit var qtypes : Array<TextView>
private lateinit var qeditors : Array<EditText>
private lateinit var delbts : Array<Button>
private lateinit var ansveditors : Array<EditText>
var quetions_counter = 0

class ConstructorAct : AppCompatActivity() {
    public var pref : SharedPreferences? = null
    private val TAG = "MyLog"
    private lateinit var binding: ActivityConstructorBinding
    private lateinit var text : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstructorBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_constructor)
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)

        qtypes = arrayOf(findViewById<TextView>(R.id.qtype1), findViewById<TextView>(R.id.qtype2), findViewById<TextView>(R.id.qtype3), findViewById<TextView>(R.id.qtype4), findViewById<TextView>(R.id.qtype5), findViewById<TextView>(R.id.qtype6), findViewById<TextView>(R.id.qtype7), findViewById<TextView>(R.id.qtype8), findViewById<TextView>(R.id.qtype9), findViewById<TextView>(R.id.qtype10), findViewById<TextView>(R.id.qtype11), findViewById<TextView>(R.id.qtype12), findViewById<TextView>(R.id.qtype13), findViewById<TextView>(R.id.qtype14), findViewById<TextView>(R.id.qtype15), findViewById<TextView>(R.id.qtype16), findViewById<TextView>(R.id.qtype17), findViewById<TextView>(R.id.qtype18), findViewById<TextView>(R.id.qtype19), findViewById<TextView>(R.id.qtype20), findViewById<TextView>(R.id.qtype21), findViewById<TextView>(R.id.qtype22), findViewById<TextView>(R.id.qtype23), findViewById<TextView>(R.id.qtype24), findViewById<TextView>(R.id.qtype25), findViewById<TextView>(R.id.qtype26), findViewById<TextView>(R.id.qtype27), findViewById<TextView>(R.id.qtype28), findViewById<TextView>(R.id.qtype29), findViewById<TextView>(R.id.qtype30))
        qeditors = arrayOf(findViewById<EditText>(R.id.qeditor1), findViewById<EditText>(R.id.qeditor2), findViewById<EditText>(R.id.qeditor3), findViewById<EditText>(R.id.qeditor4), findViewById<EditText>(R.id.qeditor5), findViewById<EditText>(R.id.qeditor6), findViewById<EditText>(R.id.qeditor7), findViewById<EditText>(R.id.qeditor8), findViewById<EditText>(R.id.qeditor9), findViewById<EditText>(R.id.qeditor10), findViewById<EditText>(R.id.qeditor11), findViewById<EditText>(R.id.qeditor12), findViewById<EditText>(R.id.qeditor13), findViewById<EditText>(R.id.qeditor14), findViewById<EditText>(R.id.qeditor15), findViewById<EditText>(R.id.qeditor16), findViewById<EditText>(R.id.qeditor17), findViewById<EditText>(R.id.qeditor18), findViewById<EditText>(R.id.qeditor19), findViewById<EditText>(R.id.qeditor20), findViewById<EditText>(R.id.qeditor21), findViewById<EditText>(R.id.qeditor22), findViewById<EditText>(R.id.qeditor23), findViewById<EditText>(R.id.qeditor24), findViewById<EditText>(R.id.qeditor25), findViewById<EditText>(R.id.qeditor26), findViewById<EditText>(R.id.qeditor27), findViewById<EditText>(R.id.qeditor28), findViewById<EditText>(R.id.qeditor29), findViewById<EditText>(R.id.qeditor30))
        delbts = arrayOf(findViewById<Button>(R.id.delbt1), findViewById<Button>(R.id.delbt2), findViewById<Button>(R.id.delbt3), findViewById<Button>(R.id.delbt4), findViewById<Button>(R.id.delbt5), findViewById<Button>(R.id.delbt6), findViewById<Button>(R.id.delbt7), findViewById<Button>(R.id.delbt8), findViewById<Button>(R.id.delbt9), findViewById<Button>(R.id.delbt10), findViewById<Button>(R.id.delbt11), findViewById<Button>(R.id.delbt12), findViewById<Button>(R.id.delbt13), findViewById<Button>(R.id.delbt14), findViewById<Button>(R.id.delbt15), findViewById<Button>(R.id.delbt16), findViewById<Button>(R.id.delbt17), findViewById<Button>(R.id.delbt18), findViewById<Button>(R.id.delbt19), findViewById<Button>(R.id.delbt20), findViewById<Button>(R.id.delbt21), findViewById<Button>(R.id.delbt22), findViewById<Button>(R.id.delbt23), findViewById<Button>(R.id.delbt24), findViewById<Button>(R.id.delbt25), findViewById<Button>(R.id.delbt26), findViewById<Button>(R.id.delbt27), findViewById<Button>(R.id.delbt28), findViewById<Button>(R.id.delbt29), findViewById<Button>(R.id.delbt30))
        ansveditors = arrayOf(findViewById<EditText>(R.id.ansveditor1), findViewById<EditText>(R.id.ansveditor2), findViewById<EditText>(R.id.ansveditor3), findViewById<EditText>(R.id.ansveditor4), findViewById<EditText>(R.id.ansveditor5), findViewById<EditText>(R.id.ansveditor6), findViewById<EditText>(R.id.ansveditor7), findViewById<EditText>(R.id.ansveditor8), findViewById<EditText>(R.id.ansveditor9), findViewById<EditText>(R.id.ansveditor10), findViewById<EditText>(R.id.ansveditor11), findViewById<EditText>(R.id.ansveditor12), findViewById<EditText>(R.id.ansveditor13), findViewById<EditText>(R.id.ansveditor14), findViewById<EditText>(R.id.ansveditor15), findViewById<EditText>(R.id.ansveditor16), findViewById<EditText>(R.id.ansveditor17), findViewById<EditText>(R.id.ansveditor18), findViewById<EditText>(R.id.ansveditor19), findViewById<EditText>(R.id.ansveditor20), findViewById<EditText>(R.id.ansveditor21), findViewById<EditText>(R.id.ansveditor22), findViewById<EditText>(R.id.ansveditor23), findViewById<EditText>(R.id.ansveditor24), findViewById<EditText>(R.id.ansveditor25), findViewById<EditText>(R.id.ansveditor26), findViewById<EditText>(R.id.ansveditor27), findViewById<EditText>(R.id.ansveditor28), findViewById<EditText>(R.id.ansveditor29), findViewById<EditText>(R.id.ansveditor30))

        val spinner: Spinner = findViewById(R.id.spinner)
        text = findViewById(R.id.textView)


        ArrayAdapter.createFromResource(
            this,
            R.array.questions_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val questions_types = resources.getStringArray(R.array.questions_types)
                    quetion_type_itog = questions_types[p2].toString()
                    Log.d(TAG, quetion_type_itog)
                    Toast.makeText(this@ConstructorAct, quetion_type_itog, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }


    fun newqBT(view: View){
        Log.d(TAG, "newqbt")
        if(quetions_counter == 29){
            Toast.makeText(this@ConstructorAct, R.string.error_max_quetions, Toast.LENGTH_SHORT).show()
        }
        else {
            qtypes[quetions_counter].visibility = View.VISIBLE
            val q = (quetions_counter + 1).toString() + ". " + quetion_type_itog
            qtypes[quetions_counter].text = q
            if (quetion_type_itog == "Несколько вариантов ответа") {
                ansveditors[quetions_counter].visibility = View.VISIBLE
            }
            qeditors[quetions_counter].visibility = View.VISIBLE
            quetions_counter += 1
            Log.d(TAG, quetions_counter.toString())
        }
        findViewById<Button>(R.id.savebt).visibility = View.VISIBLE
    }

    fun otmenabt(view: View) {
        if (quetions_counter != 0){
            quetions_counter -= 1
            Log.d(TAG, quetions_counter.toString())}
        else{findViewById<Button>(R.id.savebt).visibility = View.GONE}
        qtypes[quetions_counter].visibility = View.GONE
        ansveditors[quetions_counter].visibility = View.GONE
        ansveditors[quetions_counter].text.clear()
        qeditors[quetions_counter].visibility = View.GONE
        qeditors[quetions_counter].text.clear()
    }

    fun savebt(view: View) {
        var flag: Boolean = true
        if(findViewById<EditText>(R.id.AnketName).text.toString() == ""){
            Toast.makeText(this@ConstructorAct, R.string.name_error, Toast.LENGTH_SHORT).show()
            flag = false}
        for(i in 0..(quetions_counter - 1)){
            if(qeditors[i].text.toString() == ""){
                Toast.makeText(this@ConstructorAct, ("Некоректный вопрос номер " + (i + 1).toString()), Toast.LENGTH_SHORT).show()
                flag = false}
            if((ansveditors[i].visibility == View.VISIBLE) and (ansveditors[i].text.toString() == "")){
                    Toast.makeText(this@ConstructorAct, ("Некоректные варианты ответа в вопросе номер $i"), Toast.LENGTH_SHORT).show()
                    flag = false
            }
        }
    if (flag){
        val editor = pref?.edit()
        var ankets_names = pref?.getString("ankets names", "")
        var ankets_names1 = ankets_names!!.split("||").toTypedArray()
        ankets_names1 += ankets_names1.size.toString() + ". " + findViewById<EditText>(R.id.AnketName).text.toString()
        var ankets_names2 = ankets_names1.joinToString(separator = "||")
        editor?.putString("ankets names", ankets_names2)
        editor?.putInt(((ankets_names1.size - 1).toString() + "size"), quetions_counter)
        editor?.putString(((ankets_names1.size - 1).toString() + "name"), findViewById<EditText>(R.id.AnketName).text.toString())
        Log.d(TAG, (ankets_names1.size - 1).toString() + "name")
        editor?.putString(findViewById<EditText>(R.id.AnketName).text.toString(), "")


        for(i in 0..(quetions_counter - 1)){
            editor?.putString((ankets_names1.size - 1).toString() + i.toString(), qtypes[i].text.toString() + "||" + qeditors[i].text.toString() + "||" + ansveditors[i].text.toString())
            Log.d(TAG, pref?.getString((ankets_names1.size - 1).toString() + i.toString(), "0").toString())
            editor?.apply()}
        editor?.apply()
        Log.d(TAG, pref?.getString("ankets names", "0").toString())
        Log.d(TAG, pref?.getInt(((ankets_names1.size - 1).toString() + "size"), 0).toString())
        Toast.makeText(this@ConstructorAct, "Сохранено", Toast.LENGTH_SHORT).show()
        finish()

    }
    }

    override fun onStop() {
        super.onStop()
        for(i in 0..quetions_counter){
            if (quetions_counter != 0){
                quetions_counter -= 1
                Log.d(TAG, quetions_counter.toString())}
            else{findViewById<Button>(R.id.savebt).visibility = View.GONE}
            qtypes[quetions_counter].visibility = View.GONE
            ansveditors[quetions_counter].visibility = View.GONE
            ansveditors[quetions_counter].text.clear()
            qeditors[quetions_counter].visibility = View.GONE
            qeditors[quetions_counter].text.clear()
        }
    }

}

