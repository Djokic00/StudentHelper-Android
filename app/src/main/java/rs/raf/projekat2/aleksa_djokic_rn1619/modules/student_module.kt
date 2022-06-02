package rs.raf.projekat2.aleksa_djokic_rn1619.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.local.StudentDB
import rs.raf.projekat2.aleksa_djokic_rn1619.data.datasource.remote.StudentService
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.NoteRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.NoteRepositoryImpl
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.SubjectRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.SubjectRepositoryImpl
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel

val studentModule = module {

    viewModel { StudentViewModel(subjectRepository = get(), noteRepository = get()) }

    single<SubjectRepository> { SubjectRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single<NoteRepository> { NoteRepositoryImpl(localDataSource = get()) }

    single { get<StudentDB>().getStudentDao() }

    single<StudentService> { create(retrofit = get()) }
}