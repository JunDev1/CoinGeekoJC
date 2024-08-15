package com.example.coingeekojc.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.example.coingeekojc.R
import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyInfo(
    currencyCategoryItem: CurrencyCategoryItem,
) {
    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp
            ) {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = { Text(text = currencyCategoryItem.name.toString()) },
                )

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Box(
                modifier = Modifier
                    .size(100.dp),
                contentAlignment = Alignment.Center
            )
            {
                NetworkImage(
                    url = currencyCategoryItem.image?.large ?: (R.drawable.btc.toString()),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.description_currency_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            val htmlText = currencyCategoryItem.description?.en.toString()
            val paragraphs = htmlText.split("\r\n\r\n")
            paragraphs.forEach { paragraph ->
                val spanned = HtmlCompat.fromHtml(paragraph, HtmlCompat.FROM_HTML_MODE_COMPACT)
                Text(
                    text = spanned.toString(),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 8.dp, end = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.category_currency_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            val categoriesString = currencyCategoryItem.categories
                ?.filterNotNull() // Remove any null values from the list
                ?.joinToString(separator = ", ") // Join elements with a comma and space
                .orEmpty()
            Text(
                text = categoriesString,
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}