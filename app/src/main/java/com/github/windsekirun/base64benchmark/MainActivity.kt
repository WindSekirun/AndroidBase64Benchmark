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
                runTest()
            }
        }

        btnClipboard.setOnClickListener {
            val text = txtResult.text
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.primaryClip = ClipData.newPlainText("text", text)
        }
    }

    private fun runTest() {
        val byte100 = testBytes(100)
        val byte1000 = testBytes(1000)
        val byteMax = testBytes(Constants.TOTAL_BUFFER_SIZE)

        val string100 = testString(100)
        val string1000 = testString(1000)
        val stringMax = testString(Constants.TOTAL_BUFFER_SIZE)

        val builder = StringBuilder()
        builder.append("Test ByteArray with bufferSize 100")
                .append(System.lineSeparator())
                .append(byte100.toMapString())
                .append(System.lineSeparator())

        builder.append("Test ByteArray with bufferSize 1000")
                .append(System.lineSeparator())
                .append(byte1000.toMapString())
                .append(System.lineSeparator())

        builder.append("Test ByteArray with bufferSize ${Constants.TOTAL_BUFFER_SIZE}")
                .append(System.lineSeparator())
                .append(byteMax.toMapString())
                .append(System.lineSeparator())

        builder.append("Test String with bufferSize 100")
                .append(System.lineSeparator())
                .append(string100.toMapString())
                .append(System.lineSeparator())

        builder.append("Test String with bufferSize 1000")
                .append(System.lineSeparator())
                .append(string1000.toMapString())
                .append(System.lineSeparator())

        builder.append("Test String with bufferSize ${Constants.TOTAL_BUFFER_SIZE}")
                .append(System.lineSeparator())
                .append(stringMax.toMapString())
                .append(System.lineSeparator())

        runOnUiThread {
            txtResult.text = builder.toString()
            progressBar.visibility = View.GONE
        }
    }

    @JvmOverloads
    fun <K, V> Map<K, V>.toMapString(delimiter: CharSequence = "\n"): String {
        val builder = StringBuilder()
        val lists = this.entries.toList()
        (0 until lists.size)
                .map { lists[it] }
                .forEach { builder.append("[${it.key}] -> [${it.value}]$delimiter") }
        return builder.toString()
    }
}
