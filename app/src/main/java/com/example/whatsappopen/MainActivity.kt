package com.example.whatsappopen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var num:String = "0"//To save number
        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            num = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()  //put value of selected text
        }
         startWhatsapp(num)

    }
    private fun startWhatsapp(num:String){
        val i =Intent(Intent.ACTION_VIEW) //Since we want to view whatsapp
        i.setPackage("com.whatsapp")  //Since we have to open whatsapp only
        //Only works if in format(by whtsapp) : + 91 99 87263782
        var data:String=num;
        if(num[0]=='+' && num.length == 13){
            data=num.substring(1)
        }
        else if(num.length == 10) {
            data = "91" + num
        }else if(num.length == 12 && num[0]=='9' && num[1]=='1'){
            data= num
        }
        else{
            Toast.makeText(this,"Please check your number",Toast.LENGTH_SHORT).show()
            finish()
        }
        i.data= Uri.parse("https://wa.me/$data")  //mob number with country code
        //checking if whatsapp installed
        if(packageManager.resolveActivity(i,0)!=null){
            startActivity(i)
        }
        else{
            Toast.makeText(this,"Please Install Whatsapp",Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}