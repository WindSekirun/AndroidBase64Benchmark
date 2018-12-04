package com.github.windsekirun.base64benchmark

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.windsekirun.base64benchmark.test.testBytes
import com.github.windsekirun.base64benchmark.test.testFile
import com.github.windsekirun.base64benchmark.test.testString
import kotlinx.android.synthetic.main.activity_main.*
import pyxis.uzuki.live.richutilskt.utils.RPickMedia
import pyxis.uzuki.live.richutilskt.utils.isEmpty
import pyxis.uzuki.live.richutilskt.utils.toFile
import pyxis.uzuki.live.richutilskt.utils.toast

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
            testStart {
                runByteTest()
            }
        }

        btnRunString.setOnClickListener {
            testStart {
                runStringTest()
            }
        }

        btnPicture.setOnClickListener {
            RPickMedia.instance.pickFromGallery(this) { code, path ->
                runFileTest(code, path)
            }
        }

        btnVideo.setOnClickListener {
            RPickMedia.instance.pickFromVideo(this) { code, path ->
                runFileTest(code, path)
            }
        }

        btnClipboard.setOnClickListener {
            val text = txtResult.text
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.primaryClip = ClipData.newPlainText("text", text)

            toast("Copied to clipboard.")
        }
    }

    private fun runFileTest(code: Int, path: String) {
        if (code != RPickMedia.PICK_SUCCESS || path.isEmpty()) return
        testStart {
            runFileTest(path)
        }
    }

    private fun runFileTest(path: String) {
        val file = path.toFile()

        val result = testFile(file)
        val builder = StringBuilder()
        builder.append("Test encoding file ${file.name} into Base64")
            .append(System.lineSeparator())
            .append(result.toMapString())
            .append(System.lineSeparator())

        testEnd(builder.toString())
    }

    private fun runStringTest() {
        val builder = StringBuilder()
        Constants.produceStep.forEach { builder.appendTestResult(it, false) }
        testEnd(builder.toString())
    }

    private fun runByteTest() {
        val builder = StringBuilder()
        Constants.produceStep.forEach { builder.appendTestResult(it) }
        testEnd(builder.toString())
    }

    private fun StringBuilder.appendTestResult(size: Int, bytes: Boolean = true) {
        val result = if (bytes) testBytes(size) else testString(size)
        this.append("Test ${if (bytes) "ByteArray" else "String"} with size $size, count $size")
            .append(System.lineSeparator())
            .append(result.toMapString())
            .append(System.lineSeparator())
    }

    private fun testStart(start: () -> Unit) {
        progressBar.visibility = View.VISIBLE

        Handler().post {
            start()
        }
    }

    private fun testEnd(result: String) {
        runOnUiThread {
            txtResult.text = result
            progressBar.visibility = View.GONE
        }
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