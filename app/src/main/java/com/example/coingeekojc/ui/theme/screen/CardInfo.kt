package com.example.coingeekojc.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.coingeekojc.R
import com.example.coingeekojc.ui.theme.POJO.CurrencyItem
import com.example.coingeekojc.utils.PercentageChangeText
import com.example.coingeekojc.utils.formatPrice

@Composable
fun CardInfo(
    currencyItem : CurrencyItem,
    currencySymbol: String
) {
    Card(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NetworkImage(
                url = currencyItem.image ?: (R.drawable.btc.toString()),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = currencyItem.name ?: stringResource(id = R.string.name_currency),
                    fontSize = 16.sp,
                )
                Text(
                    text = currencyItem.symbol?.uppercase()
                        ?: stringResource(R.string.abbreviation_currency).uppercase(),
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.padding(end = 16.dp, top = 8.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$currencySymbol ${formatPrice(currencyItem.currentPrice ?: 0.0)}",
                    fontSize = 16.sp
                )
                val change = currencyItem.priceChangePercentage24h ?: 0.0
                PercentageChangeText(change)
            }
        }
    }
}

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}