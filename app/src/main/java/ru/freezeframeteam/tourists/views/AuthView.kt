package ru.freezeframeteam.tourists.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndStrategy::class)
interface AuthView : MvpView {

    fun startAction()
    fun onSuccess()
    fun onError(msg : Int)

    }