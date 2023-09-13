package com.example.scanner.data.repo

import com.example.scanner.domain.repo.MainRepo
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// dependancy injection
class MainRepoImpl @Inject constructor(
    private val scanner: GmsBarcodeScanner
): MainRepo {
    override fun startScan(): Flow<String?> {

        return callbackFlow {

            // perform scan
            scanner.startScan()

                .addOnSuccessListener { code->

                    // launch the coroutine scope:
                    launch {
                        // send the data to other coroutine in nonblocking manner:
                        send(getDetails(code))
                    }
                }

                .addOnFailureListener { e->
                    e.printStackTrace()
                }

            awaitClose {  }
        }
    }

    private fun getDetails(barcode: Barcode): String {

        return when(barcode.valueType) {

            Barcode.TYPE_WIFI -> {
                val ssid = barcode.wifi!!.ssid
                val pw = barcode.wifi!!.password
                val type = barcode.wifi!!.encryptionType

                "SSID: $ssid    Password: $pw   Type: $type"
            }

            Barcode.TYPE_URL -> {
                "url : ${barcode.url!!.url}"
            }
            Barcode.TYPE_PRODUCT -> {
                "productType : ${barcode.displayValue}"
            }
            Barcode.TYPE_EMAIL -> {
                "email : ${barcode.email}"
            }
            Barcode.TYPE_CONTACT_INFO -> {
                "contact : ${barcode.contactInfo}"
            }
            Barcode.TYPE_PHONE -> {
                "phone : ${barcode.phone}"
            }
            Barcode.TYPE_CALENDAR_EVENT -> {
                "calender event : ${barcode.calendarEvent}"
            }
            Barcode.TYPE_GEO -> {
                "geo point : ${barcode.geoPoint}"
            }
            Barcode.TYPE_ISBN -> {
                "isbn : ${barcode.displayValue}"
            }
            Barcode.TYPE_DRIVER_LICENSE -> {
                "driving license : ${barcode.driverLicense}"
            }
            Barcode.TYPE_SMS -> {
                "sms : ${barcode.sms}"
            }
            Barcode.TYPE_TEXT -> {
                "text : ${barcode.rawValue}"
            }
            Barcode.TYPE_UNKNOWN -> {
                "unknown : ${barcode.rawValue}"
            }
            else -> {
                "Couldn't determine"
            }
        }
    }
}