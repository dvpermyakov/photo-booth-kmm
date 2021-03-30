package com.dvpermyakov.photo.booth.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.dvpermyakov.photo.booth.androidApp.imagePicker.ImagePickerFragment
import com.dvpermyakov.photo.booth.androidApp.main.MainFragment
import com.dvpermyakov.photo.booth.shared.interactors.PhotoInteractor
import com.dvpermyakov.photo.booth.shared.repository.FileRepository

class EntryPointActivity : AppCompatActivity(), InteractorProvider, Navigation {

    private val interactor = PhotoInteractor(
        fileRepository = FileRepository(this)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEntryPointBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.fragment_container_view)
            }
        }
    }

    override fun getPhotoInteractor(): PhotoInteractor = interactor

    override fun navigateToImagePicker() {
        supportFragmentManager.commit {
            replace<ImagePickerFragment>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack("name") // name can be null
        }
    }
}

interface Navigation {
    fun navigateToImagePicker()
}

interface InteractorProvider {
    fun getPhotoInteractor(): PhotoInteractor
}
