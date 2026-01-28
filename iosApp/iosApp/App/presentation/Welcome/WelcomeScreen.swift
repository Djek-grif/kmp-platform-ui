//
// Created by Djek Grif on 7/1/26.
//

import Foundation
import SwiftUI
import Shared

struct WelcomeScreen: View {
    
    let welcomeComponent: WelcomeComponent
    let onUIAction: (WelcomeContract.Action) -> Void
    @StateValue private var state: WelcomeContract.State
    
    
    init(welcomeComponent: WelcomeComponent) {
        self.welcomeComponent = welcomeComponent
        self.onUIAction = welcomeComponent.welcomeViewModel.onUIAction
        _state = StateValue(welcomeComponent.welcomeViewModel.viewStateValue)
    }
    
    var body: some View {
            VStack {
                Image(systemName: "swift")
                    .font(.system(size: 200))
                    .foregroundColor(.accentColor)
                Text("\(state.title)").padding()
                
                Button("SignIn") {
                    onUIAction(WelcomeContract.ActionOnSignInClick())
                }.padding(.bottom, 20)
                
                Button("SignUp") {
                    onUIAction(WelcomeContract.ActionOnSignUpClick())
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .padding()
        }

}
