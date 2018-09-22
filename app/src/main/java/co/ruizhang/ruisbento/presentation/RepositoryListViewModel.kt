package co.ruizhang.ruisbento.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.ruizhang.ruisbento.data.api.github.Repository
import co.ruizhang.ruisbento.data.api.github.RepositoryRepo
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import java.lang.Exception
import kotlin.coroutines.experimental.CoroutineContext


class RepositoryListViewModel(private val repo: RepositoryRepo) : ViewModel(), CoroutineScope {
    val repoList: LiveData<List<Repository>> get() = repoListOb
    private val repoListOb = MutableLiveData<List<Repository>>()
    val errorMessage: LiveData<String> get() = errorMessageOb
    private val errorMessageOb = MutableLiveData<String>()
    val isLoading: LiveData<Boolean> get() = isLoadingOb
    private val isLoadingOb = MutableLiveData<Boolean>()

    fun load() {
        launch {
            try {
                isLoadingOb.value = true
                val repoList = repo.loadRepo().await()
                repoListOb.value = repoList
            } catch (e: Exception) {
                errorMessageOb.value = e.localizedMessage
            } finally {
                isLoadingOb.value = false
            }

        }
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
