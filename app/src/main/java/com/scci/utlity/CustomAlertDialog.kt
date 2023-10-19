package com.scci.utlity

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.scci.democalculator.R
import com.scci.democalculator.databinding.CustomAlertDialogLayoutBinding

class CustomAlertDialog(context: Context) {

    var dialog: CustomAlertDialog
    var isShowing = false
    private lateinit var customAlertInterface: CustomAlertInterface

    private lateinit var binding: CustomAlertDialogLayoutBinding


    init {
        dialog = CustomAlertDialog(context)
        dialog.setCancelable(false)
        dialog.setOnCancelListener {
            isShowing = false;
        }

    }

    fun show(context: Context,customAlertInterface: CustomAlertInterface): Dialog {
        this.customAlertInterface = customAlertInterface
        return show(context)
    }


    fun show(context: Context): Dialog {
        val inflater = (context as AppCompatActivity).layoutInflater
        binding = CustomAlertDialogLayoutBinding.inflate(inflater)

        Glide.with(context).load(R.drawable.gift).into(binding.ivGift);

        Handler().postDelayed(Runnable {
            dialog.dismiss()
        },2000)

        if (dialog.isShowing()) {
            dialog.cancel()
        }
        dialog.setContentView(binding.root)
        if (context != null && !context.isFinishing()) {
            dialog.show()
        }


        isShowing = true
        return dialog
    }


    class CustomAlertDialog(context: Context) : Dialog(context,R.style.ProgressDialogTheme) {
        init {
            window?.decorView?.rootView?.setBackgroundResource(R.color.semi_transparent)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                window?.decorView?.setOnApplyWindowInsetsListener { _,insets ->
                    insets.consumeSystemWindowInsets()
                }
            }
        }
    }

    interface CustomAlertInterface {
        fun onCancel()

    }

}