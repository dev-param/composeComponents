package com.example.composecomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.composecomponents.wahguru.ui.RootBody
import com.example.composecomponents.wahguruUI.home.NavFoodCat
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(darkModeState: MutableState<Boolean>){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState =drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "just text")
            }
        },

        ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopNav(darkModeState,drawerState, scope)
            }
        ) { innerPadding ->

            Column (modifier = Modifier.fillMaxSize().padding(innerPadding)){
                RootBody()

            }
        }
    }
}