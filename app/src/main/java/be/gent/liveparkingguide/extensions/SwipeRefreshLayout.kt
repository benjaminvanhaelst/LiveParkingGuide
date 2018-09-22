package be.gent.liveparkingguide.extensions

import android.support.v4.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.start() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stop() {
    isRefreshing = false
}