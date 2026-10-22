package com.org.lucidfusion.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import com.org.lucidfusion.databinding.FragmentHindiEnglishSelectionBinding
import com.org.lucidfusion.ui.calculation.CalculationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HindiEnglishSelectionFragment : Fragment() {

    private var _binding: FragmentHindiEnglishSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHindiEnglishSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.apply {
            showHideTitleBar(View.VISIBLE)
            showHideBottomBar(View.VISIBLE)
            setBackButtonVisibility(View.GONE)
        }

        val currentLanguage = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE)
        if (currentLanguage == "hindi") {
            setHindiMode()
        } else {
            setEnglishMode()
        }

        binding.chkEnglish.setOnClickListener {
            Utils.setStringSetting(requireContext(), Utils.PREF_IS_LANGAUGE_SELECTED, "1")
            Utils.setStringSetting(requireContext(), Utils.PREF_LANGAUGE, "english")
            setEnglishMode()
        }

        binding.chkHindi.setOnClickListener {
            Utils.setStringSetting(requireContext(), Utils.PREF_IS_LANGAUGE_SELECTED, "1")
            Utils.setStringSetting(requireContext(), Utils.PREF_LANGAUGE, "hindi")
            setHindiMode()
        }

        binding.btnCalculate.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(CalculationFragment(), true, null, true)
        }

        binding.btnAbout.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(TermsFragment(), true, null, true)
        }

        binding.btnContact.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(FragmentContactUs(), true, null, true)
        }

        binding.btnReport.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(FragmentReport(), true, null, true)
        }

        if (activity is MainActivity && !(activity as MainActivity).isHindiSupported) {
            binding.chkHindi.visibility = View.GONE
        }
    }

    private fun setEnglishMode() {
        (activity as? MainActivity)?.setTitle("Home")
        binding.chkHindi.isChecked = false
        binding.chkEnglish.isChecked = true
        binding.btnCalculate.text = getString(R.string.lbl_calc)
        binding.btnAbout.text = getString(R.string.lbl_terms)
        binding.btnReport.text = getString(R.string.lbl_report)
        binding.btnContact.text = getString(R.string.lbl_contact)
        binding.llBottom.visibility = View.VISIBLE
    }

    private fun setHindiMode() {
        (activity as? MainActivity)?.setTitle("Home")
        binding.chkHindi.isChecked = true
        binding.chkEnglish.isChecked = false
        binding.btnCalculate.text = getString(R.string.lbl_hindi_calculate)
        binding.btnReport.text = getString(R.string.hindi_lbl_report)
        binding.btnAbout.text = getString(R.string.lbl_hindi_terms)
        binding.btnContact.text = getString(R.string.lbl_hindi_contact_us)
        binding.llBottom.visibility = View.VISIBLE
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
