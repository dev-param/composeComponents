package com.example.composecomponents.wahguruUI.home


import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composecomponents.ktornetworking.models.ResFoodCat
import com.example.composecomponents.wahguru.ui.home.CatItemsList


@Composable
fun NavFoodCat(catList: MutableState<List<ResFoodCat>>) {
    val ActiveCat = rememberSaveable { mutableStateOf("burger") }


    if (catList.value.isEmpty()){
        Text(text = "loading")
    }else {
        Column (Modifier){


            LazyRow(Modifier.padding(vertical = 20.dp)) {
                items(catList.value) {
                    Spacer(modifier = Modifier.width(5.dp))
                    val animatedColor by animateColorAsState(
                    if (ActiveCat.value == it.name) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    label = "color"
                )

                    Button (
                        colors = ButtonDefaults.buttonColors().copy(containerColor = animatedColor),
                        onClick = {
                        ActiveCat.value = it.name
                    },

                    ) {
                        Text(text = it.name.capitalize())
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                }
            }

          CatItemsList(ActiveCat)
        }

    }

}
