package ru.services.vertex.social.Fragments.Authentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.freezeframeteam.tourists.Constants
import ru.freezeframeteam.tourists.MainActivity
import ru.freezeframeteam.tourists.R
import ru.freezeframeteam.tourists.presenters.AuthPresenter
import ru.freezeframeteam.tourists.views.AuthView
import ru.services.vertex.social.Models.UserModels.UserModelForLogin


class LoginFragment : MvpAppCompatFragment(), AuthView {

    @InjectPresenter
    lateinit var presenter: AuthPresenter
    lateinit var userModel: UserModelForLogin
    lateinit var controller: NavController
    lateinit var progress: CardView
    lateinit var spinner: Spinner
    lateinit var phone: EditText
    lateinit var sendCode: TextView

    var phoneList = arrayOf("7", "+4", "+2", "+9")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller =(activity as MainActivity).navHostFragment.navController

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login, container, false)

        spinner = view.findViewById(R.id.spinner)
        phone = view.findViewById(R.id.phone)
        sendCode = view.findViewById(R.id.sendCode)
        val adapter = ArrayAdapter(
            context!!,
            R.layout.phone_spinner_item, R.id.t_service, phoneList
        )

        adapter.setDropDownViewResource(R.layout.item_spinner_custom)
        spinner.adapter = adapter
        spinner.setSelection(0)
        adapter.notifyDataSetChanged()
//        (activity as MainActivity).mBottomNavigationView.visibility = View.GONE
//        val login = view.findViewById<TextView>(R.id.go)
//        val reg = view.findViewById<TextView>(R.id.t_go_reg)
//        val eMail : EditText = view.findViewById(R.id.e_login)
//        val ePass : EditText = view.findViewById(R.id.e_pass)
//        progress = view.findViewById(R.id.progress)

//        login.setOnClickListener {
//            val s_email = eMail.text.toString()
//            val s_pass = ePass.text.toString()
//            userModel = UserModelForLogin(email = s_email, pass = s_pass)
//            presenter.login(userModel, context!!)
//        }
//        reg.setOnClickListener { presenter.goRegistration(controller) }

        sendCode.setOnClickListener {

            userModel = UserModelForLogin(adapter.getItem(spinner.selectedItemPosition) + phone.text.toString())
            Log.d("12345","отправляем код на номер: "+adapter.getItem(spinner.selectedItemPosition) + phone.text.toString());
            presenter.login(userModel, context!!)
        }
        return view
    }

    override fun startAction() {
//        progress.visibility = View.VISIBLE

    }

    override fun onSuccess() {
        activity!!.runOnUiThread{

        }

    }

    override fun onError(msg: Int) {
        activity!!.runOnUiThread{

        }
    }


}
