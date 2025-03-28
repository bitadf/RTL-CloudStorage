package com.example.pcstorage

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView

object ToggleArrows {

    fun setUpToggle(recycler: RecyclerView, arrowIcon: ImageView) {
        var isExpanded = true
        arrowIcon.setOnClickListener {
            if (isExpanded) {
                collapseRecyclerView(recycler, arrowIcon)
            } else {
                expandRecyclerView(recycler, arrowIcon)
            }
            isExpanded = !isExpanded
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun expandRecyclerView(recycler: RecyclerView, arrowIcon: ImageView) {
        recycler.visibility = View.VISIBLE
        if (recycler.adapter == null) return

        // Temporarily set height to WRAP_CONTENT and force layout
        recycler.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        recycler.requestLayout()

        // Wait for layout to complete
        recycler.post {
            // Ensure adapter binds all items
            recycler.adapter?.notifyDataSetChanged()
            recycler.layoutManager?.requestLayout()

            // Measure full height
            recycler.measure(
                View.MeasureSpec.makeMeasureSpec(recycler.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            val targetHeight = recycler.measuredHeight

            if (targetHeight > 0) {
                // Reset height to 0 and animate to targetHeight
                recycler.layoutParams.height = 0
                recycler.requestLayout()

                val animator = ValueAnimator.ofInt(0, targetHeight).apply {
                    duration = 300
                    interpolator = AccelerateInterpolator()
                    addUpdateListener { animation ->
                        recycler.layoutParams.height = animation.animatedValue as Int
                        recycler.requestLayout()
                    }
                }
                animator.start()
                arrowIcon.animate().rotation(180f).setDuration(300).start()
            }
        }
    }

    fun collapseRecyclerView(recycler: RecyclerView, arrowIcon: ImageView) {
        val initialHeight = recycler.height
        val animator = ValueAnimator.ofInt(initialHeight, 0).apply {
            duration = 300
            interpolator = AccelerateInterpolator()
            addUpdateListener { animation ->
                recycler.layoutParams.height = animation.animatedValue as Int
                recycler.requestLayout()
            }
            doOnEnd { recycler.visibility = View.GONE }
        }
        animator.start()
        arrowIcon.animate().rotation(0f).setDuration(300).start()
    }
}