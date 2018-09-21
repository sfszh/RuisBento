package co.ruizhang.ruisbento.presentation

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    viewModel { RepositoryListViewModel(get()) }
}