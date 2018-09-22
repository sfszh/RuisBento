package co.ruizhang.ruisbento

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import co.ruizhang.ruisbento.presentation.RepositoryListViewModel
import co.ruizhang.ruisbento.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: RepositoryListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.repoList.observe(this, Observer { repoList ->
            val str = repoList.asSequence().map { it.name }.reduce { acc, s -> acc + s }
            binding.about.text = str

        })
        viewModel.load()
    }

//    private fun loadDataFromUI() = launch {
//        // <- extension on current activity, launched in the main thread
//        val ioData = withContext(Dispatchers.Default) {
//            repo.loadRepo()
//        }
//        // do something else concurrently with I/O
//        val data = ioData.await() // wait for result of I/O
//        Timber.d("bentolog: hahah \n")
//        val str = data.asSequence().map { it.name }.reduce { acc, s -> acc + s }
//        binding.about.text = str
//    }


}

