package br.www.mycatwalk.com.ui.home



import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import java.util.Calendar
import java.util.Date

import br.www.mycatwalk.com.R
import br.www.mycatwalk.com.model.data.CurrentWeatherEntry
import br.www.mycatwalk.com.model.data.CurrentWeatherResponse
import br.www.mycatwalk.com.model.services.apixuWeatherApiService
import br.www.mycatwalk.com.ui.activities.MainActivity
import br.www.mycatwalk.com.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_calendario.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var homeViewModel: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {



        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calendario, container, false)

        val apiService = apixuWeatherApiService()
        GlobalScope.launch(Dispatchers.Main) {

            val currentWeatherResponse = apiService!!.getCurrent("lodon").await()
            text_home.text = currentWeatherResponse.humidity.toString()
        }
      //  val textView = root.findViewById(R.id.text_home)
     //   homeViewModel!!.text.observe(this, Observer { s -> textView.setText(s) })
        return root
    }
}