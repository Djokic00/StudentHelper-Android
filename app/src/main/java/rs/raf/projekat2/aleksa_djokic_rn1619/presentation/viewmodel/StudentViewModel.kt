package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Resource
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.StudentRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.SubjectState
import timber.log.Timber

class StudentViewModel (
    private val studentRepository: StudentRepository
) : ViewModel(), StudentContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val subjectState: MutableLiveData<SubjectState> = MutableLiveData()

//    init {
//        val subscription = publishSubject
//            //.debounce(200, TimeUnit.MILLISECONDS)
//            //.distinctUntilChanged()
//            .switchMap {
//                studentRepository
//                    .getAllNotes(it)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Publish Subject Error")
//                    }
//            }
//            .subscribe(
//                {
//                    recipeState.value = RecipeState.Success(it)
//                },
//                {
//                    recipeState.value = RecipeState.Error("Error getting data from db")
//                }
//            )
//        subscriptions.add(subscription)
//    }
//

    override fun fetchAllSubjects() {
        val subscription = studentRepository
            .fetchAllSubjects()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> subjectState.value = SubjectState.Loading
                        is Resource.Success -> subjectState.value = SubjectState.DataFetched
                        is Resource.Error -> subjectState.value = SubjectState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    subjectState.value = SubjectState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllSubjects() {
        TODO("Not yet implemented")
    }

}