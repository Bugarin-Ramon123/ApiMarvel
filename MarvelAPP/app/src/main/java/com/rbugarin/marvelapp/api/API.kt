package com.rbugarin.marvelapp.api

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

class API {
    val publicKey: String = "965daf35340a25c55e1294c31f01d2d7"
    val privateKey: String = "57578fbce537f4a36970222eca7188f6c61ce923"
    val timeStamp: String
    //private var config: FBRemoteConfig? = null
    var md5Key: String = ""
        fun getHash(): String {
            val hash: String? = null
            val input = timeStamp + privateKey + publicKey
            try {
                val md: MessageDigest = MessageDigest.getInstance("MD5")
                val md5Bytes: ByteArray = md.digest(input.toByteArray())
                val md5 = StringBuilder()
                for (i in md5Bytes.indices) {
                    md5.append(
                        Integer.toHexString((md5Bytes[i] and 0xFF.toByte() or 0x100.toByte()).toInt())
                            .substring(1,3)
                    )
                }
                md5Key = md5.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return md5Key
        }
        //private set

    companion object {
        private const val KEY_PRIVATE = "KEY_PRIVATE"
        private const val KEY_PUBLIC = "KEY_PUBLIC"
    }
    //private val config: FBRemoteConfig
    init {
        timeStamp = System.currentTimeMillis().toString()
        /*config = FBRemoteConfig()
        privateKey = config.getKeyValue(KEY_PRIVATE)
        publicKey = config.getKeyValue(KEY_PUBLIC)*/
    }
}