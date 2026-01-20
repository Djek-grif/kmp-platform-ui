//
// Created by Djek Grif on 7/1/26.
//

import Foundation
import SwiftUI
import Shared

struct WelcomeScreen: View {
    
    let welcomeComponent: WelcomeComponent
    @StateValue private var state: WelcomeContract.State
    
    
    init(welcomeComponent: WelcomeComponent) {
        self.welcomeComponent = welcomeComponent
        _state = StateValue(welcomeComponent.welcomeViewModel.viewStateValue)
    }
    
    var body: some View {
            VStack {
                Image(systemName: "swift")
                    .font(.system(size: 200))
                    .foregroundColor(.accentColor)
                Text("\(state.title)").padding()
                Button("Click me!") {
                    
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .padding()
        }

}
