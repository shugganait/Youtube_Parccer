package lib.kg.youtubeparccer.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        checkInternet()
        setUI()
        setupLiveData()
        initClickListener()
    }

    open fun setupLiveData() {
        // Инициализация LiveData и наблюдателей
    }

    open fun setUI() {
        // Инициализация UI компонентов
    }

    open fun initClickListener() {
        // Обработка кликов
    }

    open fun checkInternet() {
        // Проверка наличия интернета
    }
}

