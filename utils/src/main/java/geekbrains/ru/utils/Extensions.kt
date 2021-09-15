package geekbrains.ru.utils

import android.content.Context
import android.text.Editable
import android.widget.Toast

fun String.Companion.getEmptyString(): String = ""

fun getStringFromEditable(string: Editable): String {
    return string.toString()
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
