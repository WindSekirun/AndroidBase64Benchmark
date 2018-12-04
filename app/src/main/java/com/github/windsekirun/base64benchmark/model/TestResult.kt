package com.github.windsekirun.base64benchmark.model

data class TestResult constructor(val encodeTime: Double, val decodeTime: Double) {

    override fun toString(): String {
        return "encode=${encodeTime}s, decode=${decodeTime}s"
    }
}