package com.cwtstudio.nasapaints.view

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cwtstudio.nasapaints.R
import com.cwtstudio.nasapaints.databinding.ActivityMainBinding
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var currentColor: Int = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signatureView.penColor = currentColor

        binding.penSize.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.txtPenSize.text = "$progress dp"
                binding.signatureView.penSize = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        binding.btnDelete.setOnClickListener {
            binding.signatureView.clearCanvas()
        }

        binding.btnErase.setOnClickListener {
            binding.signatureView.penColor = binding.signatureView.backgroundColor
        }

        binding.btnPicker.setOnClickListener {
            pickColor()
        }
        binding.btnDraw.setOnClickListener {
            binding.signatureView.penColor = currentColor
        }


    }

    private fun pickColor() {
        AmbilWarnaDialog(
            this,
            currentColor,
            object : OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {

                }

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    currentColor = color
                    binding.signatureView.penColor = currentColor
                }

            }
        ).show()
    }
}