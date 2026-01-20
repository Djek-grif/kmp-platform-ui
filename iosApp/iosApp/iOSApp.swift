import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    @StateObject private var holder = RootHolder()
    
    var body: some Scene {
            WindowGroup {
                ContentView(root: holder.root)
//                    .onAppear { holder.lifecycle.resume() }
//                    .onDisappear { holder.lifecycle.stop() }
            }
        }
}
