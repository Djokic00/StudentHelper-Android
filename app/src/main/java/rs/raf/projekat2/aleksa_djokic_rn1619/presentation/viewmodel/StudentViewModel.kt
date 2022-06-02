package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Resource
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.NoteRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.SubjectRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.NoteState
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.SubjectState
import timber.log.Timber
import java.util.regex.Pattern

class StudentViewModel (
    private val subjectRepository: SubjectRepository,
    private val noteRepository: NoteRepository
) : ViewModel(), StudentContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val subjectState: MutableLiveData<SubjectState> = MutableLiveData()
    override val noteState: MutableLiveData<NoteState> = MutableLiveData()

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
        val subscription = subjectRepository
            .fetchAll()
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
        val subscription = subjectRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjectState.value = SubjectState.Success(it)
                },
                {
                    subjectState.value = SubjectState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllGroupsAndDays() {
        // val setOfGroups = mutableSetOf<String>()
        val subscription = subjectRepository
            .getGroupAndDay()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
//                    it.forEach {
//                        val group = it.grupe
//                        val delimiter = "[,]{1}[\\s]?"
//                        val array = Pattern.compile(delimiter).split(group)
//                        for (s in array) {
//                            setOfGroups.add(s)
//                        }
//                    }
                    // subjectState.value = SubjectState.Success(it)
                },
                {
                    subjectState.value = SubjectState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNotes() {
        TODO("Not yet implemented")
    }

    override fun saveNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun editNote(id: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(id: Int) {
        TODO("Not yet implemented")
    }

}