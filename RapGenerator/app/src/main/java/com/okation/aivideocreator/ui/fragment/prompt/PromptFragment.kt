    package com.okation.aivideocreator.ui.fragment.prompt

    import PromptTitleAdapter
    import TitleClickedListener
    import android.os.Bundle
    import android.util.Log
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.viewModels
    import androidx.navigation.Navigation
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.StaggeredGridLayoutManager
    import com.okation.aivideocreator.R
    import com.okation.aivideocreator.databinding.FragmentPromptBinding
    import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint
    class PromptFragment : Fragment() {
        private lateinit var binding: FragmentPromptBinding
        private lateinit var viewModel: PromptViewModel
        private lateinit var titleAdapter: PromptTitleAdapter
        private lateinit var contentsAdapter: PromptContentsAdapter

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentPromptBinding.inflate(inflater, container, false)
            binding.imageViewBack.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_promptFragment_to_homePageFragment)
            }
            titleAdapter = PromptTitleAdapter(emptyList(), object : TitleClickedListener {
                override fun onTitleClicked(titleId: Int?) {
                    titleId?.let { selectedTitleId ->
                        viewModel.showContents(selectedTitleId)
                    }
                }
            })
            contentsAdapter = PromptContentsAdapter(emptyList(), object : ContentsClickedListener{
                override fun onContentsClicked(contentText: String?) {
                    contentText?.let {selectedContents->
                        binding.textViewContent.setText(selectedContents)
                        if(!(binding.textViewContent.text.isNullOrEmpty())){
                            binding.buttonContinuePrompt.setBackgroundResource(R.drawable.onboarding_button)
                            binding.buttonContinuePrompt.setOnClickListener {
                                val action = PromptFragmentDirections.actionPromptFragmentToGeneratingLyricsFragment(selectedContents)
                                Navigation.findNavController(it).navigate(action)
                            }
                        }
                    }
                }
            })
            binding.rvTitle.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvTitle.adapter = titleAdapter

            binding.rvLyrics.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.rvLyrics.adapter = contentsAdapter

            viewModel.titleList.observe(viewLifecycleOwner) { titles ->
                titleAdapter.updateTitleList(titles)
            }

            viewModel.contentsList.observe(viewLifecycleOwner) { contents ->
                contentsAdapter.updateContentsList(contents)
            }
            return binding.root
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val tempViewModel: PromptViewModel by viewModels()
            viewModel = tempViewModel
            tempViewModel.context = requireContext()
        }
        override fun onResume() {
            super.onResume()
            viewModel.showTitle()
        }
    }