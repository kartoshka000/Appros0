package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.util.CellUtil.createCell
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    public var pref : SharedPreferences? = null
    private val TAG = "MyLog"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
    }

    fun new_anket_bt(view: View) {
        val constructorAct = Intent(this, ConstructorAct::class.java)
        startActivity(constructorAct)
        val testAct = Intent(this, testact::class.java)
        //startActivity(testAct)
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        var ankets_names = pref?.getString("ankets names", "")
        var ankets_names1 = ankets_names!!.split("||").toTypedArray()
        val anks = arrayOf(binding.ank1, binding.ank2, binding.ank3, binding.ank4, binding.ank5, binding.ank6, binding.ank7, binding.ank8, binding.ank9, binding.ank10, binding.ank11, binding.ank12, binding.ank13, binding.ank14, binding.ank15, binding.ank16, binding.ank17, binding.ank18, binding.ank19, binding.ank20, binding.ank21, binding.ank22, binding.ank23, binding.ank24, binding.ank25, binding.ank26, binding.ank27, binding.ank28, binding.ank29, binding.ank30)
        val delankbts = arrayOf(binding.delankbt1, binding.delankbt2, binding.delankbt3, binding.delankbt4, binding.delankbt5, binding.delankbt6, binding.delankbt7, binding.delankbt8, binding.delankbt9, binding.delankbt10, binding.delankbt11, binding.delankbt12, binding.delankbt13, binding.delankbt14, binding.delankbt15, binding.delankbt16, binding.delankbt17, binding.delankbt18, binding.delankbt19, binding.delankbt20, binding.delankbt21, binding.delankbt22, binding.delankbt23, binding.delankbt24, binding.delankbt25, binding.delankbt26, binding.delankbt27, binding.delankbt28, binding.delankbt29, binding.delankbt30)
        val anketnames = arrayOf(binding.anketname1, binding.anketname2, binding.anketname3, binding.anketname4, binding.anketname5, binding.anketname6, binding.anketname7, binding.anketname8, binding.anketname9, binding.anketname10, binding.anketname11, binding.anketname12, binding.anketname13, binding.anketname14, binding.anketname15, binding.anketname16, binding.anketname17, binding.anketname18, binding.anketname19, binding.anketname20, binding.anketname21, binding.anketname22, binding.anketname23, binding.anketname24, binding.anketname25, binding.anketname26, binding.anketname27, binding.anketname28, binding.anketname29, binding.anketname30)
        val startbts = arrayOf(binding.startbt1, binding.startbt2, binding.startbt3, binding.startbt4, binding.startbt5, binding.startbt6, binding.startbt7, binding.startbt8, binding.startbt9, binding.startbt10, binding.startbt11, binding.startbt12, binding.startbt13, binding.startbt14, binding.startbt15, binding.startbt16, binding.startbt17, binding.startbt18, binding.startbt19, binding.startbt20, binding.startbt21, binding.startbt22, binding.startbt23, binding.startbt24, binding.startbt25, binding.startbt26, binding.startbt27, binding.startbt28, binding.startbt29, binding.startbt30)
        val statbts = arrayOf(binding.statbt1, binding.statbt2, binding.statbt3, binding.statbt4, binding.statbt5, binding.statbt6, binding.statbt7, binding.statbt8, binding.statbt9, binding.statbt10, binding.statbt11, binding.statbt12, binding.statbt13, binding.statbt14, binding.statbt15, binding.statbt16, binding.statbt17, binding.statbt18, binding.statbt19, binding.statbt20, binding.statbt21, binding.statbt22, binding.statbt23, binding.statbt24, binding.statbt25, binding.statbt26, binding.statbt27, binding.statbt28, binding.statbt29, binding.statbt30)
        Log.e(TAG, ankets_names)
        Log.e("MyLog", ankets_names1.size.toString())

        val anketact = Intent(this, Oprosnik::class.java)
        if(ankets_names != "") {
            for (i in 0..(ankets_names1.size - 2)) {
                Log.e("MyLog", i.toString())
                anks[i].visibility = View.VISIBLE
                anketnames[i].text = ankets_names1[i + 1]
                Log.e(TAG, "OK")
                //onclick для "начать опрос"
                startbts[i].setOnClickListener{
                    editor?.putInt("opened_anket", i + 1)
                    editor?.apply()
                    Log.e(TAG, "onclick")
                    startActivity(anketact)
                }
                //onclick для "статистика"
                statbts[i].setOnClickListener{
                    generateStat(i + 1)
                }
            }
        }
    }
    private fun generateStat(ank_num : Int){
        Log.e(TAG, ank_num.toString())
        val ank_name = pref?.getString(ank_num.toString() + "name", "?")
        Log.e(TAG, ank_name.toString())

        val anket_size = pref?.getInt(ank_num.toString() + "size", 0)!!
        var allQuetions : Array<String> = arrayOf()
        for (i in 0..anket_size!! - 1){
            val data = pref?.getString(ank_num.toString() + i.toString(), "?")?.split("||")?.toTypedArray()
            Log.d(TAG, pref?.getString(ank_num.toString() + i.toString(), "?").toString())
            allQuetions += data!![1]}
        Log.e(TAG, allQuetions.joinToString(separator = ", "))

        val allAnsvers = pref?.getString(ank_name, "")!!.split("||").toTypedArray()

        val ourWB = createWorkbook(allQuetions, allAnsvers)
        if (ank_name != null) {
            createExcelFile(ourWB, ank_name, allQuetions, allAnsvers)
        }
        Toast.makeText(this, "Файл сохранен в папку Загрузки", Toast.LENGTH_SHORT).show()

}

    private fun createExcelFile(ourWorkbook: Workbook, ank_name: String, allQuetions: Array<String>, allAnsvers: Array<String>) {

        val ourAppFileDirectory = filesDir
        if (ourAppFileDirectory != null && !ourAppFileDirectory.exists()) {
            ourAppFileDirectory.mkdirs()
        }

        val excelFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "$ank_name.xlsx")

        try {
            val fileOut = FileOutputStream(excelFile)
            ourWorkbook.write(fileOut)
            fileOut.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun createWorkbook(allQuetions: Array<String>, allAnsvers: Array<String>): Workbook {
        val ourWorkbook = XSSFWorkbook()

        val sheet: Sheet = ourWorkbook.createSheet("statSheet")
        addData(sheet, allQuetions, allAnsvers)

        return ourWorkbook
    }

    private fun addData(sheet: Sheet, allQuetions: Array<String>, allAnsvers: Array<String>) {

        val rows : Array<Row> = arrayOf(sheet.createRow(0), sheet.createRow(1), sheet.createRow(2), sheet.createRow(3), sheet.createRow(4), sheet.createRow(5), sheet.createRow(6), sheet.createRow(7), sheet.createRow(8), sheet.createRow(9), sheet.createRow(10), sheet.createRow(11), sheet.createRow(12), sheet.createRow(13), sheet.createRow(14), sheet.createRow(15), sheet.createRow(16), sheet.createRow(17), sheet.createRow(18), sheet.createRow(19), sheet.createRow(20), sheet.createRow(21), sheet.createRow(22), sheet.createRow(23), sheet.createRow(24), sheet.createRow(25), sheet.createRow(26), sheet.createRow(27), sheet.createRow(28), sheet.createRow(29), sheet.createRow(30), sheet.createRow(31))

        for (i in 0..allQuetions.size - 1){
            createCell(rows[0], i, allQuetions[i])
        }
        Log.i(TAG, allAnsvers.joinToString(separator = "||"))
        var y = 0
        var x = 1
        for (i in 0..allAnsvers.size - 1){
            createCell(rows[x], y, allAnsvers[i])
            Log.e(TAG, allAnsvers[i])
            Log.d(TAG, (x).toString())
            Log.d(TAG, (y).toString())
            if (y == allQuetions.size - 1){
                Log.e(TAG, "new row")
                y = 0
                x += 1
            }
            else{y += 1}
        }
    }
}