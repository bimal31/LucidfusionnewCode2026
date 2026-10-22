package com.org.lucidfusion.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import com.org.lucidfusion.databinding.FragmentTermsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsFragment : Fragment() {

    private var _binding: FragmentTermsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTermsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chkIAccept.visibility = View.GONE
        binding.llBottomView.visibility = View.GONE

        (activity as? MainActivity)?.setBackButtonVisibility(View.VISIBLE)
        val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
        if (isHindi) {
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_hindi_terms_condition))
        } else {
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_terms))
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.setShareButtonVisibility(View.INVISIBLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
