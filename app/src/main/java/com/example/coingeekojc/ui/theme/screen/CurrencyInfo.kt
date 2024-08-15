package com.example.coingeekojc.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coingeekojc.CurrencyInfoScreenViewModel
import com.example.coingeekojc.R
import com.example.coingeekojc.ui.theme.POJO.CurrencyCategoryItem
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyInfo(
    modifier: Modifier = Modifier,
    currencyCategoryItem: CurrencyCategoryItem,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp
            ) {
                TopAppBar(
                    title = { currencyCategoryItem.name },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate("ListCryptoScreen")
                        }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(28.dp)
                            )
                        }
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Box(
                modifier = Modifier
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                NetworkImage(
                    url = currencyCategoryItem.image.toString() ?: (R.drawable.btc.toString()),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.description_currency_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = currencyCategoryItem.description.toString() ?: "Error",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = currencyCategoryItem.categories.toString() ?: "Error",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.categoty_currency_description),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}