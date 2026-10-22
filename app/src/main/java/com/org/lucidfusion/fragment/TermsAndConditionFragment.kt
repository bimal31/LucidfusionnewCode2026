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
class TermsAndConditionFragment : Fragment() {

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

        (activity as? MainActivity)?.apply {
            setTitle(getString(R.string.lbl_disclaimer))
            showHideTitleBar(View.VISIBLE)
            showHideBottomBar(View.GONE)
            setBackButtonVisibility(View.GONE)
        }

        binding.chkIAccept.setOnCheckedChangeListener { _, isChecked ->
            binding.btnDone.isEnabled = isChecked
        }

        binding.btnDone.setOnClickListener {
            Utils.setStringSetting(requireContext(), Utils.PREF_ACCEPT_TERMS, "true")
            (activity as? MainActivity)?.replaceFragment(HindiEnglishSelectionFragment(), false, null, false)
        }

        binding.btnCancel.setOnClickListener {
            activity?.finish()
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
