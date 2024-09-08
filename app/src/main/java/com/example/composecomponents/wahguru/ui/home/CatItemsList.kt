package com.example.composecomponents.wahguru.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.composecomponents.ktornetworking.HomeRequests
import com.example.composecomponents.ktornetworking.models.ResCatItems
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.absoluteValue

@Composable
fun CatItemsList(activeCat: MutableState<String>){

    val catItemScope = rememberCoroutineScope()
    val catItemData = rememberSaveable { mutableStateOf(emptyList<ResCatItems>()) }
    LaunchedEffect(activeCat.value) {

                catItemScope.launch {
                    catItemData.value = emptyList<ResCatItems>()
                    catItemData.value = HomeRequests().getFoodSubList(activeCat.value)
                }
            }
       if (catItemData.value.isEmpty()){
               Text(text = "loading...")
       }else{
               CatItemsView(catItemData)
       }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatItemsView(catItemData: MutableState<List<ResCatItems>>){
        val pagerState = rememberPagerState(pageCount = {
            catItemData.value.size
        })
        val fling = PagerDefaults.flingBehavior(
            state = pagerState,
            pagerSnapDistance = PagerSnapDistance.atMost(5)
        )
        HorizontalPager(
            pagerState,
            beyondBoundsPageCount = 5,
            flingBehavior =fling,
//            pageSize = PageSize.Fixed(500.dp)


        ) {
            val itemData = catItemData.value[it]

            Box{


                Box {
                    Card(
                        modifier = Modifier.fillMaxSize().padding(20.dp).graphicsLayer {
                            val pageOffset = (
                                    (pagerState.currentPage - it) + pagerState
                                        .currentPageOffsetFraction
                                    ).absoluteValue
                            alpha = lerp(
                                start = 0.6f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                            this.rotationY = lerp(
                                start = 10f,
                                stop = 0f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )


                        }

                    ) {

                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box {
                                CatItemsImageView(itemData, pagerState, it)
                                CatItemsViewFooter()

                            }

                        }


                    }
                }
                CatItemsViewText(itemData, pagerState, it)
//

            }
        }
}

@Composable
fun CatItemsViewFooter(){
    Box{



        Column(
            modifier = Modifier.fillMaxSize().padding(25.dp),
            verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
            Row {



                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
                }
                Button(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = null
                    )
                    Text(text = "Add Cart")

                }
            }


        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatItemsImageView(itemData: ResCatItems, pagerState: PagerState, i: Int) {
    SubcomposeAsyncImage(
        model = "https://api.waheguruhub.com${itemData.images[0]}",
        contentDescription = itemData.name,
        modifier = Modifier.fillMaxSize(.97f).graphicsLayer {
            val pageOffset = (
                    (pagerState.currentPage - i) + pagerState
                        .currentPageOffsetFraction
                    ).absoluteValue

            this.scaleX =lerp(
                start = 1.1f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )

        }
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Loading Image...")
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

            }
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatItemsViewText(itemData: ResCatItems, pagerState: PagerState, activePage: Int){
    Box {
        Column {


            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large,
                shadowElevation = 2.dp,
                tonalElevation = 2.dp,
                modifier = Modifier.padding(start = 6.dp)


            ) {


                Text(
                    text = itemData.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)

                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.extraLarge,
                shadowElevation = 2.dp,
                tonalElevation = 2.dp,
                modifier = Modifier.padding(start = 6.dp)


            ) {


                Text(
                    text = "Price: 50",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)

                )
            }
        }
    }

}