package com.techtaskapp.presentation.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.techtaskapp.domain.model.CatItem
import com.techtaskapp.presentation.main.MainViewModel
import com.techtaskapp.utils.Result


@Composable
fun CatList(viewModel: MainViewModel) {
    val usersState by viewModel.cat.observeAsState()

    when (usersState) {
        is Result.Success -> {
            val cats = (usersState as Result.Success<List<CatItem>>).data
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(cats) { cat ->
                    CatCard(cat)
                }
            }
        }

        is Result.Loading -> {
            LoadingComposable()
        }

        is Result.Error -> {
            val errorMessage = (usersState as Result.Error).exception.message ?: "Unknown Error"
            ErrorComposable(errorMessage) {
                viewModel.getCats(20)
            }
        }

        else -> {

        }
    }


}

@Composable
fun CatCard(cat: CatItem) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        AsyncImage(
            model = cat.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )

        Text(text = cat.id)
    }
}

@Composable
fun LoadingComposable() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorComposable(errorMessage: String, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = errorMessage)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}