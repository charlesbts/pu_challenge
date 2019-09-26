package br.com.pu.pu_challenge.presentation.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import br.com.pu.pu_challenge.R
import br.com.pu.pu_challenge.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*

abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction { add(R.id.fragmentContainer, fragment()) }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

    abstract fun fragment(): BaseFragment

    abstract fun layout(): Int
}