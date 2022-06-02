package rs.raf.projekat2.aleksa_djokic_rn1619.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.StudentDB
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.remote.StudentService
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.StudentRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.StudentRepositoryImpl
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel

val studentModule = module {

    viewModel { StudentViewModel(studentRepository = get()) }

    single<StudentRepository> { StudentRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<StudentDB>().getStudentDao() }

    single<StudentService> { create(retrofit = get()) }
}