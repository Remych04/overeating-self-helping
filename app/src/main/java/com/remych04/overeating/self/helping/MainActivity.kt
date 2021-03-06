package com.remych04.overeating.self.helping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remych04.overeating.self.helping.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace

class MainActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator: Navigator = createNavigator()
    private var toolbarBackAction: (() -> Unit)? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.DayListFragmentScreen())))
        }

        toolbar.setNavigationOnClickListener {
            toolbarBackAction?.invoke()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun createNavigator(): Navigator {
        return object : SupportAppNavigator(this, R.id.fragment_container_view) {
            override fun applyCommand(command: Command) {
                super.applyCommand(command)
                supportFragmentManager.executePendingTransactions()
            }
        }
    }

    fun setToolbarBackAction(action: (() -> Unit)?) {
        toolbarBackAction = action
    }
}
