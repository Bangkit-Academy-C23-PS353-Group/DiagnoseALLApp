package com.example.diagnosaallapps

import ArticleItem
import BottomBar
import ServiceItem
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diagnosaallapps.model.Article
import com.example.diagnosaallapps.model.Service
import com.example.diagnosaallapps.model.dummyArticles
import com.example.diagnosaallapps.model.dummyServices
import com.example.diagnosaallapps.ui.theme.DiagnosaALLappsTheme
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiagnosaALLappsTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
        Column(modifier = modifier) {
            Banner(userName = "Rizaldi P", date = "Selasa, 15 Juni 2023")
            SectionText(title = "Our Services")
            ServiceRow(listService = dummyServices)
            SectionText(title = "Health Article")
            ArticleColumn(listArticle = dummyArticles)
        }
}

@Composable
fun Banner(
    userName: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(103.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween) {
        Column(
        ) {
            Text(
                text = userName,
                fontSize = 20.sp,
                modifier = modifier.padding(start = 23.dp),
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = date,
                fontSize = 15.sp,
                modifier = modifier.padding(start = 23.dp),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }
        Image(
            painter = painterResource(R.drawable.userprofile),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .padding(end = 10.dp)
        )
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h5.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun ServiceRow(
    listService: List<Service>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listService, key = { it.textService }) { service ->
            ServiceItem(service = service)
        }
    }
}

@Composable
fun ArticleColumn(
    listArticle: List<Article>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listArticle, key = { it.textArticle }) { article ->
            ArticleItem(article = article)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiagnosaALLappsTheme {
        HomeScreen()
    }
}