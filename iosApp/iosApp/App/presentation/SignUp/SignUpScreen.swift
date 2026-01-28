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

    @State private var login: String = ""
    @State private var password: String = ""

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

                    TextField("Login", text: $login)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .autocapitalization(.none)
                        .keyboardType(.emailAddress)

                    SecureField("Password", text: $password)
                        .textFieldStyle(RoundedBorderTextFieldStyle())

                    Spacer().frame(height: 24)

                    Button(action: {
                        onUIAction(SignUpContract.ActionOnContinueClick())
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
            .navigationBarTitle("Sign Up", displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                onUIAction(SignUpContract.ActionOnBackClick())
            }) {
                Image(systemName: "arrow.left")
            })
        }
    }
}
