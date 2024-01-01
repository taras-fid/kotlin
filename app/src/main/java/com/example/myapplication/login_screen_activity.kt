package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException
import org.json.JSONObject
import com.example.myapplication.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class login_screen_activity : AppCompatActivity() {
	lateinit var login_btn: Button
	lateinit var state_layer_ek2: FloatingActionButton
	lateinit var _bg__device_device_frame_components_navigation_ek1: View
	lateinit var _bg__login_screen: View
	lateinit var _bg__remember_checkbox_ek1: View
	lateinit var _bg__checkbox_ek1: View
	lateinit var _bg__state_layer_ek5: View
	lateinit var container: View
	lateinit var _bg__text_fields_ek1: View
	lateinit var text_field_email: EditText
	lateinit var text_field_password: EditText
	lateinit var _bg__logo_ek1: View
	lateinit var handle: View
	lateinit var _bg___button_ek1: View

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.login_screen)

		setContentView(R.layout.login_screen)

		_bg__login_screen = findViewById<View>(R.id._bg__login_screen)
		_bg__device_device_frame_components_navigation_ek1 =
			findViewById<View>(R.id._bg__device_device_frame_components_navigation_ek1)
		handle = findViewById<View>(R.id.handle)
		_bg___button_ek1 = findViewById<View>(R.id._bg___button_ek1)
		login_btn = findViewById<Button>(R.id._bg__state_layer_ek1)
		state_layer_ek2 = findViewById<FloatingActionButton>(R.id.state_layer_ek2)
		_bg__device_device_frame_components_navigation_ek1 =
			findViewById<View>(R.id._bg__device_device_frame_components_navigation_ek1)
		_bg__remember_checkbox_ek1 = findViewById<View>(R.id._bg__remember_checkbox_ek1)
		_bg__checkbox_ek1 = findViewById<View>(R.id._bg__checkbox_ek1)
		_bg__state_layer_ek5 = findViewById<View>(R.id._bg__state_layer_ek5)
		container = findViewById<View>(R.id.container)
		_bg__text_fields_ek1 = findViewById<View>(R.id._bg__text_fields_ek1)
		text_field_email = findViewById<EditText>(R.id.text_field_email)
		text_field_password = findViewById<EditText>(R.id.text_field_password)
		_bg__logo_ek1 = findViewById<View>(R.id._bg__logo_ek1)

		login_btn.setOnClickListener {
			val URL = "http://185.69.154.93/api/auth/login"
			if (URL.isNotEmpty()) {
				val fetchData = OkHttpClient()
				val formBody = FormBody.Builder()
					.add("email", text_field_email.text.toString())
					.add("password", text_field_password.text.toString())
					.build()

				val request = Request.Builder()
					.url(URL)
					.post(formBody)
					.build()

				fetchData.newCall(request).enqueue(object : Callback {
					override fun onFailure(call: Call, e: IOException) {
						e.printStackTrace()
					}

					override fun onResponse(call: Call, response: Response) {
						response.use {
							if (!response.isSuccessful) {
								Log.e("TAG", "Request failed with status code: ${response.code}")
								Log.e("TAG", "Response body: ${response.body?.string()}")
							} else {
								val body = response.body?.string()
								val jsonObject = JSONObject(body.toString())
								Log.i("TAG", jsonObject.getString("token"))
							}
						}
					}
				})
			} else {
				println("Url was empty")
			}
		}
	}
}
