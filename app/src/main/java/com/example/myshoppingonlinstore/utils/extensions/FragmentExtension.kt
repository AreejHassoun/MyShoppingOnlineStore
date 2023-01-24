package com.example.myshoppingonlinstore.utils.extensions

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myshoppingonlinstore.ui.main.MainActivity
import com.example.myshoppingonlinstore.R


fun Fragment.changeStatusBarColor(color: Int) {
    val activity = (activity as MainActivity?)
    activity?.let {
        it.window?.run {
            statusBarColor = ContextCompat.getColor(it, color)
        }
    }
}

/**
 * getNavController
 *
 * Return main navigation controller for the application.
 *
 * @return Instance of the application navigation controller.
 */
fun Fragment.getNavController(): NavController? {
    val activity = this.activity

    activity?.let {
        return Navigation.findNavController(it, R.id.nav_host_fragment)
    }

    return null
}


/**
 * Show message in dialog.
 *
 * @param message  The message to display in dialog.
 */
//TODO
/*fun Fragment.showMessageDialog(message: String?) {
    message?.let { nonNullMessage ->
        val activity = this.activity

        activity?.let {
            // Inflate layout for dialog.
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = it.layoutInflater
            val view = inflater.inflate(R.layout.dialog_message, null)

            // Get dialog views.
            val messageTV = view.findViewById<AppCompatTextView?>(R.id.tv_message)
            val okBT = view.findViewById<MaterialButton?>(R.id.bt_ok)

            // Fill data.
            messageTV?.text = nonNullMessage.trim()

            // Create the dialog.
            val dialog = builder.setView(view).create()

            // Click events.
            okBT?.setOnClickListener { dialog.dismiss() }

            // Set dialog background and show it.
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
    }
}
*/

/**
 * Show message in toast
 *
 * @param message  the message to display in toast
 */

fun Fragment.showMessageToast(message: String) {
    message?.let { nonNullMessage ->
        val context = this.context
        context?.let {
            Toast.makeText(it, nonNullMessage.trim(), Toast.LENGTH_LONG).show()
        }
    }
}


/**
 * Check if given permission is granted.
 *
 * @param permission The permission to check.
 *
 * @return Boolean indicating if permissions is granted or not.
 */
fun Fragment.isPermissionGranted(permission: String): Boolean {
    return when (val context = this.context) {
        null -> false

        else -> ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}

fun isAllPermissionsGranted(grantResults: Map<String, Boolean>): Boolean {
    var isGranted = true

    for (grantResult in grantResults) {
        isGranted = grantResult.value

        if (!isGranted) {
            break
        }
    }

    return isGranted
}
