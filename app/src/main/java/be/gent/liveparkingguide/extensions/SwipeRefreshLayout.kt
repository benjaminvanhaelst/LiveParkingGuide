package be.gent.liveparkingguide.extensions

import android.support.v4.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.start( onstart : () -> Unit) {
    isRefreshing = true
    onstart()
}

fun SwipeRefreshLayout.stop() {
    isRefreshing = false
}