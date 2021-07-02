package github.sachin2dehury.chucknorris

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

const val BASE_URL = "https://api.chucknorris.io/jokes/"