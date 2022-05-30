package com.yomemo.yomovies.utils

import android.view.View
import dev.chrisbanes.insetter.windowInsetTypesOf

class Insets {
    companion object {
        fun addInset(view: View) {
            dev.chrisbanes.insetter.Insetter.builder()
                // This will add the navigation bars insets as padding to all sides of the view,
                // maintaining the original padding (from the layout XML, style, etc)
                .padding(windowInsetTypesOf(navigationBars = true))
                // This will add the status bars insets as margin to all sides of the view,
                // maintaining the original margins (from the layout XML, etc)`
                .margin(windowInsetTypesOf(statusBars = true))
                // This is a shortcut for view.setOnApplyWindowInsetsListener(builder.build())
                .applyToView(view)
        }
    }
}