package com.example.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme
import java.util.Stack
import kotlin.math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCalculatorTheme {
                KalkulatorApp()
            }
        }
    }
}

@Composable
fun KalkulatorApp() {
    var input by remember { mutableStateOf("") }
    var hasil by remember { mutableStateOf("") }
    var isScientificMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF202020))
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = input,
                fontSize = 40.sp,
                color = Color.White,
                maxLines = 2,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = hasil,
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB2FF59),
                textAlign = TextAlign.End,
                maxLines = 1
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            if (isScientificMode) {
                val scientificFontSize = 18.sp

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("C", bgColor = Color(0xFFD32F2F), modifier = Modifier.weight(1f)) { input = ""; hasil = "" }
                    CalcButton("⌫", bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { if(input.isNotEmpty()) input = input.dropLast(1) }
                    CalcButton("sin", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "sin(" }
                    CalcButton("cos", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "cos(" }
                    CalcButton("tan", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "tan(" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("√", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "sqrt(" }
                    CalcButton("sin⁻¹", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "asin(" }
                    CalcButton("cos⁻¹", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "acos(" }
                    CalcButton("tan⁻¹", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "atan(" }
                    CalcButton("%", bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "%" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("x!", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "!" }
                    CalcButton("log", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "log(" }
                    CalcButton("ln", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "ln(" }
                    CalcButton("x^y", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "^" }
                    CalcButton("÷", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "÷" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("1/x", fontSize = scientificFontSize, bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "inv(" }
                    CalcButton("7", modifier = Modifier.weight(1f)) { input += "7" }
                    CalcButton("8", modifier = Modifier.weight(1f)) { input += "8" }
                    CalcButton("9", modifier = Modifier.weight(1f)) { input += "9" }
                    CalcButton("×", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "×" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("(", bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "(" }
                    CalcButton("4", modifier = Modifier.weight(1f)) { input += "4" }
                    CalcButton("5", modifier = Modifier.weight(1f)) { input += "5" }
                    CalcButton("6", modifier = Modifier.weight(1f)) { input += "6" }
                    CalcButton("-", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "-" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton(")", bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += ")" }
                    CalcButton("1", modifier = Modifier.weight(1f)) { input += "1" }
                    CalcButton("2", modifier = Modifier.weight(1f)) { input += "2" }
                    CalcButton("3", modifier = Modifier.weight(1f)) { input += "3" }
                    CalcButton("+", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "+" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("⇆", modifier = Modifier.weight(1f)) { isScientificMode = !isScientificMode }
                    CalcButton("e", bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "e" }
                    // --- 3. PERBAIKI TOMBOL 0 DI SINI ---
                    CalcButton("0", modifier = Modifier.weight(1f)) { input += "0" }
                    CalcButton(".", modifier = Modifier.weight(1f)) { input += "." }
                    CalcButton("=", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { hasil = hitung(input) }
                }

            } else {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("C", bgColor = Color(0xFFD32F2F), modifier = Modifier.weight(1f)) {
                        input = ""
                        hasil = ""
                    }
                    CalcButton("⌫", bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) {
                        if (input.isNotEmpty()) input = input.dropLast(1)
                    }
                    CalcButton("%", bgColor = Color(0xFF616161), modifier = Modifier.weight(1f)) { input += "%" }
                    CalcButton("÷", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "÷" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("7", modifier = Modifier.weight(1f)) { input += "7" }
                    CalcButton("8", modifier = Modifier.weight(1f)) { input += "8" }
                    CalcButton("9", modifier = Modifier.weight(1f)) { input += "9" }
                    CalcButton("×", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "×" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("4", modifier = Modifier.weight(1f)) { input += "4" }
                    CalcButton("5", modifier = Modifier.weight(1f)) { input += "5" }
                    CalcButton("6", modifier = Modifier.weight(1f)) { input += "6" }
                    CalcButton("-", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "-" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("1", modifier = Modifier.weight(1f)) { input += "1" }
                    CalcButton("2", modifier = Modifier.weight(1f)) { input += "2" }
                    CalcButton("3", modifier = Modifier.weight(1f)) { input += "3" }
                    CalcButton("+", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) { input += "+" }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalcButton("⇆", modifier = Modifier.weight(1f)) {
                        isScientificMode = !isScientificMode
                    }

                    CalcButton("0", modifier = Modifier.weight(1f)) { input += "0" }
                    CalcButton(".", modifier = Modifier.weight(1f)) { input += "." }
                    CalcButton("=", bgColor = Color(0xFFFF9800), modifier = Modifier.weight(1f)) {
                        hasil = hitung(input)
                    }
                }
            }
        }
    }
}

@Composable
fun CalcButton(
    text: String,
    bgColor: Color = Color(0xFF424242),
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 22.sp,
    onClick: () -> Unit
) {
    val finalModifier = if (modifier.toString().contains("aspectRatio")) {
        modifier
    } else {
        modifier.aspectRatio(1f)
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
        shape = RoundedCornerShape(24.dp),
        modifier = finalModifier.padding(3.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text, fontSize = fontSize, color = textColor, fontWeight = FontWeight.Medium)
    }
}


fun hitung(expr: String): String {
    return try {

        val cleanExpr = expr
            .replace("×", "*")
            .replace("÷", "/")
            .replace("e", E.toString())
            .replace("π", PI.toString())

        val result = evaluate(cleanExpr)

        if (result == result.toLong().toDouble()) {
            result.toLong().toString()
        } else {

            String.format("%.8f", result).trimEnd('0').trimEnd('.')
        }
    } catch (e: Exception) {

        e.message ?: "Invalid Expression"
    }
}

fun evaluate(expression: String): Double {
    val tokens = mutableListOf<String>()
    var i = 0
    while (i < expression.length) {
        val char = expression[i]
        when {
            char.isDigit() || char == '.' -> {
                val sb = StringBuilder()
                while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                    sb.append(expression[i])
                    i++
                }
                tokens.add(sb.toString())
                i--
            }
            char.isLetter() -> {
                val sb = StringBuilder()
                while (i < expression.length && expression[i].isLetter()) {
                    sb.append(expression[i])
                    i++
                }
                tokens.add(sb.toString())
                i--
            }
            char in "+-*/%^()!" -> tokens.add(char.toString())
        }
        i++
    }

    val outputQueue = mutableListOf<String>()
    val operatorStack = Stack<String>()

    val precedence = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2, "%" to 2, "^" to 3, "!" to 4)
    val functions = setOf("sin", "cos", "tan", "asin", "acos", "atan", "log", "ln", "sqrt", "inv")

    for (token in tokens) {
        when {
            token.toDoubleOrNull() != null -> outputQueue.add(token)
            token in functions -> operatorStack.push(token)
            token == "(" -> operatorStack.push(token)
            token == ")" -> {
                while (operatorStack.isNotEmpty() && operatorStack.peek() != "(") {
                    outputQueue.add(operatorStack.pop())
                }
                operatorStack.pop()
                if (operatorStack.isNotEmpty() && operatorStack.peek() in functions) {
                    outputQueue.add(operatorStack.pop())
                }
            }
            token in precedence.keys -> {
                while (operatorStack.isNotEmpty() &&
                    precedence[operatorStack.peek()] ?: 0 >= precedence[token]!! &&
                    operatorStack.peek() != "("
                ) {
                    outputQueue.add(operatorStack.pop())
                }
                operatorStack.push(token)
            }
        }
    }
    while (operatorStack.isNotEmpty()) {
        outputQueue.add(operatorStack.pop())
    }

    val resultStack = Stack<Double>()
    for (token in outputQueue) {
        when {
            token.toDoubleOrNull() != null -> resultStack.push(token.toDouble())
            token in functions -> {
                if (resultStack.isEmpty()) throw IllegalArgumentException("Missing argument for $token")
                val operand = resultStack.pop()
                val result = when (token) {
                    "sin" -> sin(Math.toRadians(operand))
                    "cos" -> cos(Math.toRadians(operand))
                    "tan" -> tan(Math.toRadians(operand))
                    "asin" -> Math.toDegrees(asin(operand))
                    "acos" -> Math.toDegrees(acos(operand))
                    "atan" -> Math.toDegrees(atan(operand))
                    "log" -> log10(operand)
                    "ln" -> ln(operand)
                    "sqrt" -> sqrt(operand)
                    "inv" -> 1.0 / operand
                    else -> throw IllegalArgumentException("Unknown function $token")
                }
                resultStack.push(result)
            }
            token == "!" -> {
                if (resultStack.isEmpty()) throw IllegalArgumentException("Missing argument for !")
                val operand = resultStack.pop()
                if (operand < 0 || operand != floor(operand)) throw IllegalArgumentException("Factorial is only for non-negative integers")
                var fact = 1.0
                for (j in 1..operand.toInt()) fact *= j
                resultStack.push(fact)
            }
            token in precedence.keys -> {
                if (resultStack.size < 2) throw IllegalArgumentException("Missing operand for $token")
                val b = resultStack.pop()
                val a = resultStack.pop()
                val result = when (token) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    "/" -> if (b == 0.0) throw ArithmeticException("Division by zero") else a / b
                    "%" -> a % b
                    "^" -> a.pow(b)
                    else -> throw IllegalArgumentException("Unknown operator $token")
                }
                resultStack.push(result)
            }
        }
    }
    if (resultStack.size != 1) throw IllegalArgumentException("Invalid expression")
    return resultStack.pop()
}

