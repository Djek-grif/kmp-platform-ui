//
// Created by Djek Grif on 28/1/26.
//

import Foundation
import SwiftUI
import Shared

struct SignUpScreen: View {

    let signUpComponent: SignUpComponent
    let onUIAction: (SignUpContract.Action) -> Void
    @StateValue private var state: SignUpContract.State

    init(signUpComponent: SignUpComponent) {
        self.signUpComponent = signUpComponent
        self.onUIAction = signUpComponent.signUpViewModel.onUIAction
        _state = StateValue(signUpComponent.signUpViewModel.viewStateValue)
    }

    var body: some View {
        NavigationView {
            VStack(spacing: 0) {
                VStack(spacing: 16) {
                    Spacer()

                    Text("Create Account")
                        .font(.largeTitle)
                        .fontWeight(.bold)

                    Text("Sign up to get started")
                        .font(.body)
                        .foregroundColor(.gray)

                    Spacer().frame(height: 32)

                    TextField("Login", text: Binding(
                        get: { state.login },
                        set: { onUIAction(SignUpContract.ActionOnLoginChanged(value: $0)) }
                    ))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .autocapitalization(.none)
                    .keyboardType(.emailAddress)
                    .disabled(state.isProgress)

                    SecureField("Password", text: Binding(
                        get: { state.password },
                        set: { onUIAction(SignUpContract.ActionOnPasswordChanged(value: $0)) }
                    ))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .disabled(state.isProgress)

                    Spacer().frame(height: 24)

                    Button {
                        hideKeyboard()
                        onUIAction(SignUpContract.ActionOnContinueClick())
                    } label: {
                        HStack {
                            if state.isProgress {
                                ProgressView().progressViewStyle(.circular)
                            } else {
                                Text(NSLocalizedString("continue", comment: ""))
                            }
                        }
                        .frame(maxWidth: .infinity)
                        .padding()
                    }
                    .buttonStyle(.borderedProminent)
                    .disabled(state.isProgress)

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
            .navigationBarTitle("Sign Up", displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                onUIAction(SignUpContract.ActionOnBackClick())
            }) {
                Image(systemName: "arrow.left")
            })
            .alert(
                NSLocalizedString("validation_error", comment: ""),
                isPresented: Binding(
                    get: { state.showInvalidValidationDialog },
                    set: { newValue in
                        if newValue == false {
                            onUIAction(SignUpContract.ActionOnInvalidValidationDialogOk())
                        }
                    }
                )
            ) {
                Button(NSLocalizedString("ok", comment: ""), role: .cancel) {
                    onUIAction(SignUpContract.ActionOnInvalidValidationDialogOk())
                }
            } message: {
                Text(NSLocalizedString("enter_login_password", comment: ""))
            }
            .alert(
                NSLocalizedString("error", comment: ""),
                isPresented: Binding(
                    get: { state.showUserAlreadyExistsDialog },
                    set: { newValue in
                        if newValue == false {
                            onUIAction(SignUpContract.ActionOnUserAlreadyExistsDialogOk())
                        }
                    }
                )
            ) {
                Button(NSLocalizedString("ok", comment: ""), role: .cancel) {
                    onUIAction(SignUpContract.ActionOnUserAlreadyExistsDialogOk())
                }
            } message: {
                Text(NSLocalizedString("user_already_exists_message", comment: ""))
            }
        }
    }
}
