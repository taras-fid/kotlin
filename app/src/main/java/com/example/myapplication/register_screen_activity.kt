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

class register_screen_activity : AppCompatActivity() {
	lateinit var register_btn: Button
	lateinit var text_field_email: EditText
	lateinit var text_field_password: EditText
	lateinit var text_field_nickname: EditText

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.register_screen)

		register_btn = findViewById<Button>(R.id.register_btn)
		text_field_email = findViewById<EditText>(R.id.text_field_email)
		text_field_password = findViewById<EditText>(R.id.text_field_password)
		text_field_nickname = findViewById<EditText>(R.id.text_field_nickname)

		register_btn.setOnClickListener {
			val URL = "http://185.69.154.93/api/auth/register"
			if (URL.isNotEmpty()) {
				val fetchData = OkHttpClient()
				val formBody = FormBody.Builder()
					.add("email", text_field_email.text.toString())
					.add("nickname", text_field_nickname.text.toString())
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
								Log.i("TAG", body.toString())
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
