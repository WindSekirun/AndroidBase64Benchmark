package com.github.windsekirun.base64benchmark.model

import org.apache.commons.lang3.time.StopWatch

data class TestResult constructor(val encodeTime: Double, val decodeTime: Double) {

    override fun toString(): String {
        return "encode=${encodeTime}ms, decode=${decodeTime}ms"
    }
}

fun measureTimeStopWatch(job: () -> Unit): Long {
    val watch = StopWatch()
    watch.start()
    job()
    watch.stop()
    return watch.time
}