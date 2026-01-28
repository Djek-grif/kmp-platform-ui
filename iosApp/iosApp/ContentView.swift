import SwiftUI
import Shared

struct ContentView: View {
    let root: RootComponent
    
    @StateValue
    private var stack: ChildStack<AnyObject, RootComponentChild>
    
    init(root: RootComponent) {
        self.root = root
        _stack = StateValue(root.stack)
    }
    
    var body: some View {
        let child = stack.active.instance
        
        switch child {
        case let welcome as RootComponentChild.Welcome:
            WelcomeScreen(welcomeComponent: welcome.component)
        case let signIn as RootComponentChild.SignIn:
            SignInScreen(signInComponent: signIn.component)
        case let signUp as RootComponentChild.SignUp:
            SignUpScreen(signUpComponent: signUp.component)
        default:
            Text("Unknown screen")
        }
    }
}
