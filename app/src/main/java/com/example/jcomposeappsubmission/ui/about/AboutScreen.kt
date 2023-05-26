package com.example.jcomposeappsubmission.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcomposeappsubmission.R
import com.example.jcomposeappsubmission.ui.theme.JComposeAppSubmissionTheme

@ExperimentalMaterialApi
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        HeaderAbout()
    }
}

@ExperimentalMaterialApi
@Composable
fun HeaderAbout(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(360.dp)
                .padding(16.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.my_picture),
            contentScale = ContentScale.FillWidth,
            contentDescription = "My Picture"
        )
        Spacer(modifier = modifier.height(8.dp))
        ProfileItem(title = "Name", field = "Azizah Fathimatuzzahro")
        ProfileItem(title = "Email", field = "afathimatuzzahro@gmail.com")
        ProfileItem(title = "Learning Path", field = "MD-05")
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun AboutScreenPreview() {
    JComposeAppSubmissionTheme() {
        HeaderAbout()
    }
}