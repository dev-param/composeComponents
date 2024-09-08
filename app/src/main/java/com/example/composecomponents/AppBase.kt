package com.example.composecomponents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.composecomponents.wahguruUI.home.NavFoodCat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNav(darkModeState: MutableState<Boolean>, sideDrawerState:DrawerState, sideDrawerScope:CoroutineScope){
    TopAppBar(

        title = {
                val img = painterResource(R.drawable.transparet_hub_logo)
//            Text(text = stringResource(R.string.api_for), color = MaterialTheme.colorScheme.background, style = MaterialTheme.typography.headlineSmall)
            Image(painter = img, contentDescription = null, modifier = Modifier.size(80.dp).offset(x = -30.dp))

        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),

        navigationIcon = {
            IconButton(onClick = {
                sideDrawerScope.launch {
                    if (sideDrawerState.isOpen) sideDrawerState.close() else sideDrawerState.open()

                }

            }) {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null, tint =  MaterialTheme.colorScheme.background)

            }
        },
        actions = {
            IconButton(onClick = {
                darkModeState.value = !darkModeState.value
            }) {
                Icon(imageVector = Icons.Outlined.Create, contentDescription = null, tint =  MaterialTheme.colorScheme.background)

            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null, tint =  MaterialTheme.colorScheme.background)

            }

        },




    )

}



