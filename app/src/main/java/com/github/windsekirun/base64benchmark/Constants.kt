package com.github.windsekirun.base64benchmark

import com.github.windsekirun.base64benchmark.codec.*
import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.impl.Base64Codec

/**
 * Base64Benchmark
 * Class: Constants
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */

object Constants {
    val produceStep: List<Int> = listOf(1024, 2048, 4096, 8192, 10240)

    val byteCodecList: List<Base64ByteCodec> =
        listOf(AndroidImpl(), ApacheImpl(), Java8Impl(), MiGImpl(), IHarderImpl())

    // guava doesn't support ByteArray -> ByteArray
    val codecList: List<Base64Codec> =
        listOf(AndroidImpl(), ApacheImpl(), Java8Impl(), MiGImpl(), IHarderImpl(), GuavaImpl())
}