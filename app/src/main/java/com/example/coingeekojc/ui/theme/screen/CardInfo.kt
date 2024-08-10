package com.example.coingeekojc.ui.theme.screen

import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coingeekojc.R

@Preview (showSystemUi = true)
@Composable
fun CardInfo() {
    Card (
        modifier = Modifier.padding(top = 4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Image(
                painterResource(id = R.drawable.btc),
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
                    text = stringResource(R.string.name_currency),
                    fontSize = 16.sp
                )
                Text(
                    text = stringResource(R.string.abbreviation_currency),
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.padding(end = 16.dp, top = 8.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$ 28,560.40",
                    fontSize = 16.sp
                )
                Text(
                    text = "+ 4.05%",
                    fontSize = 12.sp
                )
            }
        }
    }
}