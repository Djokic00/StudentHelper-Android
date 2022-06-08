package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteEntity
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Resource
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.NoteRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.data.repositories.SubjectRepository
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.NoteState
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.SubjectState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class StudentViewModel (
    private val subjectRepository: SubjectRepository,
    private val noteRepository: NoteRepository
) : ViewModel(), StudentContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val subjectState: MutableLiveData<SubjectState> = MutableLiveData()
    override val noteState: MutableLiveData<NoteState> = MutableLiveData()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()
    // private val publishSubject1: PublishSubject<CourseFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .filter(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Publish Subject Error")
                    }
            }
            .subscribe(
                {
                    noteState.value = NoteState.Success(it)
                },
                {
                    noteState.value = NoteState.Error("Error getting data from db")
                }
            )
        subscriptions.add(subscription)
    }


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

    override fun filterSubject(group: String, day: String, text: String) {
        val subscription = subjectRepository
            .filter(group, day, text)
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


    override fun getAllNotes() {
        val subscription = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    noteState.value = NoteState.Success(it)
                },
                {
                    subjectState.value = SubjectState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun saveNote(note: Note) {
        val subscription = noteRepository
            .save(
                NoteEntity(
                    id = 0,
                    title = note.title,
                    content = note.content,
                    archive = false,
                    date = note.date
                )
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Saved")
                },
                {
                    noteState.value = NoteState.Error("Error updating db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun editNote(id: Int, newTitle: String, newContent: String) {
        val subscription = noteRepository
            .edit(id, newTitle, newContent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Edited")
                },
                {
                    noteState.value = NoteState.Error("Error updating db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteNote(id: Int) {
        val subscription = noteRepository
            .delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Deleted")
                },
                {
                    noteState.value = NoteState.Error("Error updating db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun changeNoteState(id: Int) {
        val subscription = noteRepository
            .changeState(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Archive")
                },
                {
                    noteState.value = NoteState.Error("Error updating db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getNoteByFilter(text: String) {
        publishSubject.onNext(text)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}