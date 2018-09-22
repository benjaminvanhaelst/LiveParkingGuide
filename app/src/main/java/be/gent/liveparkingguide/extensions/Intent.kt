package be.gent.liveparkingguide.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.singleTop

/**
 * Created by bvanhaelst
 */
inline fun <reified T : Any> android.support.v4.app.Fragment.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {
    activity?.launchActivity<T>(options, init)
}

inline fun <reified T : Any> AnkoContext<*>.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {
    ctx.launchActivity<T>(options, init)
}

inline fun <reified T : Any> Context.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    intent.clearTop().singleTop()
    startActivity(intent, options)

}


inline fun <reified T : Any> newIntent(context: Context): Intent {
    return Intent(context, T::class.java)
}
