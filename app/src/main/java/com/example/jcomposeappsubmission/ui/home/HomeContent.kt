package com.example.jcomposeappsubmission.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jcomposeappsubmission.data.di.Injection
import com.example.jcomposeappsubmission.data.model.Kuliner
import com.example.jcomposeappsubmission.data.repository.ViewModelFactory
import com.example.jcomposeappsubmission.ui.common.UiState
import com.example.jcomposeappsubmission.R

@Composable
fun HomeContent (
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllKuliner()
                }
                is UiState.Success -> {
                    ContentList(
                        modifier = modifier,
                        kuliner = uiState.data,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is  UiState.Error -> {}
            }
        }
    }
}

@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    data: Kuliner,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(
            modifier = modifier
                .height(200.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = data.img!!),
                    contentDescription = "Kuliner Image",
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black,
                            ),
                            startY = 400f
                        )
                    )
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = data.name.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    ),
                )
            }
        }
    }
}

@Composable
fun ContentList(
    kuliner: List<Kuliner>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn{
        items(kuliner) { item ->
            ContentCard(
                data = item,
                modifier = modifier.clickable {
                    navigateToDetail(item.id.toString())
                }
            )
        }
    }
}

@Composable
fun MyTopBarApp(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.kuliner_name_data))
        },
        actions = {
            IconButton(
                onClick = {
                    onMenuClick()
                },
            ) {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "about_page")
            }
        }
    )
}