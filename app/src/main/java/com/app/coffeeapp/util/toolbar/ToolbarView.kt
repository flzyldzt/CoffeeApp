package com.app.coffeeapp.util.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.app.coffeeapp.databinding.ViewCustomToolbarBinding

class ToolbarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewCustomToolbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var properties = ToolbarProperties(type = ToolbarType.GONE)

    fun setProperties(properties: ToolbarProperties) {
        this@ToolbarView.properties = properties
        initUi()
    }

    private fun initUi() = with(binding) {
        if (properties.type == ToolbarType.GONE) {
            toolbar.isVisible = false
        } else {
            toolbar.isVisible = true
            tvTitle.text = properties.title

            ivBack.isVisible = when (properties.type) {
                ToolbarType.BACK_ONLY,
                ToolbarType.BACK_WITH_TITLE,
                ToolbarType.BACK_WITH_CLOSE -> true

                else -> false
            }

            ivClose.isVisible = when (properties.type) {
                ToolbarType.CLOSE_ONLY,
                ToolbarType.CLOSE_WITH_TITLE,
                ToolbarType.BACK_WITH_CLOSE -> true

                else -> false
            }

            properties.backgroundColor?.let {
                toolbar.setBackgroundColor(
                    ContextCompat.getColor(context, it)
                )
            }
        }
    }

    fun onBackClick(action: () -> Unit) {
        binding.ivBack.setOnClickListener { action() }
    }

    fun onCloseClick(action: () -> Unit) {
        binding.ivClose.setOnClickListener { action() }
    }
}