package ru.services.vertex.social.Fragments.Authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.freezeframeteam.technicalchat.common.WelcomeModel
import ru.freezeframeteam.technicalchat.common.WelcomePagerAdapter
import ru.freezeframeteam.tourists.Constants
import ru.freezeframeteam.tourists.MainActivity
import ru.freezeframeteam.tourists.presenters.AuthPresenter
import ru.freezeframeteam.tourists.R
import ru.freezeframeteam.tourists.views.AuthView
import ru.services.vertex.social.Models.UserModels.UserModelForLogin
import java.util.ArrayList


class WelcomeFragment : Fragment() {
    lateinit var viewpager: ViewPager
    lateinit var stepView: TextView
    var models: ArrayList<WelcomeModel> = ArrayList()
    var step: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).mBottomNavigationView.visibility = View.GONE
        val view = inflater.inflate(R.layout.wellcome, container, false)
        var next: Button = view.findViewById(R.id.next)
        stepView = view.findViewById(R.id.step)
        viewpager = view.findViewById(R.id.viewpager)
        models.add(0, WelcomeModel(resources.getDrawable(R.drawable.ic_welcome_1), "1/4", resources.getString((R.string.step1))))
        models.add(1, WelcomeModel(resources.getDrawable(R.drawable.ic_welcome_2), "2/4", resources.getString((R.string.step2))))
        models.add(2, WelcomeModel(resources.getDrawable(R.drawable.ic_welcome_3), "3/4", resources.getString((R.string.step3))))
        models.add(3, WelcomeModel(resources.getDrawable(R.drawable.ic_welcome_4), "4/4", resources.getString((R.string.step4))))
        val ofVpAdapter = WelcomePagerAdapter(models, context!!)
        viewpager.adapter = ofVpAdapter
        viewpager.beginFakeDrag()
        next.setOnClickListener {
            if (step <3) {
                step++
                updateStep()
            } else if (step == 3) {
                (activity as MainActivity).navHostFragment.navController.navigate(R.id.action_global_loginFragment)
//                finish()
//                val intent = Intent(this, StartActivity::class.java)
//                startActivity(intent)

            }
            Log.d("12345", "step: " + step)
            viewpager.setCurrentItem(step, true)

        }

        val thresholdOffset = 0.5f
        var scrollStarted: Boolean = false
        var checkDirection: Boolean = false

//
//        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                if (checkDirection) {
//                    if (thresholdOffset > positionOffset) {
//                        Log.d("12345", "going left");
//                        step ++
//                    } else {
//                        Log.d("12345", "going right");
//                        step --
//                    }
//                    checkDirection = false;
//                }
//                updateStep()
//            }
//
//            override fun onPageSelected(position: Int) {
//
//                //step = position
//                Log.d("12345", "position: " + position)
//                if (step != 3) {
//                    next.text = "Продолжить"
//                } else {
//                    next.text = "Перейти к авторизации"
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
//                    scrollStarted = true;
//                    checkDirection = true;
//                } else {
//                    scrollStarted = false;
//                }
//            }
//
//        })

        return view
    }

    private fun updateStep() {
        if (step<=3) {
            stepView.text = resources.getString(R.string.step) + " " + models[step].step
        }
    }
}
