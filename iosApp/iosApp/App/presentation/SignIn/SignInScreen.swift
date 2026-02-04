//
// Created by Djek Grif on 28/1/26.
//

import Foundation
import SwiftUI
import Shared

struct SignInScreen: View {

    let signInComponent: SignInComponent
    let onUIAction: (SignInContract.Action) -> Void
    @StateValue private var state: SignInContract.State

    init(signInComponent: SignInComponent) {
        self.signInComponent = signInComponent
        self.onUIAction = signInComponent.signInViewModel.onUIAction
        _state = StateValue(signInComponent.signInViewModel.viewStateValue)
    }

    var body: some View {
        NavigationView {
            VStack(spacing: 0) {
                VStack(spacing: 16) {
                    Spacer()

                    Text("Welcome Back")
                        .font(.largeTitle)
                        .fontWeight(.bold)

                    Text("Sign in to continue")
                        .font(.body)
                        .foregroundColor(.gray)

                    Spacer().frame(height: 32)

                    TextField("Login", text: Binding(
                        get: { state.login },
                        set: { onUIAction(SignInContract.ActionOnLoginChanged(value: $0)) }
                    ))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .autocapitalization(.none)
                    .keyboardType(.emailAddress)
                    
                    SecureField("Password", text: Binding(
                        get: { state.password },
                        set: { onUIAction(SignInContract.ActionOnPasswordChanged(value: $0)) }
                    ))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    
                    Spacer().frame(height: 24)

                    Button(action: {
                        hideKeyboard()
                        onUIAction(SignInContract.ActionOnContinueClick())
                    }) {
                        Text("Continue")
                            .frame(maxWidth: .infinity)
                            .padding()
                            .background(Color.accentColor)
                            .foregroundColor(.white)
                            .cornerRadius(8)
                    }

                    Spacer()
                }
                .padding(.horizontal, 24)

                Text("By continuing, you agree to our Terms & Conditions")
                    .font(.caption)
                    .foregroundColor(.gray)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 24)
                    .padding(.bottom, 16)
            }
            .navigationBarTitle("Sign In", displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                onUIAction(SignInContract.ActionOnBackClick())
            }) {
                Image(systemName: "arrow.left")
            })
            .alert(
                NSLocalizedString("network_error", comment: ""),
                isPresented: Binding(
                    get: { state.showNetworkErrorDialog },
                    set: { newValue in
                        if newValue == false {
                            onUIAction(SignInContract.ActionOnNetworkErrorDialogOk())
                        }
                    }
                )
            ) {
                Button(NSLocalizedString("ok", comment: ""), role: .cancel) {
                    onUIAction(SignInContract.ActionOnNetworkErrorDialogOk())
                }
            } message: {
                Text(NSLocalizedString("check_connection", comment: ""))
            }

            // Validation alert
            .alert(
                NSLocalizedString("validation_error", comment: ""),
                isPresented: Binding(
                    get: { state.showInvalidValidationDialog },
                    set: { newValue in
                        if newValue == false {
                            onUIAction(SignInContract.ActionOnInvalidValidationDialogOk())
                        }
                    }
                )
            ) {
                Button(NSLocalizedString("ok", comment: ""), role: .cancel) {
                    onUIAction(SignInContract.ActionOnInvalidValidationDialogOk())
                }
            } message: {
                Text(NSLocalizedString("enter_login_password", comment: ""))
            }

            // Credentials alert
            .alert(
                NSLocalizedString("error", comment: ""),
                isPresented: Binding(
                    get: { state.showInvalidCredentialsDialog },
                    set: { newValue in
                        if newValue == false {
                            onUIAction(SignInContract.ActionOnInvalidCredentialsDialogOk())
                        }
                    }
                )
            ) {
                Button(NSLocalizedString("ok", comment: ""), role: .cancel) {
                    onUIAction(SignInContract.ActionOnInvalidCredentialsDialogOk())
                }
            } message: {
                Text(NSLocalizedString("invalid_credentials", comment: ""))
            }
            
        }
    }
}
