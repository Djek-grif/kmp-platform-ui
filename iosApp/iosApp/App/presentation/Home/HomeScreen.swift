//
// Created by Djek Grif on 28/1/26.
//

import Foundation
import SwiftUI
import Shared

struct HomeScreen: View {

    let homeComponent: HomeComponent
    @StateValue private var state: HomeContract.State

    init(homeComponent: HomeComponent) {
        self.homeComponent = homeComponent
        _state = StateValue(homeComponent.homeViewModel.viewStateValue)
    }

    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                Spacer()

                Text("Welcome to Home!")
                    .font(.largeTitle)
                    .fontWeight(.bold)

                Text("You have successfully logged in.")
                    .font(.body)
                    .foregroundColor(.gray)

                Spacer()
            }
            .padding(.horizontal, 24)
            .navigationBarTitle("Home", displayMode: .inline)
        }
    }
}
