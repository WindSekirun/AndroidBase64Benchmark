package com.github.windsekirun.base64benchmark

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.windsekirun.base64benchmark.test.testBytes
import com.github.windsekirun.base64benchmark.test.testString
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Base64Benchmark
 * Class: AndroidImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRun.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            Handler().post {
                runByteTest()
            }
        }

        btnRunString.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            Handler().post {
                runStringTest()
            }
        }

        btnClipboard.setOnClickListener {
            val text = txtResult.text
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.primaryClip = ClipData.newPlainText("text", text)
        }
    }

    private fun runStringTest() {
        val builder = StringBuilder()
        Constants.produceStep.forEach { builder.appendTestResult(it, false) }

        runOnUiThread {
            txtResult.text = builder.toString()
            progressBar.visibility = View.GONE
        }
    }

    private fun runByteTest() {
        val builder = StringBuilder()
        Constants.produceStep.forEach { builder.appendTestResult(it) }

        runOnUiThread {
            txtResult.text = builder.toString()
            progressBar.visibility = View.GONE
        }
    }

    private fun StringBuilder.appendTestResult(size: Int, bytes: Boolean = true) {
        val result = if (bytes) testBytes(size) else testString(size)
        this.append("Test ${if (bytes) "ByteArray" else "String"} with size $size, count $size")
                .append(System.lineSeparator())
                .append(result.toMapString())
                .append(System.lineSeparator())
    }

    private fun <K, V> Map<K, V>.toMapString(delimiter: CharSequence = "\n"): String {
        val builder = StringBuilder()
        val lists = this.entries.toList()
        (0 until lists.size)
                .map { lists[it] }
                .forEach { builder.append("${it.key} -> ${it.value}$delimiter") }
        return builder.toString()
    }
}