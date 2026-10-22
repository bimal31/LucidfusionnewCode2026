package com.org.lucidfusion.ui.calculation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import com.org.lucidfusion.adapters.MachineModelsAdapter
import com.org.lucidfusion.adapters.PerssureRatingAdapter
import com.org.lucidfusion.adapters.PipeGradeAdapter
import com.org.lucidfusion.adapters.PipeSizeAdapter
import com.org.lucidfusion.databinding.LayoutCalculationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalculationFragment : Fragment() {

    private var _binding: LayoutCalculationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutCalculationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.apply {
            showHideTitleBar(View.VISIBLE)
            showHideBottomBar(View.VISIBLE)
            setBackButtonVisibility(View.VISIBLE)
        }

        setupLanguage()
        setupListeners()
        observeState()
        observeEffects()
    }

    private fun setupLanguage() {
        val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
        viewModel.initLanguage(isHindi)

        if (isHindi) {
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_hindi_calculate))
            setHindiLabels()
        } else {
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_calc))
            setEnglishLabels()
        }
    }

    private fun setHindiLabels() {
        binding.tvLblPipeStandard.setText(R.string.lbl_hindi_pipe_standard)
        binding.tvLblMachineModel.setText(R.string.lbl_hindi_machine)
        binding.tvLblPipeSize.setText(R.string.lbl_hindi_pipe_size)
        binding.tvLblPipeGrade.setText(R.string.lbl_hindi_pipe_grades)
        binding.tvLblPresureRating.setText(R.string.lbl_hindi_pressure_rating)
        binding.tvLblJointingPressure.setText(R.string.lbl_hindi_jointing_pressure)
        binding.tvLblDragPressure.setText(R.string.lbl_hindi_drag_pressure)
        binding.tvLblT2SoakingTime.setText(R.string.lbl_hindi_t2_soaking_time)
        binding.tvLblT2DragPressure.setText(R.string.lbl_hindi_drag_pressure_colon)
        binding.tvLblT2BeadTime.setText(R.string.lbl_hindi_t2_bead_up_time)
        binding.tvLblWeldingPressure.setText(R.string.lbl_hindi_welding_pressure)
        binding.tvLblCoolingTime.setText(R.string.lbl_hindi_cooling_time)
        binding.tvLblTemprature.setText(R.string.lbl_hindi_temprature)
        binding.btnCalculate.setText(R.string.lbl_hindi_calculate)
        val kgLabel = getString(R.string.lbl_hindi_kg_cm)
        binding.tvDragPressureKg.text = kgLabel
        binding.tvJointingPressureKg.text = kgLabel
    }

    private fun setEnglishLabels() {
        binding.tvLblPipeStandard.setText(R.string.lbl_pipe_standard)
        binding.tvLblMachineModel.setText(R.string.lbl_machine)
        binding.tvLblPipeSize.setText(R.string.lbl_pipe_size)
        binding.tvLblPipeGrade.setText(R.string.lbl_pipe_grades)
        binding.tvLblPresureRating.setText(R.string.lbl_pressure_rating)
        binding.tvLblJointingPressure.setText(R.string.lbl_jointing_pressure)
        binding.tvLblDragPressure.setText(R.string.lbl_drag_pressure)
        binding.tvLblT2SoakingTime.setText(R.string.lbl_t2_soaking_time)
        binding.tvLblT2DragPressure.setText(R.string.lbl_drag_pressure_colon)
        binding.tvLblT2BeadTime.setText(R.string.lbl_t1_bead_up_time)
        binding.tvLblWeldingPressure.setText(R.string.lbl_welding_pressure)
        binding.tvLblCoolingTime.setText(R.string.lbl_cooling_time)
        binding.tvLblTemprature.setText(R.string.lbl_temprature)
        binding.btnCalculate.setText(R.string.lbl_calc)
        val kgLabel = getString(R.string.lbl_kg_cm)
        binding.tvDragPressureKg.text = kgLabel
        binding.tvJointingPressureKg.text = kgLabel
    }

    private fun setupListeners() {
        binding.spnPipeStandard.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val standard = binding.spnPipeStandard.selectedItem as String
                viewModel.handleIntent(CalculationIntent.ChangeStandard(standard))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spnMachines.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.handleIntent(CalculationIntent.SelectMachine(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spnPipeSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.handleIntent(CalculationIntent.SelectPipeSize(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spnPipeGrades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.handleIntent(CalculationIntent.SelectPipeGrade(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spnPressureRating.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.handleIntent(CalculationIntent.SelectPressureRating(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.edDragPressure.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.handleIntent(CalculationIntent.DragPressureChanged(s?.toString() ?: ""))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnCalculate.setOnClickListener {
            viewModel.handleIntent(CalculationIntent.CalculateClicked)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderUiState(state)
                }
            }
        }
    }

    private fun renderUiState(state: CalculationUiState) {
        val currentMachineAdapter = binding.spnMachines.adapter as? MachineModelsAdapter
        if (state.machineModels.isNotEmpty() && (currentMachineAdapter == null || currentMachineAdapter.machineModels != state.machineModels)) {
            binding.spnMachines.adapter = MachineModelsAdapter(state.machineModels)
        }
        val machinePos = state.machineModels.indexOfFirst { it.machine_id == state.selectedMachineId }
        if (machinePos >= 0 && binding.spnMachines.selectedItemPosition != machinePos) {
            binding.spnMachines.setSelection(machinePos)
        }

        val currentPipeSizeAdapter = binding.spnPipeSize.adapter as? PipeSizeAdapter
        if (state.pipeSizes.isNotEmpty() && (currentPipeSizeAdapter == null || currentPipeSizeAdapter.pipeSizes != state.pipeSizes)) {
            binding.spnPipeSize.adapter = PipeSizeAdapter(state.pipeSizes)
        }
        val pipeSizePos = state.pipeSizes.indexOfFirst { it.pipe_size_id == state.selectedPipeSizeId }
        if (pipeSizePos >= 0 && binding.spnPipeSize.selectedItemPosition != pipeSizePos) {
            binding.spnPipeSize.setSelection(pipeSizePos)
        }

        val currentGradeAdapter = binding.spnPipeGrades.adapter as? PipeGradeAdapter
        if (state.pipeGrades.isNotEmpty() && (currentGradeAdapter == null || currentGradeAdapter.pipeGrades != state.pipeGrades)) {
            binding.spnPipeGrades.adapter = PipeGradeAdapter(state.pipeGrades)
        }
        val gradePos = state.pipeGrades.indexOfFirst { it.p_grade_id == state.selectedPipeGradeId }
        if (gradePos >= 0 && binding.spnPipeGrades.selectedItemPosition != gradePos) {
            binding.spnPipeGrades.setSelection(gradePos)
        }

        val currentPressureAdapter = binding.spnPressureRating.adapter as? PerssureRatingAdapter
        if (state.pressureRatings.isNotEmpty() && (currentPressureAdapter == null || currentPressureAdapter.pressureSizes != state.pressureRatings)) {
            binding.spnPressureRating.adapter = PerssureRatingAdapter(state.pressureRatings)
        }
        val pressurePos = state.pressureRatings.indexOfFirst { it.pressure_id == state.selectedPressureId }
        if (pressurePos >= 0 && binding.spnPressureRating.selectedItemPosition != pressurePos) {
            binding.spnPressureRating.setSelection(pressurePos)
        }

        binding.edJointingPressure.setText(state.jointingPressure)

        val result = state.calculationResult
        if (result != null) {
            binding.llResult.visibility = View.VISIBLE
            val lblMin = if (state.isHindi) getString(R.string.lbl_hindi_min) else " Min. "
            val lblSec = if (state.isHindi) " से." else " sec."
            val kgLabel = if (state.isHindi) getString(R.string.lbl_hindi_kg_cm) else getString(R.string.lbl_kg_cm)

            binding.tvT2BeadTimeValue.text = "${result.beadUpMin} $lblMin : ${result.beadUpSec} $lblSec"
            binding.tvSoakingTimeValue.text = "${result.soakingMin} $lblMin : ${result.soakingSec} $lblSec"
            binding.tvDragPressureValue.text = "${result.dragPressure} $kgLabel"
            binding.tvWeldingPressureValue.text = "${result.weldingPressure} $kgLabel"
            binding.tvCoolingTimeValue.text = "${result.coolingTimeMin} $lblMin"
            binding.tvTempratureValue.text = "${result.temperature} ℃"
        } else {
            binding.llResult.visibility = View.GONE
        }
    }

    private fun observeEffects() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEffect.collect { effect ->
                    when (effect) {
                        is CalculationEffect.ShowToast -> Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                        is CalculationEffect.ScrollToBottom -> {
                            binding.svParent.post { binding.svParent.fullScroll(View.FOCUS_DOWN) }
                        }
                    }
                }
            }
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
