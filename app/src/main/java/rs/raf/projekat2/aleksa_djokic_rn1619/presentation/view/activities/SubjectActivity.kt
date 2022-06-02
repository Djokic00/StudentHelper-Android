package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
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
        studentViewModel.fetchAllSubjects()
        studentViewModel.getAllSubjects()
//        Timber.e("$setOfGroups")
    }

    private fun initObservers() {
        studentViewModel.subjectState.observe(this, Observer {
            renderState(it)
        })
    }

    private fun initListeners() {

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
                Timber.e("Success")
                adapter.submitList(state.subject)
                val subjects = state.subject
                val setOfGroups = mutableSetOf<String>()
                for (subject in subjects) {
                    val delimiter = "[,]{1}[\\s]?"
                    val array = Pattern.compile(delimiter).split(subject.grupe)
                    for (s in array) {
                        setOfGroups.add(s)
                    }
                }
                setSpinner(setOfGroups)
            }
            is SubjectState.Error -> {
                Timber.e("Error")
            }
            is SubjectState.DataFetched -> {
                Timber.e("Data fetched")
            }
            is SubjectState.Loading -> {
                Timber.e("Loading")
            }
        }
    }

    private fun setSpinner(setOfGroups : Set<String>) {
        val groupAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, ArrayList())
        groupAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.groupSpinner.adapter = groupAdapter
        groupAdapter.addAll(setOfGroups.toList().sorted())

        val dayAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, ArrayList())
        dayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.daySpinner.adapter = dayAdapter
        dayAdapter.addAll("PON", "UTO", "SRE", "CET", "PET")
    }
}