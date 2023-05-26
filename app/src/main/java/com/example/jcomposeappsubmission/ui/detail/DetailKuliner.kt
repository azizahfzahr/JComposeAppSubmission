package com.example.jcomposeappsubmission.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jcomposeappsubmission.R
import com.example.jcomposeappsubmission.data.di.Injection
import com.example.jcomposeappsubmission.data.model.Kuliner
import com.example.jcomposeappsubmission.data.repository.ViewModelFactory
import com.example.jcomposeappsubmission.ui.common.UiState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jcomposeappsubmission.data.model.FakeDataKuliner
import com.example.jcomposeappsubmission.ui.theme.JComposeAppSubmissionTheme

@Composable
fun DetailKuliner(
    idDetail: String,
    navigateBack: () -> Unit,
    viewModel: DetailKulinerViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getIdKuliner(idDetail)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    kulinerData = data,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun Header(
    data: Kuliner,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = data.img!!),
            contentDescription = stringResource(id = R.string.image_detail),
            modifier = modifier
                .fillMaxWidth()
                .height(500.dp),
            contentScale = ContentScale.Fit,
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 400f,
                    )
                )
        )
        Box(
            modifier = modifier
                .statusBarsPadding()
                .fillMaxWidth()
        ) {
            TopButton(
                imageVector = Icons.Default.ArrowBackIos,
                modifier = modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                clickListener = onBackClick
            )
        }
    }
}

@Composable
fun DetailContent(
    kulinerData: Kuliner,
    onBackClick: () -> Unit
) {
    LazyColumn {
        item {
            Header(data = kulinerData, onBackClick = onBackClick)
            Detail(kulinerData)
            KulinerDataContent(kulinerData)
        }
    }
}

@Composable
fun Detail(
    data: Kuliner,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.LocationOn, contentDescription = "",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(12.dp)
                    .align(CenterVertically),
                tint = Color(0xFFFF0000)
            )

            Text(
                text = "Kota Majalengka",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = data.name!!,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.2).sp
        )

        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun KulinerDataContent(kuliner: Kuliner) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Description".uppercase(),
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.75.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = kuliner.desc!!,
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp
        )
    }
}

@Preview
@Composable
fun KulinerContentPrev() {
    JComposeAppSubmissionTheme {
        KulinerDataContent(kuliner = FakeDataKuliner.listKuliner[0])
    }
}

@Composable
fun KulinerDataItem(imageVector: ImageVector, title: String, subtitle: String) {
    Row {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp)
                .padding(8.dp),
            imageVector = imageVector, contentDescription = ""
        )
        Column {
            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun TopButton(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
    Button(
        onClick = { clickListener() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xDDF6F9FF),
            contentColor = Color(0xFF000000),
        ),
        modifier = modifier.size(48.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    JComposeAppSubmissionTheme {
        DetailContent(
            kulinerData = FakeDataKuliner.listKuliner[0],
            onBackClick = {}
        )
    }
}