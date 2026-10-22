package com.org.lucidfusion.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import com.org.lucidfusion.databinding.FragmentContactUsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentContactUs : Fragment() {

    private var _binding: FragmentContactUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setBackButtonVisibility(View.VISIBLE)

        val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
        if (isHindi) {
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_hindi_contact_us))
        } else {
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_contact))
        }

        binding.tvContactUsString.setLinkTextColor(
            ContextCompat.getColor(requireContext(), R.color.primary_color)
        )
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
