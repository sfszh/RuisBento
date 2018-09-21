package co.ruizhang.ruisbento.data.api.github

import kotlinx.coroutines.experimental.Deferred

interface RepositoryRepo {
    fun loadRepo(): Deferred<List<Repository>>
}

class RepositoryRepoImpl(private val api: RepositoryApi) : RepositoryRepo {
    override fun loadRepo(): Deferred<List<Repository>> {
        return api.loadRepo()
    }
}