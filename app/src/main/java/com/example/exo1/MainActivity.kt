package com.example.exo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var AnneeClimatique = arrayOfNulls<Mois>(12)
    private lateinit var txtTmMin:EditText
    private lateinit var txtTmMax:EditText
    private lateinit var txtPmTot:EditText

    private lateinit var btnPta:Button
    private lateinit var btnPtma:Button
    private lateinit var btnTmin:Button
    private lateinit var btnTmax:Button

    private lateinit var lblPta:TextView
    private lateinit var lblPtma:TextView
    private lateinit var lblTmin:TextView
    private lateinit var lblTmax:TextView

    private  var tempTmax:String = ""
    private  var tempTmin:String = ""
    private  var tempPtot:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mois = arrayOf<String>("Janvier","Fevrier","Mars","Avril","Mai","Juin","Juille","Août","Septembre","Octobre","Novembre","Decembre")
        var index:Int=0;
        val btnRw:Button = findViewById(R.id.btnRw)
        val btnFw:Button = findViewById(R.id.btnFw)
        val btnAdd:Button = findViewById(R.id.btnAdd)
        val lblMois:TextView = findViewById(R.id.lblMois)

        lblMois.text = "Janvier"

        txtTmMin = findViewById(R.id.txtTmMin);
        txtTmMax = findViewById(R.id.txtTmMax)
        txtPmTot = findViewById(R.id.txtPmTot)


        btnPta = findViewById(R.id.btnPta)
        btnPtma = findViewById(R.id.btnPtma)
        btnTmax = findViewById(R.id.btnTmax)
        btnTmin = findViewById(R.id.btnTmin)

        lblPta = findViewById<TextView>(R.id.lblPta)
        lblPtma = findViewById<TextView>(R.id.lblPtma)
        lblTmax = findViewById<TextView>(R.id.lblTmax)
        lblTmin = findViewById<TextView>(R.id.lblTmin)


        btnRw.setOnClickListener(View.OnClickListener {

            index--;
            Log.i("Mois 1 - = ",index.toString())
            if(index<0)
            {
                index=0
            }
            //Log.i("Mois - = ",mois[index])
            lblMois.text = mois[index]

            var val1=AnneeClimatique[index]?.tmMax.toString()
            var tmax = if (val1.equals("null")) "" else val1
            txtTmMax.setText(tmax)
            tempTmax = tmax

            var val2=AnneeClimatique[index]?.tmMin.toString()
            var tmin = if (val2.equals("null")) "" else val2
            txtTmMin.setText(tmin)
            tempTmin = tmin


            var val3=AnneeClimatique[index]?.pmTot.toString()
            var pTot = if (val3.equals("null")) "" else val3
            txtPmTot.setText(pTot)
            tempPtot = pTot


        })

        btnFw.setOnClickListener(View.OnClickListener {

            index++;
            Log.i("Mois 2 - = ",index.toString())
            if(index>11)
            {
                index=11
            }
            //Log.i("Mois + = ",mois[index])
            lblMois.text = mois[index]

            var val1=AnneeClimatique[index]?.tmMax.toString()
            var tmax = if (val1.equals("null")) "" else val1
            txtTmMax.setText(tmax)
            tempTmax = tmax

            var val2=AnneeClimatique[index]?.tmMin.toString()
            var tmin = if (val2.equals("null")) "" else val2
            txtTmMin.setText(tmin)
            tempTmin = tmin


            var val3=AnneeClimatique[index]?.pmTot.toString()
            var pTot = if (val3.equals("null")) "" else val3
            txtPmTot.setText(pTot)
            tempPtot = pTot
        })



        btnAdd.setOnClickListener(View.OnClickListener {

            var mois = lblMois.text.toString()

            if (valTxtTmMin() && valTxtPmTot() && valTxtTmMax()){
                var tMin = txtTmMin.text.toString().toDouble()
                var tMax = txtTmMax.text.toString().toDouble()
                var Ptot = txtPmTot.text.toString().toDouble()

                var val2=AnneeClimatique[index]?.nb

                if (AnneeClimatique[index]?.nb==index){
                    val msgAlert = AlertDialog.Builder(this)
                    msgAlert.setTitle("Le mois contient deja des valeurs")
                    msgAlert.setMessage("voulez-vous le changer?")

                    msgAlert.setPositiveButton("Oui"){dialog, which ->
                        AnneeClimatique[index]?.tmMax= tMax
                        AnneeClimatique[index]?.tmMin= tMin
                        AnneeClimatique[index]?.pmTot= Ptot
                        txtTmMin.setText(tMin.toString())
                        txtTmMax.setText(tMax.toString())
                        txtPmTot.setText(Ptot.toString())
                        tempTmax = tMax.toString()
                        tempTmin = tMin.toString()
                        tempPtot = Ptot.toString()
                    }

                    msgAlert.setNegativeButton("Non") { dialog, which ->
                        txtTmMin.setText(tempTmin.toString())
                        txtTmMax.setText(tempTmax.toString())
                        txtPmTot.setText(tempPtot.toString())

                    }

                    msgAlert.show()
                    //Toast.makeText(this@MainActivity,"le valeur existe deja",Toast.LENGTH_SHORT).show()
                }
                else{
                    var m = Mois(index,mois,tMin,tMax, Ptot )
                    AnneeClimatique[index]=m
                    tempTmax = tMax.toString()
                    tempTmin = tMin.toString()
                    tempPtot = Ptot.toString()
                    txtTmMin.setText(tMin.toString())
                    txtTmMax.setText(tMax.toString())
                    txtPmTot.setText(Ptot.toString())
                    Toast.makeText(this@MainActivity,"le valeur ajoute",Toast.LENGTH_SHORT).show()
                }

            }


        })


        btnPta.setOnClickListener(View.OnClickListener {
            var total:Double = 0.0
            var cont:Int=0;
            for(i in AnneeClimatique)
            {
                if(!i?.pmTot.toString().equals("null"))
                {
                    cont++
                    total += i?.pmTot.toString().toDouble()
                }

            }
            if(cont>0) lblPta.text =total.toString() else Toast.makeText(this@MainActivity,"Il n'y a pas de donnees",Toast.LENGTH_SHORT).show()
        })

        btnPtma.setOnClickListener(View.OnClickListener {
            var total:Double = 0.0
            var cont:Int=0;
            for(i in AnneeClimatique)
            {
                if(!i?.pmTot.toString().equals("null"))
                {
                    cont++
                    total += i?.pmTot.toString().toDouble()
                }

            }
            if(cont>0) lblPtma.text =  (total/cont).toString()  else Toast.makeText(this@MainActivity,"Il n'y a pas de donnees",Toast.LENGTH_SHORT).show()
        })

        btnTmax.setOnClickListener(View.OnClickListener {
            var max=-100.0
            var cont:Int=0;
            var mois:String=""
            for(i in AnneeClimatique)
            {
                var dat= i?.tmMax.toString()

                if(!dat.equals("null"))
                {
                    cont++
                    if(dat.toDouble()>max){
                        max=dat.toDouble()
                        mois=i?.nom.toString()
                    }
                }
            }
            if(cont>0) lblTmax.text = (max.toString() +" dans le mois de "+mois)  else Toast.makeText(this@MainActivity,"Il n'y a pas de donnees",Toast.LENGTH_SHORT).show()
        })

        btnTmin.setOnClickListener(View.OnClickListener {
            var min=100.0
            var cont:Int=0;
            var mois:String=""
            for(i in AnneeClimatique)
            {
                var dat= i?.tmMin.toString()

                if(!dat.equals("null"))
                {
                    cont++
                    if(dat.toDouble()<min){
                        min=dat.toDouble()
                        mois=i?.nom.toString()
                    }
                }
            }
            if(cont>0) lblTmin.text = (min.toString() +" dans le mois de "+mois) else Toast.makeText(this@MainActivity,"Il n'y a pas de donnees",Toast.LENGTH_SHORT).show()
        })

        /*
              txtTmMin.setOnEditorActionListener { v, KeyCode, event ->
                  if (event.action == KeyEvent.KEYCODE_DPAD_CENTER)
                  {
                      if (!txtTmMin.text.toString().equals("")){
                          var tmin = txtTmMin.text.toString().toDouble();
                          if(tmin<-30){
                              Toast.makeText(this@MainActivity,"TmMin doit être superior à -30",Toast.LENGTH_SHORT).show()
                             // txtTmMin.error = "valor error"
                          }
                      }
                  }
                 true
              }



              txtTmMin.setOnFocusChangeListener{_, hasFocus ->
                  if (!txtTmMin.text.toString().equals("")){
                      var tmin = txtTmMin.text.toString().toDouble();
                      if(tmin<-30){
                          Toast.makeText(this@MainActivity,"TmMin doit être superior à -30",Toast.LENGTH_SHORT).show()
                          txtTmMin.requestFocus();
                          txtTmMin.setSelection(txtTmMin.text.length)
                      }
                  }
              }

              txtTmMax.setOnFocusChangeListener{_, hasFocus ->
                  if (!txtTmMax.text.toString().equals("")){
                      var tmax = txtTmMax.text.toString().toDouble();
                      if(tmax>30){
                          txtTmMax.requestFocus();
                          txtTmMax.setSelection(txtTmMax.text.length)
                      }
                  }
              }*/


        /*

        txtTmMin.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (!txtTmMin.text.toString().equals("")){
                    var tmin = txtTmMin.text.toString().toDouble();
                    if(tmin<-30){
                        Toast.makeText(this@MainActivity,"TmMin doit être superior à -30",Toast.LENGTH_SHORT).show()
                    }
                }

            }

        })*/


    }

    //fun addVal(view: View) {

    //}



    fun valTxtTmMin():Boolean
    {
        var isValid = true
        if (!txtTmMin.text.toString().equals("")){
            try {
                var tMin = txtTmMin.text.toString().toDouble()
                if(tMin<-30){
                    txtTmMin.error ="le valeur doit être superior ou egal -30"
                    isValid=false
                }
            }
            catch (ex: Exception){
                isValid=false
                txtTmMin.error = "Erreur du format"
            }
        }
        else{
            txtTmMin.error ="Valor est vide"
            isValid=false
        }

        return isValid
    }

    fun valTxtTmMax():Boolean
    {
        var isValid = true
        if (!txtTmMax.text.toString().equals("")){
            try {
                var tMin = txtTmMax.text.toString().toDouble()
                if(tMin>30){
                    txtTmMax.error ="le valeur doit être inferior ou egal 30"
                    isValid=false
                }
            }
            catch (ex: Exception){
                isValid=false
                txtTmMax.error = "Erreur du format"
            }
        }
        else{
            txtTmMax.error ="Valor est vide"
            isValid=false
        }

        return isValid
    }

    fun valTxtPmTot():Boolean
    {
        var isValid = true
        if (!txtPmTot.text.toString().equals("")){
            try {
                var tMin = txtPmTot.text.toString().toDouble()
                if(!(tMin>=0 && tMin<=200)){
                    txtPmTot.error ="le valeur doit être entre 0 et 200mm"
                    isValid=false
                }
            }
            catch (ex: Exception){
                isValid=false
                txtPmTot.error = "Erreur du format"
            }
        }
        else{
            txtPmTot.error ="Valor est vide"
            isValid=false
        }


        return isValid
    }
}