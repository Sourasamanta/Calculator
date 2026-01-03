package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.*

class MainActivity : ComponentActivity() {

    private var value: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val answer = findViewById<TextView>(R.id.Answer)

        val aC = findViewById<Button>(R.id.AC)
        val backspace = findViewById<Button>(R.id.backspace)
        val equals = findViewById<Button>(R.id.equals)

        val zero = findViewById<Button>(R.id.zero)
        val one = findViewById<Button>(R.id.one)
        val two = findViewById<Button>(R.id.two)
        val three = findViewById<Button>(R.id.three)
        val four = findViewById<Button>(R.id.four)
        val five = findViewById<Button>(R.id.five)
        val six = findViewById<Button>(R.id.six)
        val seven = findViewById<Button>(R.id.seven)
        val eight = findViewById<Button>(R.id.eight)
        val nine = findViewById<Button>(R.id.nine)

        val plus = findViewById<Button>(R.id.plus)
        val minus = findViewById<Button>(R.id.minus)
        val multiply = findViewById<Button>(R.id.multiply)
        val divide = findViewById<Button>(R.id.divide)
        val decimal = findViewById<Button>(R.id.decimal)
        val doubleZero = findViewById<Button>(R.id.doubleZero)
        val percent = findViewById<Button>(R.id.percent)
        val openParen = findViewById<Button>(R.id.openBracket)
        val closeParen = findViewById<Button>(R.id.closeBracket)

        aC.setOnClickListener {
            value = ""
            answer.text = value
        }

        backspace.setOnClickListener {
            if (value.isNotEmpty()) {
                value = value.substring(0, value.length - 1)
                answer.text = value
            }
        }

        equals.setOnClickListener {
            try {
                val result = evaluateExpression(value)
                answer.text = result.toString()
                value = result.toString()
            } catch (e: Exception) {
                answer.text = "Error"
            }
        }

        fun appendValue(s: String) {
            value += s
            answer.text = value
        }

        zero.setOnClickListener { appendValue("0") }
        one.setOnClickListener { appendValue("1") }
        two.setOnClickListener { appendValue("2") }
        three.setOnClickListener { appendValue("3") }
        four.setOnClickListener { appendValue("4") }
        five.setOnClickListener { appendValue("5") }
        six.setOnClickListener { appendValue("6") }
        seven.setOnClickListener { appendValue("7") }
        eight.setOnClickListener { appendValue("8") }
        nine.setOnClickListener { appendValue("9") }
        doubleZero.setOnClickListener { appendValue("00") }
        decimal.setOnClickListener { appendValue(".") }

        plus.setOnClickListener { appendValue("+") }
        minus.setOnClickListener { appendValue("-") }
        multiply.setOnClickListener { appendValue("×") }
        divide.setOnClickListener { appendValue("÷") }
        percent.setOnClickListener { appendValue("%") }
        openParen.setOnClickListener { appendValue("(") }
        closeParen.setOnClickListener { appendValue(")") }
    }

    private fun evaluateExpression(expression: String): Double {
        val exp = expression.replace("×", "*").replace("÷", "/")
        val tokens = tokenize(exp)
        val postfix = infixToPostfix(tokens)
        return evaluatePostfix(postfix)
    }

    private fun tokenize(expr: String): List<String> {
        val tokens = mutableListOf<String>()
        var i = 0
        while (i < expr.length) {
            when {
                expr[i].isDigit() || expr[i] == '.' -> {
                    var num = ""
                    while (i < expr.length && (expr[i].isDigit() || expr[i] == '.')) {
                        num += expr[i]
                        i++
                    }
                    tokens.add(num)
                }

                expr[i] in "+-*/%" -> {

                    // ❌ Detect invalid percent: a%b
                    if (expr[i] == '%' &&
                        i + 1 < expr.length &&
                        expr[i + 1].isDigit()
                    ) {
                        throw IllegalArgumentException("Invalid use of % operator")
                    }

                    tokens.add(expr[i].toString())
                    i++
                }

                expr[i] == '(' || expr[i] == ')' -> {
                    tokens.add(expr[i].toString())
                    i++
                }

                expr[i].isWhitespace() -> i++

                else -> throw IllegalArgumentException("Invalid character: ${expr[i]}")
            }
        }
        return tokens
    }

    private fun infixToPostfix(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val stack = Stack<String>()
        val precedence = mapOf(
            "+" to 1,
            "-" to 1,
            "*" to 2,
            "/" to 2,
            "%" to 3 // percent highest (unary)
        )

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token)

                token == "(" -> stack.push(token)

                token == ")" -> {
                    while (stack.isNotEmpty() && stack.peek() != "(") {
                        output.add(stack.pop())
                    }
                    if (stack.isNotEmpty() && stack.peek() == "(")
                        stack.pop()
                    else throw IllegalArgumentException("Mismatched parentheses")
                }

                token in precedence.keys -> {
                    while (
                        stack.isNotEmpty() &&
                        stack.peek() != "(" &&
                        precedence.getOrDefault(stack.peek(), 0) >= precedence[token]!!
                    ) {
                        output.add(stack.pop())
                    }
                    stack.push(token)
                }
            }
        }

        while (stack.isNotEmpty()) {
            if (stack.peek() == "(" || stack.peek() == ")")
                throw IllegalArgumentException("Mismatched parentheses")
            output.add(stack.pop())
        }

        return output
    }

    private fun evaluatePostfix(postfix: List<String>): Double {
        val stack = Stack<Double>()

        for (token in postfix) {
            val num = token.toDoubleOrNull()

            if (num != null) {
                stack.push(num)
            } else {
                when (token) {
                    "+" -> {
                        val b = stack.pop()
                        val a = stack.pop()
                        stack.push(a + b)
                    }
                    "-" -> {
                        val b = stack.pop()
                        val a = stack.pop()
                        stack.push(a - b)
                    }
                    "*" -> {
                        val b = stack.pop()
                        val a = stack.pop()
                        stack.push(a * b)
                    }
                    "/" -> {
                        val b = stack.pop()
                        val a = stack.pop()
                        stack.push(a / b)
                    }
                    "%" -> {
                        val a = stack.pop()
                        stack.push(a / 100)
                    }
                    else -> throw IllegalArgumentException("Unknown operator: $token")
                }
            }
        }

        return stack.pop()
    }
}
