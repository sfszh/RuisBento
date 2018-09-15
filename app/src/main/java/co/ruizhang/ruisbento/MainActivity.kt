package co.ruizhang.ruisbento

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.ruizhang.ruisbento.data.api.github.*
import co.ruizhang.ruisbento.databinding.ActivityMainBinding
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import org.koin.android.ext.android.inject
import timber.log.Timber
import kotlin.coroutines.experimental.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    private lateinit var binding: ActivityMainBinding

    private val repo : RepositoryRepo by inject()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        job = Job()
        Timber.d("bentolog: start")
        loadDataFromUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Cancel job on activity destroy. After destroy all children jobs will be cancelled automatically
    }

    private fun loadDataFromUI() = launch {
        // <- extension on current activity, launched in the main thread
        val ioData = withContext(Dispatchers.Default) {
            repo.loadRepo()
        }
        // do something else concurrently with I/O
        val data = ioData.await() // wait for result of I/O
        Timber.d("bentolog: hahah \n")
        val str = data.asSequence().map { it.name }.reduce { acc, s -> acc + s }
        binding.about.text = str
    }


}

