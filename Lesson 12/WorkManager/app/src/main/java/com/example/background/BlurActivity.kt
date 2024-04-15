package com.example.background

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import com.example.background.databinding.ActivityBlurBinding

class BlurActivity : AppCompatActivity() {

    // ViewModel para el procesamiento de imágenes
    private val viewModel: BlurViewModel by viewModels { BlurViewModelFactory(application) }
    private lateinit var binding: ActivityBlurBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el botón para iniciar el procesamiento de imágenes
        binding.goButton.setOnClickListener { viewModel.applyBlur(blurLevel) }

        // Configurar el botón para ver el archivo de imagen resultante
        binding.seeFileButton.setOnClickListener {
            viewModel.outputUri?.let { currentUri ->
                val actionView = Intent(Intent.ACTION_VIEW, currentUri)
                actionView.resolveActivity(packageManager)?.run {
                    startActivity(actionView)
                }
            }
        }

        // Configurar el botón para cancelar el trabajo
        binding.cancelButton.setOnClickListener { viewModel.cancelWork() }
        // Observador para el estado del trabajo
        viewModel.outputWorkInfos.observe(this, workInfosObserver())
    }

    // Observador para el estado del trabajo
    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            // Verificar si hay información de trabajo
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

           // Obtener la información del primer trabajo
            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                // Procesar la URI del archivo de salida
                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                // Mostrar el botón "Ver Archivo" si hay una URI de salida
                if (!outputImageUri.isNullOrEmpty()) {
                    viewModel.setOutputUri(outputImageUri)
                    binding.seeFileButton.visibility = View.VISIBLE
                }
            } else {
                showWorkInProgress()
            }
        }
    }

    /**
     * Mostrar las vistas correspondientes cuando el trabajo está en progreso
     */
    private fun showWorkInProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            cancelButton.visibility = View.VISIBLE
            goButton.visibility = View.GONE
            seeFileButton.visibility = View.GONE
        }
    }

    /**
     * Mostrar las vistas correspondientes cuando el trabajo ha finalizado
     */
    private fun showWorkFinished() {
        with(binding) {
            progressBar.visibility = View.GONE
            cancelButton.visibility = View.GONE
            goButton.visibility = View.VISIBLE
        }
    }

    // Obtener el nivel de desenfoque seleccionado por el usuario
    private val blurLevel: Int
        get() =
            when (binding.radioBlurGroup.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 2
                R.id.radio_blur_lv_3 -> 3
                else -> 1
            }
}
