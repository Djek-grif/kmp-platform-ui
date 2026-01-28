package com.djekgrif.nativeuisimple.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.WelcomeComponent
import com.djekgrif.nativeuisimple.presentation.base.ui.welcome.WelcomeContract
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinproject.composeapp.generated.resources.sign_in
import kotlinproject.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeScreen(component: WelcomeComponent) {

    val state = component.welcomeViewModel.viewState.collectAsState().value
    val onUIAction = component.welcomeViewModel::onUIAction

    Scaffold(content = { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(state.title)
            Spacer(modifier = Modifier.height(5.dp))
            Image(painterResource(Res.drawable.compose_multiplatform), null)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { onUIAction(WelcomeContract.Action.OnSignUpClick) }) {
                Text(stringResource(Res.string.sign_up))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { onUIAction(WelcomeContract.Action.OnSignInClick) }) {
                Text(stringResource(Res.string.sign_in))
            }

        }
    })
}