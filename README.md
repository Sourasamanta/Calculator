# Calculator App — Android (Kotlin | XML) {apk file available}

A **robust Android calculator application** built using **Kotlin** with a **traditional XML-based UI**, demonstrating **algorithmic thinking**, **expression parsing**, and **clean separation of logic**.
The project emphasizes **correctness, edge-case handling, and maintainable code**, making it well-suited for **software developer and Android internship roles**.

---

## 🚀 Overview

This project implements a **fully functional arithmetic calculator** capable of evaluating complex mathematical expressions involving:

* Operator precedence
* Parentheses
* Unary percent operation
* Decimal numbers

Instead of relying on unsafe built-in evaluators, the app uses a **custom expression parser and evaluator**, highlighting strong fundamentals in **data structures and algorithms**.

---

## 🎯 What This Project Demonstrates (Internship Focus)

* Strong **problem-solving and algorithmic reasoning**
* Clear understanding of **expression parsing**
* Practical use of **Stacks** and operator precedence
* Defensive programming with **input validation**
* Clean, readable **Kotlin**
* Solid Android fundamentals using **XML layouts**
* Event-driven UI handling with `Button` and `TextView`

---

## 🧮 Core Features

* Supports:

  * Addition (`+`)
  * Subtraction (`-`)
  * Multiplication (`×`)
  * Division (`÷`)
  * Percentage (`%`)
  * Parentheses (`(`, `)`)
* Decimal number support
* Backspace and clear operations
* Graceful error handling for:

  * Invalid expressions
  * Mismatched parentheses
  * Incorrect percent usage
* Deterministic evaluation (no floating ambiguity)

---

## 🧠 Algorithmic Approach

### 1️⃣ Tokenization

Breaks the input string into valid tokens (numbers, operators, parentheses).

```text
"12.5+(3×4)" → ["12.5", "+", "(", "3", "*", "4", ")"]
```

---

### 2️⃣ Infix → Postfix Conversion

Uses the **Shunting Yard Algorithm** to enforce operator precedence.

```text
Infix:    3 + 4 × 2
Postfix:  3 4 2 × +
```

---

### 3️⃣ Postfix Evaluation

Evaluated using a **stack-based execution model**.

```text
Stack → Result
```

This ensures predictable and testable behavior.

---

## ⚠️ Input Safety & Validation

* Detects invalid percent usage (`a%b`)
* Prevents mismatched parentheses
* Handles malformed input gracefully
* Displays `"Error"` instead of crashing

---

## 🖼️ Screenshots & Demo

### App UI (XML Layout)

<p align="center">
  <img
    src="https://github.com/Sourasamanta/ScreenShots/blob/main/Calculator/Calculator.jpeg"
    width="240"
    alt="Calculator App XML UI"
  />
</p>

---

### Demo Walkthrough

<p align="center">
  <img
    src="https://github.com/Sourasamanta/ScreenShots/blob/main/Calculator/CalculatorDemo.gif"
    width="240"
    alt="Calculator App Demo"
  />
</p>

<em>Demonstrates expression input, operator precedence, evaluation, and error handling.</em>

---

## 🛠️ Tech Stack

* **Language:** Kotlin
* **UI:** XML Layouts (Views)
* **Platform:** Android
* **Architecture:** Single Activity (logic-driven)
* **Data Structures:** Stack
* **Algorithms:** Shunting Yard, Postfix Evaluation

---

## ⚙️ Installation & Run

### Requirements

* Android Studio (latest stable)
* Android SDK
* Emulator or physical device

### Run Locally

```bash
git clone <repository-url>
cd CalculatorApp
./gradlew installDebug
```

Or press **Run ▶️** in Android Studio.

---

## 🧪 Example Test Cases

| Expression  | Output  |
| ----------- | ------- |
| `10+20×3`   | `70`    |
| `50%`       | `0.5`   |
| `(5+5)%`    | `0.1`   |
| `((2+3)*4)` | `20`    |
| `12..3`     | `Error` |

---

## ⚠️ Limitations

* UI implemented using **XML**, not Jetpack Compose
* No scientific functions (sin, cos, log)
* No automated unit tests yet
* Single-activity architecture

---

## 🛣️ Future Improvements

* Migrate UI to **Jetpack Compose**
* Add scientific calculator features
* Introduce **MVVM architecture**
* Add unit tests for evaluator logic
* Improve accessibility and theming

---

## 🤝 Contributing

Contributions are welcome.
Fork the repository, create a feature branch, and submit a pull request with a clear explanation.

---

## 📝 License

This project is licensed under the **MIT License**.
