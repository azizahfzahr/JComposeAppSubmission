package com.example.jcomposeappsubmission.ui.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcomposeappsubmission.ui.theme.JComposeAppSubmissionTheme

@ExperimentalMaterialApi
@Composable
fun ProfileItem(
    title: String,
    field: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier.padding(
            start = 16.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        )) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = modifier.height(8.dp))

            Text(
                modifier = modifier.padding(bottom = 2.dp),
                text = field,
                style = MaterialTheme.typography.subtitle2
            )
            Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun AboutItemReview() {
    JComposeAppSubmissionTheme() {
        ProfileItem(title = "Email", field = "afathimatuzzahro@gmail.com")
    }
}