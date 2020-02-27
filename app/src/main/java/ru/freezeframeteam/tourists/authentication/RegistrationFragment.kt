package ru.services.vertex.social.Fragments.Authentication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.freezeframeteam.tourists.MainActivity
import ru.freezeframeteam.tourists.presenters.AuthPresenter
import ru.freezeframeteam.tourists.R
import ru.freezeframeteam.tourists.views.AuthView
import ru.services.vertex.social.Models.UserModels.UserModelForReg

class RegistrationFragment : MvpAppCompatFragment(), AuthView {

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    lateinit var progress: CardView
    lateinit var controller: NavController

    lateinit var model : UserModelForReg

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

//        val eMail : EditText = view.findViewById(R.id.e_login)
//        val ePass : EditText = view.findViewById(R.id.e_pass)
//        val eUsername : EditText = view.findViewById(R.id.e_username)
//        val go : TextView = view.findViewById(R.id.go)
//        progress = view.findViewById(R.id.progress)
        controller = (activity as MainActivity).navHostFragment.navController

//        go.setOnClickListener {
//            model = UserModelForReg(username = eUsername.text.toString().trim(), email = eMail.text.toString().trim(), pass = ePass.text.toString().trim())
//            presenter.registration(model, context!!)
//        }

        return view
    }
    override fun startAction() {
        progress.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        activity!!.runOnUiThread{
            progress.visibility = View.GONE
            presenter.onSuccesRegistrationNavigate(controller)
        }
    }

    override fun onError(msg: Int) {
        activity!!.runOnUiThread{
            progress.visibility = View.GONE
        }

    }


}
