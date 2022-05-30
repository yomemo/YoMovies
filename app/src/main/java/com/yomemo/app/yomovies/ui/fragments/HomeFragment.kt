package com.yomemo.app.yomovies.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigator.Extras
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import coil.load
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.yomemo.app.yomovies.R
import com.yomemo.app.yomovies.databinding.FragmentHomeBinding
import com.yomemo.app.yomovies.ui.MainActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    // Main Activity
    private lateinit var mainActivity: MainActivity

    // Home Fragment Binding
    private lateinit var binding: FragmentHomeBinding

    // Movie Api
    private val service = ApiService.getTMDBApi()

    // Adapters
    private lateinit var adapterNowPlaying: NowPlayingAdapter
    private lateinit var adapterUpcoming: MoviesAdapter
    private lateinit var adapterPopular: MoviesAdapter
    private lateinit var adapterTopRated: MoviesAdapter

    // View Model
    private val model: MoviesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        val transition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, saved: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        mainActivity.setToolbar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Home", "onViewCreated")
        loadProfile()
        setupButtonClicks()
        setupRecyclerViews()
        loadData()
    }

//    private fun loadProfile() {
//        val user = FirebaseAuth.getInstance().currentUser
//        val stringName = "${user?.displayName} 👋"
//        binding.textName.text = stringName
//        binding.profileImage.load(user?.photoUrl)
//    }


    private fun setupButtonClicks() {
        binding.layoutProfile.setOnClickListener {
            findNavController().navigate(R.id.nav_profile, null, null, null)
        }
        binding.buttonExlore.setOnClickListener {
            findNavController().navigate(R.id.nav_explore, null, null, null)
        }
        binding.buttonMovieTheaters.setOnClickListener {
            findNavController().navigate(R.id.nav_movie_theaters, null, null, null)
        }
        binding.buttonMyTickets.setOnClickListener {
            findNavController().navigate(R.id.nav_my_tickets, null, null, null)
        }
        binding.buttonScan.setOnClickListener {
            findNavController().navigate(R.id.nav_scan, null, null, null)
        }
    }

    private fun setupRecyclerViews() {
        binding.rcNowPlaying.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rcUpcoming.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rcPopular.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rcTopRated.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        GravitySnapHelper(Gravity.START).attachToRecyclerView(binding.rcNowPlaying)
        GravitySnapHelper(Gravity.START).attachToRecyclerView(binding.rcUpcoming)
        GravitySnapHelper(Gravity.START).attachToRecyclerView(binding.rcPopular)
        GravitySnapHelper(Gravity.START).attachToRecyclerView(binding.rcTopRated)
        adapterNowPlaying = NowPlayingAdapter()
        adapterUpcoming = MoviesAdapter()
        adapterPopular = MoviesAdapter()
        adapterTopRated = MoviesAdapter()
        binding.rcNowPlaying.adapter = adapterNowPlaying
        binding.rcUpcoming.adapter = adapterUpcoming
        binding.rcPopular.adapter = adapterPopular
        binding.rcTopRated.adapter = adapterTopRated
        adapterNowPlaying.onMovieItemClick = { itemView, movie ->
            itemView.cardPoster.transitionName = "transition_home_poster"
            itemView.textTitle.transitionName = "transition_home_title"
            val extras = FragmentNavigatorExtras(
                itemView.cardPoster to "transition_details_poster",
                itemView.textTitle to "transition_details_title")
            toDetail(movie, extras)
        }
        adapterUpcoming.onMovieItemClick = { itemView, movie ->
            itemView.cardPoster.transitionName = "transition_home_poster"
            itemView.textTitle.transitionName = "transition_home_title"
            val extras = FragmentNavigatorExtras(
                itemView.cardPoster to "transition_details_poster",
                itemView.textTitle to "transition_details_title")
            toDetail(movie, extras)
        }
        adapterPopular.onMovieItemClick = { itemView, movie ->
            itemView.cardPoster.transitionName = "transition_home_poster"
            itemView.textTitle.transitionName = "transition_home_title"
            val extras = FragmentNavigatorExtras(
                itemView.cardPoster to "transition_details_poster",
                itemView.textTitle to "transition_details_title")
            toDetail(movie, extras)
        }
        adapterTopRated.onMovieItemClick = { itemView, movie ->
            itemView.cardPoster.transitionName = "transition_home_poster"
            itemView.textTitle.transitionName = "transition_home_title"
            val extras = FragmentNavigatorExtras(
                itemView.cardPoster to "transition_details_poster",
                itemView.textTitle to "transition_details_title")
            toDetail(movie, extras)
        }
    }

    private fun loadData() {
        service.getNowPlayingMovies().enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    movies?.let { adapterNowPlaying.addMovies(it) }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        service.getUpcomingMovies().enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    movies?.let { adapterUpcoming.addMovies(it) }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        service.getPopularMovies().enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    movies?.let { adapterPopular.addMovies(it) }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        service.getTopRatedMovies().enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    movies?.let { adapterTopRated.addMovies(it) }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun toDetail(movie: Movie, extras: Extras?) {
        model.select(movie)
        findNavController().navigate(R.id.nav_movie_details, null, null, extras)
    }
}


