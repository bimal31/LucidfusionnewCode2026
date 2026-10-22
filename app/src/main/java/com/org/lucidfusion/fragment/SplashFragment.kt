package com.org.lucidfusion.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.Utils
import com.org.lucidfusion.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.apply {
            setBackButtonVisibility(View.GONE)
            showHideTitleBar(View.GONE)
            showHideBottomBar(View.GONE)
            setTitle("")
        }

        try {
            val tf = Typeface.createFromAsset(requireContext().assets, "BAUHS93.TTF")
            binding.shimmerTv.setTypeface(tf, Typeface.ITALIC)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded) {
                val isAccepted = Utils.getStringSetting(requireContext(), Utils.PREF_ACCEPT_TERMS) == "true"
                val nextFragment: Fragment = if (isAccepted) {
                    HindiEnglishSelectionFragment()
                } else {
                    TermsAndConditionFragment()
                }
                (activity as? MainActivity)?.replaceFragment(nextFragment, true, null, false)
            }
        }, 6000)
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
