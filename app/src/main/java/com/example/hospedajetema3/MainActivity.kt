package com.example.hospedajetema3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.hospedajetema3.controler.Controller
import com.example.hospedajetema3.ui.theme.HospedajeTema3Theme
import com.example.hospedajetema3.databinding.MainBinding

class MainActivity : ComponentActivity() {
    lateinit var controller: Controller
    lateinit var binding : MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init() {
        initRecyclerView()
        controller = Controller(this)
        controller.setAdapter()
    }

    private fun initRecyclerView() {
        var myReciclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        myReciclerView.layoutManager = LinearLayoutManager(this)
    }
}
