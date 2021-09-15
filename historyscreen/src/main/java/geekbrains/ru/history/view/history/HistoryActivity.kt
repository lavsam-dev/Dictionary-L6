package geekbrains.ru.history.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import geekbrains.ru.core.BaseActivity
import geekbrains.ru.history.R
import geekbrains.ru.history.injectDependencies
import geekbrains.ru.model.data.AppState
import geekbrains.ru.model.data.DataModel
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val SEARCH_WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b1"

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        val bundle = intent.extras
        var word = bundle?.getString(SEARCH_WORD_EXTRA)
        if (word == null) word = ""
//        var word = "run"
        model.getData(word, false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        check(history_activity_recyclerview.adapter == null) { "The ViewModel should be initialised first" }
        injectDependencies()
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }

    companion object {
        private const val SEARCH_WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b1"
    }
}
