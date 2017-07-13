package com.lazycouple.restapiclient.util

import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.SecretKeySpec

/**
 * Created by whylee on 2015. 12. 30..
 */
object EncryptionUtils {
    @Throws(InvalidKeyException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun encryptAES(secret: ByteArray, data: ByteArray): ByteArray? {
        var result: ByteArray? = null
        try {
            val key = SecretKeySpec(secret, "AES")
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            result = cipher.doFinal(data)
        } catch (ignored: NoSuchAlgorithmException) {
        } catch (ignored: NoSuchPaddingException) {
        }

        return result
    }

    @Throws(InvalidKeyException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun decryptAES(secret: ByteArray, data: ByteArray): ByteArray? {
        var result: ByteArray? = null
        try {
            val key = SecretKeySpec(secret, "AES")
            val cipher = Cipher.getInstance("AES/ECB/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, key)
            result = cipher.doFinal(data)
        } catch (ignored: NoSuchAlgorithmException) {
        } catch (ignored: NoSuchPaddingException) {
        }

        return result
    }

    fun sha512(data: ByteArray): ByteArray? {
        var result: ByteArray? = null
        try {
            val md = MessageDigest.getInstance("SHA-512")
            md.reset()
            md.update(data)
            result = md.digest()
        } catch (ignored: NoSuchAlgorithmException) {
        }

        return result
    }

    fun sha256(data: ByteArray): ByteArray? {
        var result: ByteArray? = null
        try {
            val md = MessageDigest.getInstance("SHA-256")
            md.reset()
            md.update(data)
            result = md.digest()
        } catch (ignored: NoSuchAlgorithmException) {
        }

        return result
    }

    fun md5(data: ByteArray): ByteArray? {
        var result: ByteArray? = null
        try {
            val md = MessageDigest.getInstance("MD5")
            md.reset()
            md.update(data)
            result = md.digest()
        } catch (ignored: NoSuchAlgorithmException) {
        }

        return result
    }
}
