package com.example.composecomponents.wahguru.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.composecomponents.ktornetworking.HomeRequests
import com.example.composecomponents.ktornetworking.models.ResFoodCat
import com.example.composecomponents.wahguruUI.home.NavFoodCat
import kotlinx.coroutines.launch


@Composable
fun RootBody(){
    val scope = rememberCoroutineScope()
    val foodCatData = rememberSaveable { mutableStateOf(emptyList<ResFoodCat>()) }
    LaunchedEffect(Unit) {
        scope.launch {
            foodCatData.value =  HomeRequests().getFoodList()
            Log.d("jj", foodCatData.toString())

        }
    }

    NavFoodCat(foodCatData)
}