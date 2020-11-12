package com.example.calculadora_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity() {

    var isNewOp = true
    var oldNum = ""
    var op = "+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_igual.isClickable = false


    }

    fun number(view: View) {
        if (isNewOp) {
            tv.text = ""
            tv_2.text = ""
        }
        isNewOp = false
        var btClickValue = tv.text.toString()
        var btPressed = view as Button
        when (btPressed.id) {
            bt_0.id -> {
                btClickValue += "0"
            }
            bt_1.id -> {
                btClickValue += "1"
            }
            bt_2.id -> {
                btClickValue += "2"
            }
            bt_3.id -> {
                btClickValue += "3"
            }
            bt_4.id -> {
                btClickValue += "4"
            }
            bt_5.id -> {
                btClickValue += "5"
            }
            bt_6.id -> {
                btClickValue += "6"
            }
            bt_7.id -> {
                btClickValue += "7"
            }
            bt_8.id -> {
                btClickValue += "8"
            }
            bt_9.id -> {
                btClickValue += "9"
            }
            bt_minus.id -> {
                btClickValue = "-$btClickValue"
                bt_minus.isClickable = false
            }
            bt_coma.id -> {
                if (btClickValue.isNotEmpty()) {
                    btClickValue += "."
                } else {
                    btClickValue += "0."
                }
                bt_coma.isClickable = false
            }
        }
        tv.setText(btClickValue)
    }

    fun operator(view: View) {
        bt_minus.isClickable = true
        bt_coma.isClickable = true
        var btPressed = view as Button
        when (btPressed.id) {
            bt_sum.id -> {
                op = "+"
            }
            bt_res.id -> {
                op = "-"
            }
            bt_mul.id -> {
                op = "x"
            }
            bt_div.id -> {
                op = "/"
            }
        }
        oldNum = tv.text.toString()
        isNewOp = true
        bt_igual.isClickable = true
    }

    fun equal(view: View) {
        var newNum = tv.text.toString()
        var result = 0.0
        when (op) {
            "+" -> {
                result = oldNum.toDouble() + newNum.toDouble()
            }
            "-" -> {
                result = oldNum.toDouble() - newNum.toDouble()
            }
            "x" -> {
                result = oldNum.toDouble() * newNum.toDouble()
                if (result.isInfinite() || result.isNaN()) {
                    result = 0.0;
                }
            }
            "/" -> {
                result = oldNum.toDouble() / newNum.toDouble()
                if (result.isInfinite() || result.isNaN()) {
                    result = 0.0
                    Toast.makeText(
                        applicationContext,
                        "No puedes dividir entre 0",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        tv_2.setText(oldNum + op + newNum)
        tv.text = result.toString()
        isNewOp = true
    }

    fun deleteLast(view: View) {
        val chain = tv.text.toString()
        if (chain.isNotEmpty()) {
            tv.text = chain.substring(0, chain.length - 1)
        }
    }

    fun resetAll(view: View) {

        oldNum = 0.toString()
        tv.text = "0"
        tv_2.text = ""
        isNewOp = true
        bt_igual.isClickable = true
    }

    fun percent(view: View) {
        var percent = tv.text.toString().toDouble() / 100
        tv.setText(percent.toString())
        isNewOp = true
    }

    var isNewOpLand = true
    var oldNumLand = ""
    var opLand = "+"

    fun landNum(view: View) {
        if (isNewOpLand) {
            tv.text = ""
        }
        isNewOpLand = false
        var btClickValue = tv.text.toString()
        var btPressed = view as Button
        when (btPressed.id) {
            bt_0.id -> {
                btClickValue += "0"
            }
            bt_1.id -> {
                btClickValue += "1"
            }
            bt_2.id -> {
                btClickValue += "2"
            }
            bt_3.id -> {
                btClickValue += "3"
            }
            bt_4.id -> {
                btClickValue += "4"
            }
            bt_5.id -> {
                btClickValue += "5"
            }
            bt_6.id -> {
                btClickValue += "6"
            }
            bt_7.id -> {
                btClickValue += "7"
            }
            bt_8.id -> {
                btClickValue += "8"
            }
            bt_9.id -> {
                btClickValue += "9"
            }
            bt_coma.id -> {
                if (btClickValue.isNotEmpty()) {
                    btClickValue += "."
                } else {
                    btClickValue += "0."
                }
                bt_coma.isClickable = false
            }
        }
        tv.setText(btClickValue)
    }

    fun numLetra(view: View) {
        if (isNewOpLand) {
            tv.text = ""
        }
        isNewOpLand = false
        var btClickValue = tv.text.toString()
        var btPressed = view as Button
        when (btPressed.id) {
            bt_a.id -> {
                btClickValue += "a"
            }
            bt_b.id -> {
                btClickValue += "b"
            }
            bt_c.id -> {
                btClickValue += "c"
            }
            bt_d.id -> {
                btClickValue += "d"
            }
            bt_e.id -> {
                btClickValue += "e"
            }
            bt_f.id -> {
                btClickValue += "f"
            }
        }
        tv.setText(btClickValue)
    }

    fun landOperator(view: View) {
        bt_coma.isClickable = true
        var btPressed = view as Button

        if (!bt_bi.isEnabled) {
            var numero = tv.text.toString().toLong()
            var a = convertBinaryToDecimal(numero)
            oldNumLand = a.toString()
            tv.setText("0")
        }
        if (!bt_hex.isEnabled) {
            var x = hexadecimalADecimal(tv.text.toString());
            oldNumLand = x.toString()
            tv.setText("0")
        }
        if (!bt_dec.isEnabled) {
            oldNumLand = tv.text.toString()
            tv.setText("0")
        }
        when (btPressed.id) {
            bt_sum.id -> {
                opLand = "+"
            }
            bt_res.id -> {
                opLand = "-"
            }
            bt_mul.id -> {
                opLand = "x"
            }
            bt_div.id -> {
                opLand = "/"
            }
        }
        isNewOpLand = true
        bt_igual.isClickable = true
    }

    fun landEqual(view: View) {
        var currentNum = tv.text
        var result = 0
        try {
            var newNum = tv.text.toString()
            if (!bt_bi.isEnabled) {
                var numero = tv.text.toString().toLong()
                var x = convertBinaryToDecimal(numero)
                newNum = x.toString()
            }
            if (!bt_hex.isEnabled) {
                var x = hexadecimalADecimal(tv.text.toString());
                newNum = x.toString()
            }
            if (!bt_dec.isEnabled) {
                newNum = tv.text.toString()
            }

            when (opLand) {

                "+" -> {
                    result = oldNumLand.toInt() + newNum.toInt()
                }
                "-" -> {
                    result = oldNumLand.toInt() - newNum.toInt()
                }
                "x" -> {
                    result = oldNumLand.toInt() * newNum.toInt()

                }
                "/" -> {
                    result = oldNumLand.toInt() / newNum.toInt()

                }

            }

            if (!bt_bi.isEnabled) {
                var bin = result.toString()

                var resBin = Integer.toBinaryString(bin.toInt())
                tv.setText(resBin.toString())
            }
            if (!bt_hex.isEnabled) {
                var e = result.toString()
                var r = Integer.toHexString(e.toInt())
                tv.setText(r)
            }
            if (!bt_dec.isEnabled) {
                tv.setText(result.toString())
            }

            isNewOpLand = true
        } catch (ae: ArithmeticException) {
            Toast.makeText(applicationContext, "No puedes dividir entre 0", Toast.LENGTH_SHORT)
                .show()
            result = 0

        } catch (ex: NumberFormatException) {
            currentNum = tv.text
        } catch (e: Exception) {
        }

    }

    fun resetAllLand(view: View) {
        oldNum = 0.toString()
        tv.text = "0"
        isNewOp = true
    }

    fun deleteLastLand(view: View) {
        val chain = tv.text.toString()
        if (chain.isNotEmpty()) {
            tv.text = chain.substring(0, chain.length - 1)
        }
    }

    fun convertBinaryToDecimal(num: Long): Int {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }

    fun hexadecimalADecimal(hexadecimal: String): Long {
        var decimal: Long = 0
        var potencia = 0
        for (x in hexadecimal.length - 1 downTo 0) {
            val valor: Int = caracterHexadecimalADecimal(hexadecimal[x])
            val elevado = Math.pow(16.0, potencia.toDouble()).toLong() * valor
            decimal += elevado
            potencia++
        }
        return decimal
    }

    fun caracterHexadecimalADecimal(caracter: Char): Int {
        return when (caracter) {
            'a' -> 10
            'b' -> 11
            'c' -> 12
            'd' -> 13
            'e' -> 14
            'f' -> 15
            else -> caracter.toString().toInt()
        }
    }

    fun binary(view: View) {

        Toast.makeText(applicationContext, "Modo BINARIO activado", Toast.LENGTH_SHORT)
            .show()
        bt_2.visibility = View.INVISIBLE
        bt_3.visibility = View.INVISIBLE
        bt_4.visibility = View.INVISIBLE
        bt_5.visibility = View.INVISIBLE
        bt_6.visibility = View.INVISIBLE
        bt_7.visibility = View.INVISIBLE
        bt_8.visibility = View.INVISIBLE
        bt_9.visibility = View.INVISIBLE

        bt_a.visibility = View.INVISIBLE
        bt_b.visibility = View.INVISIBLE
        bt_c.visibility = View.INVISIBLE
        bt_d.visibility = View.INVISIBLE
        bt_e.visibility = View.INVISIBLE
        bt_f.visibility = View.INVISIBLE

        if (!bt_dec.isEnabled) {
            if (tv.text == "") {
                tv.text = "0"
            } else {
                try {
                    var toBi = tv.text.toString().toLong()
                    tv.setText(Integer.toBinaryString(toBi.toInt()))
                } catch (ae: NumberFormatException) {
                    Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                        .show()
                    tv.text = "0"
                }

            }
        }

        if (!bt_hex.isEnabled) {
            try {
                var toDec = hexadecimalADecimal(tv.text.toString())
                tv.setText(Integer.toBinaryString(toDec.toInt()))
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                    .show()
                tv.text = "0"
            }

        }

        bt_bi.setEnabled(false)
        bt_dec.setEnabled(true)
        bt_hex.setEnabled(true)
    }

    fun decimal(view: View) {
        Toast.makeText(applicationContext, "Modo DECIMAL activado", Toast.LENGTH_SHORT)
            .show()

        bt_2.visibility = View.VISIBLE
        bt_3.visibility = View.VISIBLE
        bt_4.visibility = View.VISIBLE
        bt_5.visibility = View.VISIBLE
        bt_6.visibility = View.VISIBLE
        bt_7.visibility = View.VISIBLE
        bt_8.visibility = View.VISIBLE
        bt_9.visibility = View.VISIBLE

        bt_a.visibility = View.INVISIBLE
        bt_b.visibility = View.INVISIBLE
        bt_c.visibility = View.INVISIBLE
        bt_d.visibility = View.INVISIBLE
        bt_e.visibility = View.INVISIBLE
        bt_f.visibility = View.INVISIBLE

        if (!bt_bi.isEnabled) {
            try {
                var toBi = tv.text.toString().toLong()
                var a = convertBinaryToDecimal(toBi)
                tv.setText(a.toString())
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                    .show()
                tv.text = "0"
            }
        }
        if (!bt_hex.isEnabled) {
            try {
                var toDec = hexadecimalADecimal(tv.text.toString())
                tv.setText(toDec.toString())
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                    .show()
                tv.text = "0"
            }
        }
        bt_bi.setEnabled(true)
        bt_dec.setEnabled(false)
        bt_hex.setEnabled(true)
    }

    fun hexa(view: View) {
        Toast.makeText(applicationContext, "Modo HEXADECIMAL activado", Toast.LENGTH_SHORT)
            .show()
        bt_2.visibility = View.VISIBLE
        bt_3.visibility = View.VISIBLE
        bt_4.visibility = View.VISIBLE
        bt_5.visibility = View.VISIBLE
        bt_6.visibility = View.VISIBLE
        bt_7.visibility = View.VISIBLE
        bt_8.visibility = View.VISIBLE
        bt_9.visibility = View.VISIBLE

        bt_a.visibility = View.VISIBLE
        bt_b.visibility = View.VISIBLE
        bt_c.visibility = View.VISIBLE
        bt_d.visibility = View.VISIBLE
        bt_e.visibility = View.VISIBLE
        bt_f.visibility = View.VISIBLE

        if (!bt_bi.isEnabled) {

            try {
                var toBi = tv.text.toString().toLong()
                var toDec = convertBinaryToDecimal(toBi)
                tv.setText(Integer.toHexString(toDec))
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                    .show()
                tv.text = "0"
            }
        }
        if (!bt_dec.isEnabled) {
            try {
                var toDec = hexadecimalADecimal(tv.text.toString())
                tv.setText(Integer.toHexString(toDec.toInt()))
            } catch (ae: NumberFormatException) {
                Toast.makeText(applicationContext, "Numero demasiado largo", Toast.LENGTH_LONG)
                    .show()
                tv.text = "0"
            }
        }
        bt_bi.setEnabled(true)
        bt_dec.setEnabled(true)
        bt_hex.setEnabled(false)
    }


}
