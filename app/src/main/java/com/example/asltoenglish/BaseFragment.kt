package com.example.asltoenglish

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Created by zachcole on 6/13/16.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        if (shouldShowActionBar()) {
            (activity as AppCompatActivity).supportActionBar!!.show()
        } else {
            (activity as AppCompatActivity).supportActionBar!!.hide()
        }
    }

    // Helpers

    /***
     * Use this method if you need to intercept onTabSelect
     *
     * @return true if you want to intercept, false if you want the Activity to continue normally
     */

    fun shouldShowActionBar(): Boolean {
        return true
    }

    abstract fun titleForActionBar(): String
}
