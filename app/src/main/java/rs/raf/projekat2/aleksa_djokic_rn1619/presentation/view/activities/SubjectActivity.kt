package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivitySubjectBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.adapter.SubjectAdapter
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.SubjectState
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel
import timber.log.Timber
import java.util.regex.Pattern


class SubjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubjectBinding
    private val studentViewModel: StudentContract.ViewModel by viewModel<StudentViewModel>()
    private lateinit var adapter: SubjectAdapter
    private var flag : Boolean = false
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
        initRecycler()
        initListeners()
    }

    private fun initView() {
//        studentViewModel.fetchAllSubjects()
//        studentViewModel.getAllSubjects()
//        Timber.e("$setOfGroups")
    }

    private fun initObservers() {
        studentViewModel.subjectState.observe(this) {
            renderState(it)
        }
        // Prvi put kad pokrenemo aplikaciju podaci nece biti kesirani i zbog toga vraca praznu listu
        // studentViewModel.getAllSubjects() // prvo vraca praznu listu
        // studentViewModel.fetchAllSubjects() // zatim dohvatamo sa interneta i override ove koje se nalaze u bazi
        // kad se desi upis (deleteAndInsert) onda se opet okida query da se vrate svi podaci
        // sto ce biti observe-ovano gore i uci ce u metodu renderState(it)
        studentViewModel.getAllSubjects()
        studentViewModel.fetchAllSubjects()

    }

    private fun initListeners() {
        binding.searchButton.setOnClickListener {
            val group = binding.groupSpinner.selectedItem.toString()
            val day = binding.daySpinner.selectedItem.toString()
            val textField = binding.searchEt.text
            if (group != "Group" && day == "Day" && textField.isBlank()) {
                studentViewModel.filterSubjectsByGroup(group)
                Timber.e("1 - Po grupi")
            }
            else if (group == "Group" && day != "Day" && textField.isBlank()) {
                studentViewModel.filterSubjectByDay(day)
                    Timber.e("2 - Po danu")
            }
            else if (group != "Group" && day != "Day" && textField.isBlank()) {
                studentViewModel.filterSubjectByGroupAndDay(group, day)
                Timber.e("3 - Po grupi i danu")
            }
            else if (group != "Group" && day != "Day" && textField.isNotBlank()) {
                // filterByAll metoda
                Timber.e("RADI (3)")
            }
            else if (group != "Group" && day == "Day" && textField.isNotBlank()) {
                // filterByGroupAndField metoda
                Timber.e("RADI (4)")
            }
            else if (group == "Group" && day != "Day" && textField.isNotBlank()) {
                // filterByDayAndField metoda
                Timber.e("RADI (5)")
            }
            else if (group == "Group" && day == "Day" && textField.isNotBlank()) {
                // filterByField metoda
                Timber.e("RADI (6)")
            }
            else studentViewModel.getAllSubjects()
        }
    }

    private fun initRecycler() {
        binding.subjectRv.layoutManager = LinearLayoutManager(this)
        adapter = SubjectAdapter()
        binding.subjectRv.adapter = adapter

//        binding.categoryRv.layoutManager = LinearLayoutManager(this)
//        categoryAdapter = CategoryAdapter() {
//            println(it.title)
//            binding.categoryRv.visibility = View.GONE
//            binding.recipeRv.visibility = View.VISIBLE
//            binding.savedRecipeRv.visibility = View.GONE
//            recipeViewModel.deleteRecipes()
//            recipeViewModel.getRecipes(it.title)
//            recipeViewModel.getRecipesPage(it.title, "1")
//        }
//        binding.categoryRv.adapter = categoryAdapter
//        categoryAdapter.submitList(categories)
//
//        binding.recipeRv.layoutManager = LinearLayoutManager(this)
//        recipeAdapter = RecipeAdapter() {
//            val intent = Intent(this, RecipeDetailsActivity::class.java)
//            intent.putExtra("RECIPE", it)
//            startActivity(intent)
//        }
//        binding.recipeRv.adapter = recipeAdapter
//
//        binding.savedRecipeRv.layoutManager = LinearLayoutManager(this)
//        savedRecipeAdapter = SavedRecipeAdapter() {
//            val intent = Intent(this, SavedRecipeDetailsActivity::class.java)
//            intent.putExtra("RECIPE", it)
//            startActivity(intent)
//        }
//        binding.savedRecipeRv.adapter = savedRecipeAdapter
    }

    private fun renderState(state: SubjectState) {
        when (state) {
            is SubjectState.Success -> {
                showLoadingState(false)
                Timber.e("Success")
                adapter.submitList(state.subject)
                if (!flag) {
                    setSpinner(state.subject)
                    flag = true
                }
            }
            is SubjectState.Error -> {
                showLoadingState(false)
                Timber.e("Error")
            }
            is SubjectState.DataFetched -> {
                showLoadingState(false)
                Timber.e("Data fetched")
            }
            is SubjectState.Loading -> {
                showLoadingState(true)
                Timber.e("Loading")
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.groupSpinner.isVisible = !loading
        binding.daySpinner.isVisible = !loading
        binding.subjectRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    private fun setSpinner(subjects: List<Subject>) {
        val setOfGroups = mutableSetOf<String>()
        for (subject in subjects) {
            val delimiter = "[,]{1}[\\s]?"
            val array = Pattern.compile(delimiter).split(subject.grupe)
            for (s in array) {
                setOfGroups.add(s)
            }
        }
        val groupAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, ArrayList())
        groupAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.groupSpinner.adapter = groupAdapter
        groupAdapter.add("Group")
        groupAdapter.addAll(setOfGroups.toList().sorted())

        val dayAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, ArrayList())
        dayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.daySpinner.adapter = dayAdapter
        dayAdapter.add("Day")
        dayAdapter.addAll("PON", "UTO", "SRE", "ČET", "PET")
    }
}