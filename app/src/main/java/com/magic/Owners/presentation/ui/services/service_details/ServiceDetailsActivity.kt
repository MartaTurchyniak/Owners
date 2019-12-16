package com.magic.Owners.presentation.ui.services.service_details

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.magic.Owners.R
import com.magic.Owners.domain.models.Services
import kotlinx.android.synthetic.main.activity_service_details.*

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
private const val EXTRAS_SERVICE = "service"
class ServiceDetailsActivity: AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)
        initUI()
    }

    private fun initUI() {
        val services: Services = intent.getSerializableExtra(EXTRAS_SERVICE) as Services
        tvTitle.text = services.title
        tvDescription.text = services.description
        tvMailContent.text = services.email
        initWebsite(services)
        tvPhoneNumber.text = services.phone
        initServicePicture(services)
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initServicePicture(services: Services) {
        try {
            val res = R.drawable::class.java
            val field = res.getField(services.detailedPhoto)
            val drawableId = field.getInt(null)
            ivService.setImageResource(drawableId)
        } catch (e: Exception) {
            Log.e("MyTag", "Failure to get drawable id.", e)
        }
    }

    private fun initWebsite(services: Services) {
        if (services.website == null || services.website.isEmpty()) {
            tvWebsiteTitle.visibility = View.GONE
            tvWebsiteLink.visibility = View.GONE
        } else {
            tvWebsiteLink.text = services.website
            tvWebsiteTitle.visibility = View.VISIBLE
            tvWebsiteLink.visibility = View.VISIBLE
        }
    }

    companion object{

        fun startServiceDetailsActivity(context: Context, service: Services){
            val intent = Intent(context, ServiceDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(EXTRAS_SERVICE, service)
            intent.putExtras(bundle)
            context.startActivity( intent)
        }
    }
}