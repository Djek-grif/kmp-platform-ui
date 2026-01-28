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

    @State private var login: String = ""
    @State private var password: String = ""

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

                    TextField("Login", text: $login)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .autocapitalization(.none)
                        .keyboardType(.emailAddress)

                    SecureField("Password", text: $password)
                        .textFieldStyle(RoundedBorderTextFieldStyle())

                    Spacer().frame(height: 24)

                    Button(action: {
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
        }
    }
}
