package com.stopstudiovm.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.stopstudiovm.cryptoapp.presentation.coin_detail.CoinDetailViewModel


@Composable
fun CoinDetailScreen(
    // We don't need navController in here because we are not going to navigate anywhere and to go back we will only use back button
    viewModel: CoinDetailViewModel = hiltViewModel() // This will give to us viewModel Instance
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){
        state.coin?.let { // We need to be sure that coin is not null
            coin ->
            LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)){
                // Lets build single item
                item{
                    Row (
                        modifier = Modifier.fillMaxWidth(), // So we will take max width but height only how much we required
                        horizontalArrangement = Arrangement.SpaceBetween
                            ){
                        Text(text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f) // So it will not overlap our active text
                        )
                        Text(
                            text = if(coin.isActive) "active" else "inactive",
                            color = if(coin.isActive) Color.Green else Color.Red,
                            fontStyle = Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Library use
                    FlowRow(
                        mainAxisSpacing = 8.dp, // x - axes
                        crossAxisSpacing = 8.dp, // y - axes
                        modifier = Modifier.fillMaxWidth()
                    ) { // Is row which will wrap it's content on the next line if the elements exceed the bounds
                        coin.tags.forEach {
                            tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Team members",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                // Lazy column items dynamically
                items(coin.team){
                    teamMember ->
                    TeamListItem(teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Divider()
                }
            }
        }

        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center)

            )
        }
        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center) // Align inside center of the box
            )
        }
    }
}