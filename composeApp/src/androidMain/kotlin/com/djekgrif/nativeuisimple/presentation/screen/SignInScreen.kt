package com.djekgrif.nativeuisimple.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.SignInComponent
import com.djekgrif.nativeuisimple.presentation.base.ui.signin.SignInContract
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.back
import kotlinproject.composeapp.generated.resources.by_continuing_agree_terms
import kotlinproject.composeapp.generated.resources.check_connection
import kotlinproject.composeapp.generated.resources.enter_login_password
import kotlinproject.composeapp.generated.resources.error
import kotlinproject.composeapp.generated.resources.invalid_credentials
import kotlinproject.composeapp.generated.resources.login
import kotlinproject.composeapp.generated.resources.network_error
import kotlinproject.composeapp.generated.resources.ok
import kotlinproject.composeapp.generated.resources.password
import kotlinproject.composeapp.generated.resources.sign_in
import kotlinproject.composeapp.generated.resources.sign_in_to_continue
import kotlinproject.composeapp.generated.resources.validation_error
import kotlinproject.composeapp.generated.resources.welcome_back
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(component: SignInComponent) {
    val state = component.signInViewModel.viewState.collectAsState().value
    val onUIAction = component.signInViewModel::onUIAction
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.sign_in)) },
                navigationIcon = {
                    IconButton(onClick = { onUIAction(SignInContract.Action.OnBackClick) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(Res.string.back))
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background)
                    .safeContentPadding()
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(Res.string.welcome_back),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(Res.string.sign_in_to_continue),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = state.login,
                        onValueChange = { onUIAction.invoke(SignInContract.Action.OnLoginChanged(it)) },
                        label = { Text(stringResource(Res.string.login)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { onUIAction.invoke(SignInContract.Action.OnPasswordChanged(it)) },
                        label = { Text(stringResource(Res.string.password)) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            focusManager.clearFocus(force = true)
                            onUIAction(SignInContract.Action.OnContinueClick)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(Res.string.sign_in_to_continue))
                    }
                }

                Text(
                    text = stringResource(Res.string.by_continuing_agree_terms),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }
    )

    if (state.showInvalidValidationDialog) {
        AlertDialog(
            onDismissRequest = { onUIAction(SignInContract.Action.OnInvalidValidationDialogOk) },
            title = { Text(stringResource(Res.string.validation_error)) },
            text = { Text(stringResource(Res.string.enter_login_password)) },
            confirmButton = {
                TextButton(onClick = { onUIAction(SignInContract.Action.OnInvalidValidationDialogOk) }) {
                    Text(stringResource(Res.string.ok))
                }
            }
        )
    }

    if (state.showInvalidCredentialsDialog) {
        AlertDialog(
            onDismissRequest = { onUIAction(SignInContract.Action.OnInvalidCredentialsDialogOk) },
            title = { Text(stringResource(Res.string.error)) },
            text = { Text(stringResource(Res.string.invalid_credentials)) },
            confirmButton = {
                TextButton(onClick = { onUIAction(SignInContract.Action.OnInvalidCredentialsDialogOk) }) {
                    Text(stringResource(Res.string.ok))
                }
            }
        )
    }

    if (state.showNetworkErrorDialog) {
        AlertDialog(
            onDismissRequest = { onUIAction(SignInContract.Action.OnNetworkErrorDialogOk) },
            title = { Text(stringResource(Res.string.network_error)) },
            text = { Text(stringResource(Res.string.check_connection)) },
            confirmButton = {
                TextButton(onClick = { onUIAction(SignInContract.Action.OnNetworkErrorDialogOk) }) {
                    Text(stringResource(Res.string.ok))
                }
            }
        )
    }
}
