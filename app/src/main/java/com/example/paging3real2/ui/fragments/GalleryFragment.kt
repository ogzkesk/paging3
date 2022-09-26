package com.example.paging3real2.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.paging3real2.R
import com.example.paging3real2.adapters.UnsplashPhotoAdapter
import com.example.paging3real2.databinding.FragmentGalleryBinding
import com.example.paging3real2.model.UnsplashPhoto
import com.example.paging3real2.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(),UnsplashPhotoAdapter.OnItemClickListener {

    private var _binding : FragmentGalleryBinding? = null
    private val binding get()= _binding!!
    private val viewModel by viewModels<GalleryViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        // set Adapter
        val adapter = UnsplashPhotoAdapter(this)
        binding.apply {
            rvList.setHasFixedSize(true)
            // + olarak loadStateAdapter'i de bağlıyoruz ( footer için ) . Lambda is retry button'un onClick'i
            rvList.adapter = adapter
            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.photos.observe(viewLifecycleOwner){ pagingData ->
            //submitData() from pagingDataAdapter  <-
            adapter.submitData(viewLifecycleOwner.lifecycle,pagingData)

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.search_menu,menu)

        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    // Bir şey arattığında ilk satıra dönsün ->
                    binding.rvList.scrollToPosition(0)
                    // Aranan veriyi viewmodel'a gönderir
                    viewModel.searchPhotos(query)
                    // aramadan sonra temizle ?? idk
                    searchView.clearFocus()

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // todo nothing
                return true
            }
        })

        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            val imm : InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(v.findFocus(),0)
        }
    }

    override fun onItemClick(photo: UnsplashPhoto) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(photo)
        Navigation.findNavController(requireView()).navigate(action)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}